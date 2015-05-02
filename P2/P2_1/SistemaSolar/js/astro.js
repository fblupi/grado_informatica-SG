function Astro(distancia, velRotOrb, velRot, stoppable) {
    this.vertex = null;
    this.faces = null;
    this.VERTEX = null;
    this.FACES = null;
    this.texture = null;
    this.distancia = distancia;
    this.velRotOrb = velRotOrb;
    this.velRot = velRot;
    this.rot = 0;
    this.rotOrb = 0;
    this.satelites = [];
    this.MATRIX = null;
    this.stoppable = stoppable;
    
    this.addSatelite = function(satelite) {
        this.satelites.push(satelite);
    };
    
    this.model = function (GL, radius, textureURL) {
        this.vertex = SPHERE.getSphereVertex(radius, 32); // Se obtiene el array de vértices
        this.faces = SPHERE.getShereFaces(32); // Se obtiene el array de caras
        
        this.VERTEX = GL.createBuffer(); // Se crea el Vertex Buffer Object de los vértices del cubo
        GL.bindBuffer(GL.ARRAY_BUFFER, this.VERTEX); // Se enlazan los vértices
        GL.bufferData(GL.ARRAY_BUFFER, new Float32Array(this.vertex), GL.STATIC_DRAW); // Se le asignan los valores
        
        this.FACES = GL.createBuffer(); // Se crea el Vertex Buffer Object de las caras del cubo
        GL.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, this.FACES); // Se enlazan las caras
        GL.bufferData(GL.ELEMENT_ARRAY_BUFFER, new Uint16Array(this.faces), GL.STATIC_DRAW); // Se le asignan los valores
        
        this.texture = TEXTURE.getTexture(GL, textureURL); // Se genera la textura
    };
    
    this.draw = function (GL, MATRIX) {
        
        this.MATRIX = MATRIX; // Asignamos a la matriz del astro el valor de entrada
        
        // Matrices auxiliares para cada una de las transformaciones
        var MATRIX_ROT_ORB = LIBS.getI4();
        var MATRIX_DIS = LIBS.getI4();
        var MATRIX_ROT = LIBS.getI4();
        
        // Rotación
        this.rot += this.velRot; // Aumenta el ángulo de rotación sobre si mismo
        LIBS.rotateY(MATRIX_ROT, this.rot); // Rota sobre si mismo
        this.MATRIX = LIBS.mul(this.MATRIX, MATRIX_ROT); // Se multiplica por la matriz del astro
        
        // Desplazamiento
        LIBS.translateZ(MATRIX_DIS, this.distancia); // Se desplaza a su posición en la órbita
        this.MATRIX = LIBS.mul(this.MATRIX, MATRIX_DIS); // Se multiplica por la matriz del astro
        
        // Satélites
        for(var i = 0; i < this.satelites.length; i++) {
            this.satelites[i].draw(GL, this.MATRIX); // Dibuja cada satélite 
        }
        
        // Rotación orbital
        if(!this.stoppable || !MOUSE.click) { // No es parable o no está pulsado el ratón
            this.rotOrb += this.velRotOrb; // Aumenta el ángulo de rotación orbital
        }
            LIBS.rotateY(MATRIX_ROT_ORB, this.rotOrb); // Rota sobre su astro de referencia
        this.MATRIX = LIBS.mul(this.MATRIX, MATRIX_ROT_ORB); // Se multiplica por la matriz del astro
        
        GL.uniformMatrix4fv(SHADERS._Mmatrix, false, this.MATRIX); // Se asigna la matriz de modelo 
        
        if (this.texture.webglTexture) { // Si tiene textura
            GL.activeTexture(GL.TEXTURE0); // Se activa la textura
            GL.bindTexture(GL.TEXTURE_2D, this.texture.webglTexture); // Se enlaza la textura
        }
        
        GL.bindBuffer(GL.ARRAY_BUFFER, this.VERTEX); // Se enlazan los vértices
        GL.vertexAttribPointer(SHADERS._position, 3, GL.FLOAT, false, 4 * (3 + 3 + 2), 0); // Se define el "puntero" a los vértices
        GL.vertexAttribPointer(SHADERS._normal, 3, GL.FLOAT, false, 4 * (3 + 3 + 2), 3 * 4); // Se define el "puntero" a las normales
        GL.vertexAttribPointer(SHADERS._uv, 2, GL.FLOAT, false, 4 * (3 + 3 + 2), (3 + 3) * 4); // Se define el "puntero" a las coords. de textura
        
        GL.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, this.FACES); // Se enlazan las caras
        GL.drawElements(GL.TRIANGLES, this.faces.length, GL.UNSIGNED_SHORT, 0); // Se pintan 6 caras * 2 triángulos/cara * 3 puntos/triángulo
    };
};