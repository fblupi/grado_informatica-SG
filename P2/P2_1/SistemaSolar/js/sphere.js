var SPHERE = {
    getSphereVertex: function (radius, res) { // Se obtienen los vértices, normales y coordenadas de textura
        var vertexData = [];
        for (var i = 0; i <= res; i++) {
            var theta = i * Math.PI / res;
            var sinTheta = Math.sin(theta);
            var cosTheta = Math.cos(theta);

            for (var j = 0; j <= res; j++) {
                var phi = j * 2 * Math.PI / res;
                var sinPhi = Math.sin(phi);
                var cosPhi = Math.cos(phi);

                var x = cosPhi * sinTheta;
                var y = cosTheta;
                var z = sinPhi * sinTheta;
                var u = 1 - (j / res);
                var v = 1 - (i / res);

                // Vértices
                vertexData.push(radius * x);
                vertexData.push(radius * y);
                vertexData.push(radius * z);
                // Normales
                vertexData.push(x);
                vertexData.push(y);
                vertexData.push(z);
                // Coordenadas de textura
                vertexData.push(u);
                vertexData.push(v);
            }
        }
        return vertexData;
    },
    
    getShereFaces: function (res) { // Se obtienen los índices para crear las caras
        var indexData = [];
        for (var i = 0; i < res; i++) {
            for (var j = 0; j < res; j++) {
                var first = (i * (res + 1)) + j;
                var second = first + res + 1;
                indexData.push(first);
                indexData.push(second);
                indexData.push(first + 1);
                indexData.push(second);
                indexData.push(second + 1);
                indexData.push(first + 1);
            }
        }
        return indexData;
    }
};