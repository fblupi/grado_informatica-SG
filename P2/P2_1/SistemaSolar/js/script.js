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

    /*========================= THE CUBE ========================= */
    //POINTS :
    var cube_vertex = [ // Coordenadas del cubo (posición y textura)
        -1,-1,-1,    0,0,
        1,-1,-1,     1,0,
        1, 1,-1,     1,1,
        -1, 1,-1,    0,1,

        -1,-1, 1,    0,0,
        1,-1, 1,     1,0,
        1, 1, 1,     1,1,
        -1, 1, 1,    0,1,

        -1,-1,-1,    0,0,
        -1, 1,-1,    1,0,
        -1, 1, 1,    1,1,
        -1,-1, 1,    0,1,

        1,-1,-1,     0,0,
        1, 1,-1,     1,0,
        1, 1, 1,     1,1,
        1,-1, 1,     0,1,

        -1,-1,-1,    0,0,
        -1,-1, 1,    1,0,
        1,-1, 1,     1,1,
        1,-1,-1,     0,1,

        -1, 1,-1,    0,0,
        -1, 1, 1,    1,0,
        1, 1, 1,     1,1,
        1, 1,-1,     0,1
    ];

    var CUBE_VERTEX = GL.createBuffer (); // Se crea el Vertex Buffer Object de los vértices del cubo
    GL.bindBuffer(GL.ARRAY_BUFFER, CUBE_VERTEX); // Se enlazan los vértices
    GL.bufferData(GL.ARRAY_BUFFER, new Float32Array(cube_vertex), GL.STATIC_DRAW); // Se le asignan los valores

    //FACES :
    var cube_faces = [
        0,1,2,
        0,2,3,

        4,5,6,
        4,6,7,

        8,9,10,
        8,10,11,

        12,13,14,
        12,14,15,

        16,17,18,
        16,18,19,

        20,21,22,
        20,22,23
    ];
    var CUBE_FACES = GL.createBuffer(); // Se crea el Vertex Buffer Object de las caras del cubo
    GL.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, CUBE_FACES); // Se enlazan las caras
    GL.bufferData(GL.ELEMENT_ARRAY_BUFFER, new Uint16Array(cube_faces), GL.STATIC_DRAW); // Se le asignan los valores

    /*========================= MATRIX ========================= */

    var PROJMATRIX = LIBS.get_projection(40, CANVAS.width / CANVAS.height, 1, 100); // Se establece la matriz de proyección
    var MOVEMATRIX = LIBS.get_I4(); // Se inicia la matriz de movimiento como la matriz identidad
    var VIEWMATRIX = LIBS.get_I4(); // Se inicia la matriz de vista como la matriz identidad

    LIBS.translateZ(VIEWMATRIX, -6); // Se traslada la cámara hacia atrás realizando una traslación sobre la matriz de vista
    
    var THETA = 0, PHI = 0; // Variables usadas para el movimiento

    /*========================= TEXTURES ========================= */
    var get_texture = function (image_URL) { // Función para hacer una textura webgl
        var image = new Image(); // se crea un objeto image javaScript

        image.src = image_URL; // Se guardará la textura webgl como una propidad de la imagen
        image.webglTexture = false;
        
        image.onload = function (e) { // Esta función crea el objeto textura webgl cuando la imagen ha sido cargada
            var texture = GL.createTexture(); // Se crea la textura
            GL.pixelStorei(GL.UNPACK_FLIP_Y_WEBGL, true); // Se invierte el orden de los pixels verticales
            GL.bindTexture(GL.TEXTURE_2D, texture); // Se hace un emlace con el contexto
            GL.texImage2D(GL.TEXTURE_2D, 0, GL.RGBA, GL.RGBA, GL.UNSIGNED_BYTE, image); // Se envían los datos de la imagen a la textura
            GL.texParameteri(GL.TEXTURE_2D, GL.TEXTURE_MAG_FILTER, GL.LINEAR); // Se establece el filtro de ampliación
            GL.texParameteri(GL.TEXTURE_2D, GL.TEXTURE_MIN_FILTER, GL.NEAREST_MIPMAP_LINEAR); // Se establece el filtro de reducción
            GL.generateMipmap(GL.TEXTURE_2D); // Se generan texturas distintas para distintas resoluciones
            GL.bindTexture(GL.TEXTURE_2D, null); // Se libera el contexto 
            image.webglTexture = texture;
        };

        return image;
    };

    var cube_texture = get_texture("res/texture.png"); // Se crea la textura desde el recurso de imagen

    /*========================= DRAWING ========================= */
    GL.enable(GL.DEPTH_TEST); // Se habilita el buffer test de profundidad
    GL.depthFunc(GL.LEQUAL); // Especifica el valor usado para las comparaciones del buffer de profundidad
    GL.clearColor(0.0, 0.0, 0.0, 0.0); // Se asigna el clear color como transparente
    GL.clearDepth(1.0); // Se asigna el valor de limpieza para el buffer de profundidad a 1
    
    var dibujarCubo = function () {
        if (cube_texture.webglTexture) { // Si tiene textura
            GL.activeTexture(GL.TEXTURE0); // Se activa la textura
            GL.bindTexture(GL.TEXTURE_2D, cube_texture.webglTexture); // Se enlaza la textura
        }
        
        GL.bindBuffer(GL.ARRAY_BUFFER, CUBE_VERTEX); // Se enlazan los vértices
        GL.vertexAttribPointer(_position, 3, GL.FLOAT, false, 4 * (3 + 2), 0) ; // Se define el "puntero" a los vértices
        GL.vertexAttribPointer(_uv, 2, GL.FLOAT, false, 4 * (3 + 2), 3 * 4) ; // Se define el "puntero" a las coords. de textura

        GL.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, CUBE_FACES); // Se enlazan las caras
        GL.drawElements(GL.TRIANGLES, 6 * 2 * 3, GL.UNSIGNED_SHORT, 0); // Se pintan 6 caras * 2 triángulos/cara * 3 puntos/triángulo
    };
    
    var draw = function () { // Esta función dibuja la escena
        LIBS.set_I4(MOVEMATRIX); // Se le da la matriz de identidad como valor a la matriz de movimiento
        LIBS.rotateY(MOVEMATRIX, THETA);    // Se gira THETA grados en el eje de la Y
        LIBS.rotateX(MOVEMATRIX, PHI);      // Se gira PHI grados en el eje de la X

        GL.viewport(0.0, 0.0, CANVAS.width, CANVAS.height); // Establece el área de dibujado
        GL.clear(GL.COLOR_BUFFER_BIT | GL.DEPTH_BUFFER_BIT); // La limpia
        
        GL.uniformMatrix4fv(_Pmatrix, false, PROJMATRIX); // Se asigna la matriz de proyección
        GL.uniformMatrix4fv(_Vmatrix, false, VIEWMATRIX); // Se asigna la matriz de vista
        GL.uniformMatrix4fv(_Mmatrix, false, MOVEMATRIX); // Se asigna la matriz de modelo
        
        dibujarCubo(); // Se dibuja el cubo

        GL.flush(); // Se fuerza el dibujado
        window.requestAnimationFrame(draw); // Vuelve a pintar la escena
    };
    
    draw(); // Se inicia el dibujado
};