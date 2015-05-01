var SHADERS = {
    _Pmatrix: null,
    _Vmatrix: null,
    _Mmatrix: null,
    _sampler:null,
    _uv: null,
    _position: null,
    initialize: function (GL) {
        var shaderVertexSource = "\n\
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

        var shaderFragmentSource = "\n\
precision mediump float;\n\
uniform sampler2D sampler;\n\
varying vec2 vUV;\n\
\n\
\n\
void main(void) {\n\
gl_FragColor = texture2D(sampler, vUV);\n\
}";
        
        var getShader = function (source, type, typeString) { // Función usada para compilar un shader
            var shader = GL.createShader(type);
            GL.shaderSource(shader, source);
            GL.compileShader(shader);
            if (!GL.getShaderParameter(shader, GL.COMPILE_STATUS)) {
                alert("ERROR IN " + typeString + " SHADER : " + GL.getShaderInfoLog(shader));
                return false;
            }
            return shader;
        };        
        
        var shaderVertex = getShader(shaderVertexSource, GL.VERTEX_SHADER, "VERTEX");           // Compilación del vertex shader
        var shaderFragment = getShader(shaderFragmentSource, GL.FRAGMENT_SHADER, "FRAGMENT");   // Compilador del fragment shader

        var SHADER_PROGRAM = GL.createProgram(); // Creación del SHADER_PROGRAM

        GL.attachShader(SHADER_PROGRAM, shaderVertex);     // Se adjunta el vertex shader
        GL.attachShader(SHADER_PROGRAM, shaderFragment);   // Se adjunta el fragment shader

        GL.linkProgram(SHADER_PROGRAM); // Se enlaza el SHADER_PROGRAM

        SHADERS._Pmatrix = GL.getUniformLocation(SHADER_PROGRAM, "Pmatrix");    // "Puntero" a la matriz de proyección
        SHADERS._Vmatrix = GL.getUniformLocation(SHADER_PROGRAM, "Vmatrix");    // "Puntero" a la matriz de vista
        SHADERS._Mmatrix = GL.getUniformLocation(SHADER_PROGRAM, "Mmatrix");    // "Puntero" a la matriz de modelo
        SHADERS._sampler = GL.getUniformLocation(SHADER_PROGRAM, "sampler");
        SHADERS._uv = GL.getAttribLocation(SHADER_PROGRAM, "uv");               // "Puntero" a la variable _uv (coordenadas de textura)
        SHADERS._position = GL.getAttribLocation(SHADER_PROGRAM, "position");   // "Puntero" a la variable _position

        GL.enableVertexAttribArray(_uv);        // Se habilita la variable _uv
        GL.enableVertexAttribArray(_position);  // Se habilita la variable _position

        GL.useProgram(SHADER_PROGRAM); // Se ha terminado de enlazar, se le indica a webgl que puede usar el SHADER_PROGRAM para renderizar
        GL.uniform1i(_sampler, 0); // _sampler es el canal de textura número 0
    }
};
