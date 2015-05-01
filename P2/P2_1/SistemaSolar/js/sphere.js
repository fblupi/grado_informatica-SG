var SPHERE = {
    getSphereVertex: function (radius, res) {
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

                vertexData.push(radius * x);
                vertexData.push(radius * y);
                vertexData.push(radius * z);
                vertexData.push(u);
                vertexData.push(v);
                //vertexData.push(x);
                //vertexData.push(y);
                //vertexData.push(z);
            }
        }
        return vertexData;
    },
    
    getShereFaces: function (res) {
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