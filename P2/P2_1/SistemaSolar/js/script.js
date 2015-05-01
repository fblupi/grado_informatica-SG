var main = function () {
    var CANVAS = document.getElementById("your_canvas");
    CANVAS.width = window.innerWidth;
    CANVAS.height = window.innerHeight;

    /*========================= CAPTURE MOUSE EVENTS ========================= */
    
    var drag = false,
        old_x, old_y, 
        dX = 0, dY = 0;

    var mouseDown = function (e) { // El botón está pulsado
        drag = true;
        old_x = e.pageX;
        old_y = e.pageY;
        e.preventDefault();
        return false;
    };

    var mouseUp = function (e) { // El botón se suelta
        drag = false;
    };

    var mouseMove = function (e) { // Se mueve el ratón
        if (!drag) {
            return false;
        }
        dX = (e.pageX - old_x) * Math.PI / CANVAS.width;
        dY = (e.pageY - old_y) * Math.PI / CANVAS.height;
        THETA += dX;
        PHI += dY;
        old_x = e.pageX;
        old_y = e.pageY;
        e.preventDefault();
    };

    // Se asignan los listeners
    CANVAS.addEventListener("mousedown", mouseDown, false);
    CANVAS.addEventListener("mouseup", mouseUp, false);
    CANVAS.addEventListener("mouseout", mouseUp, false);
    CANVAS.addEventListener("mousemove", mouseMove, false);

    /*========================= GET WEBGL CONTEXT ========================= */
    var GL; // Se crea la variable webl
    try {
        GL = CANVAS.getContext("experimental-webgl", {antialias: true});
    } catch (e) {
        alert("You are not webgl compatible :(");
        return false;
    }

    /*========================= SHADERS ========================= */
    /*jshint multistr: true */

    var shader_vertex_source = "\n\
attribute vec3 position;\n\
uniform mat4 Pmatrix;\n\
uniform mat4 Vmatrix;\n\
uniform mat4 Mmatrix;\n\
attribute vec2 uv;\n\
varying vec2 vUV;\n\
void main(void) { //pre-built function\n\
gl_Position = Pmatrix*Vmatrix*Mmatrix*vec4(position, 1.);\n\
vUV=uv;\n\
}";

    var shader_fragment_source = "\n\
precision mediump float;\n\
uniform sampler2D sampler;\n\
varying vec2 vUV;\n\
\n\
\n\
void main(void) {\n\
gl_FragColor = texture2D(sampler, vUV);\n\
}";

    var get_shader = function (source, type, typeString) { // Función usada para compilar un shader
        var shader = GL.createShader(type);
        GL.shaderSource(shader, source);
        GL.compileShader(shader);
        if (!GL.getShaderParameter(shader, GL.COMPILE_STATUS)) {
            alert("ERROR IN " + typeString + " SHADER : " + GL.getShaderInfoLog(shader));
            return false;
        }
        return shader;
    };

    var shader_vertex = get_shader(shader_vertex_source, GL.VERTEX_SHADER, "VERTEX");           // Compilación del vertex shader
    var shader_fragment = get_shader(shader_fragment_source, GL.FRAGMENT_SHADER, "FRAGMENT");   // Compilador del fragment shader

    var SHADER_PROGRAM = GL.createProgram(); // Creación del SHADER_PROGRAM
    
    GL.attachShader(SHADER_PROGRAM, shader_vertex);     // Se adjunta el vertex shader
    GL.attachShader(SHADER_PROGRAM, shader_fragment);   // Se adjunta el fragment shader

    GL.linkProgram(SHADER_PROGRAM); // Se enlaza el SHADER_PROGRAM

    var _Pmatrix = GL.getUniformLocation(SHADER_PROGRAM, "Pmatrix");    // "Puntero" a la matriz de proyección
    var _Vmatrix = GL.getUniformLocation(SHADER_PROGRAM, "Vmatrix");    // "Puntero" a la matriz de vista
    var _Mmatrix = GL.getUniformLocation(SHADER_PROGRAM, "Mmatrix");    // "Puntero" a la matriz de modelo
    var _sampler = GL.getUniformLocation(SHADER_PROGRAM, "sampler");
    
    var _uv = GL.getAttribLocation(SHADER_PROGRAM, "uv");               // "Puntero" a la variable _uv (coordenadas de textura)
    var _position = GL.getAttribLocation(SHADER_PROGRAM, "position");   // "Puntero" a la variable _position

    GL.enableVertexAttribArray(_uv);        // Se habilita la variable _uv
    GL.enableVertexAttribArray(_position);  // Se habilita la variable _position

    GL.useProgram(SHADER_PROGRAM); // Se ha terminado de enlazar, se le indica a webgl que puede usar el SHADER_PROGRAM para renderizar
    GL.uniform1i(_sampler, 0); // _sampler es el canal de textura número 0
    
    //SHADERS.initialize(GL);

    /*========================= THE MODEL ====================== */
    
    var tierra = new Astro();
    tierra.model(GL, 2, "res/tierra.jpg");

    /*========================= MATRIX ========================= */

    var PROJMATRIX = LIBS.get_projection(40, CANVAS.width / CANVAS.height, 1, 100); // Se establece la matriz de proyección
    var MOVEMATRIX = LIBS.get_I4(); // Se inicia la matriz de movimiento como la matriz identidad
    var VIEWMATRIX = LIBS.get_I4(); // Se inicia la matriz de vista como la matriz identidad

    LIBS.translateZ(VIEWMATRIX, -6); // Se traslada la cámara hacia atrás realizando una traslación sobre la matriz de vista
    
    var THETA = 0, PHI = 0; // Variables usadas para el movimiento

    /*========================= TEXTURES ========================= */
    var tierra_texture = TEXTURE.getTexture(GL,"res/tierra.jpg"); // Se crea la textura desde el recurso de imagen
    var luna_texture = TEXTURE.getTexture(GL,"res/luna.jpg");

    /*========================= DRAWING ========================= */
    GL.enable(GL.DEPTH_TEST); // Se habilita el buffer test de profundidad
    GL.depthFunc(GL.LEQUAL); // Especifica el valor usado para las comparaciones del buffer de profundidad
    GL.clearColor(0.0, 0.0, 0.0, 0.0); // Se asigna el clear color como transparente
    GL.clearDepth(1.0); // Se asigna el valor de limpieza para el buffer de profundidad a 1
    
    var draw = function () { // Esta función dibuja la escena
        LIBS.set_I4(MOVEMATRIX); // Se le da la matriz de identidad como valor a la matriz de movimiento
        LIBS.rotateY(MOVEMATRIX, THETA);    // Se gira THETA grados en el eje de la Y
        LIBS.rotateX(MOVEMATRIX, PHI);      // Se gira PHI grados en el eje de la X

        GL.viewport(0.0, 0.0, CANVAS.width, CANVAS.height); // Establece el área de dibujado
        GL.clear(GL.COLOR_BUFFER_BIT | GL.DEPTH_BUFFER_BIT); // La limpia
        
        GL.uniformMatrix4fv(_Pmatrix, false, PROJMATRIX); // Se asigna la matriz de proyección
        GL.uniformMatrix4fv(_Vmatrix, false, VIEWMATRIX); // Se asigna la matriz de vista
        GL.uniformMatrix4fv(_Mmatrix, false, MOVEMATRIX); // Se asigna la matriz de modelo
        
        tierra.draw(GL, _position, _uv);

        GL.flush(); // Se fuerza el dibujado
        window.requestAnimationFrame(draw); // Vuelve a pintar la escena
    };
    
    draw(); // Se inicia el dibujado
};