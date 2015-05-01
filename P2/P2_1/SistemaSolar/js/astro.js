function Astro() {
    this.vertex = null;
    this.faces = null;
    this.VERTEX = null;
    this.FACES = null;
    this.texture = null;
    
    this.model = function (GL, radius, textureURL) {
        this.vertex = SPHERE.getSphereVertex(radius, 64); // Se obtiene el array de vértices
        this.faces = SPHERE.getShereFaces(64); // Se obtiene el array de caras
        
        this.VERTEX = GL.createBuffer(); // Se crea el Vertex Buffer Object de los vértices del cubo
        GL.bindBuffer(GL.ARRAY_BUFFER, this.VERTEX); // Se enlazan los vértices
        GL.bufferData(GL.ARRAY_BUFFER, new Float32Array(this.vertex), GL.STATIC_DRAW); // Se le asignan los valores
        
        this.FACES = GL.createBuffer(); // Se crea el Vertex Buffer Object de las caras del cubo
        GL.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, this.FACES); // Se enlazan las caras
        GL.bufferData(GL.ELEMENT_ARRAY_BUFFER, new Uint16Array(this.faces), GL.STATIC_DRAW); // Se le asignan los valores
        
        this.texture = TEXTURE.getTexture(GL, textureURL); // Se genera la textura
    };
    
    this.draw = function (GL, _position, _uv) {
        if (this.texture.webglTexture) { // Si tiene textura
            GL.activeTexture(GL.TEXTURE0); // Se activa la textura
            GL.bindTexture(GL.TEXTURE_2D, this.texture.webglTexture); // Se enlaza la textura
        }
        
        GL.bindBuffer(GL.ARRAY_BUFFER, this.VERTEX); // Se enlazan los vértices
        GL.vertexAttribPointer(_position, 3, GL.FLOAT, false, 4 * (3 + 2), 0); // Se define el "puntero" a los vértices
        GL.vertexAttribPointer(_uv, 2, GL.FLOAT, false, 4 * (3 + 2), 3 * 4); // Se define el "puntero" a las coords. de textura

        GL.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, this.FACES); // Se enlazan las caras
        GL.drawElements(GL.TRIANGLES, this.faces.length, GL.UNSIGNED_SHORT, 0); // Se pintan 6 caras * 2 triángulos/cara * 3 puntos/triángulo
    };
};