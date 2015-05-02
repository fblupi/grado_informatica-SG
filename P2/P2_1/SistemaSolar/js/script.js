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
    
    SHADERS.initialize(GL);

    /*========================= THE MODEL ====================== */
    
    var tierra = new Astro();
    tierra.model(GL, 2, "res/tierra.jpg");

    /*========================= MATRIX ========================= */

    var PROJMATRIX = LIBS.getProjection(40, CANVAS.width / CANVAS.height, 1, 100); // Se establece la matriz de proyección
    var MOVEMATRIX = LIBS.getI4(); // Se inicia la matriz de movimiento como la matriz identidad
    var VIEWMATRIX = LIBS.getI4(); // Se inicia la matriz de vista como la matriz identidad

    LIBS.translateZ(VIEWMATRIX, -6); // Se traslada la cámara hacia atrás realizando una traslación sobre la matriz de vista
    
    var THETA = 0, PHI = 0; // Variables usadas para el movimiento

    /*========================= DRAWING ========================= */
    GL.enable(GL.DEPTH_TEST); // Se habilita el buffer test de profundidad
    GL.depthFunc(GL.LEQUAL); // Especifica el valor usado para las comparaciones del buffer de profundidad
    GL.clearColor(0.0, 0.0, 0.0, 0.0); // Se asigna el clear color como transparente
    GL.clearDepth(1.0); // Se asigna el valor de limpieza para el buffer de profundidad a 1
    
    var draw = function () { // Esta función dibuja la escena
        LIBS.setI4(MOVEMATRIX); // Se le da la matriz de identidad como valor a la matriz de movimiento
        LIBS.rotateY(MOVEMATRIX, THETA);    // Se gira THETA grados en el eje de la Y
        LIBS.rotateX(MOVEMATRIX, PHI);      // Se gira PHI grados en el eje de la X

        GL.viewport(0.0, 0.0, CANVAS.width, CANVAS.height); // Establece el área de dibujado
        GL.clear(GL.COLOR_BUFFER_BIT | GL.DEPTH_BUFFER_BIT); // La limpia
        
        GL.uniformMatrix4fv(SHADERS._Pmatrix, false, PROJMATRIX); // Se asigna la matriz de proyección
        GL.uniformMatrix4fv(SHADERS._Vmatrix, false, VIEWMATRIX); // Se asigna la matriz de vista
        GL.uniformMatrix4fv(SHADERS._Mmatrix, false, MOVEMATRIX); // Se asigna la matriz de modelo
        
        tierra.draw(GL);

        GL.flush(); // Se fuerza el dibujado
        window.requestAnimationFrame(draw); // Vuelve a pintar la escena
    };
    
    draw(); // Se inicia el dibujado
};