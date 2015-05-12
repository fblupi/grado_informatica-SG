function Astro (radio, urlTextura, distancia, velRotOrb, velRot, stoppable, luz) {
    
    this.RESOLUCION = 64;
    this.radio = radio;
    this.urlTextura = urlTextura;
    this.distancia = distancia;
    this.velRot = velRot;
    this.velRotOrb = velRotOrb;
    this.stoppable = stoppable;
    this.luz = luz;
    this.sphere = null;
    this.satelites = [];
    
    this.model = function (scene) {
        var sphereTexture = THREE.ImageUtils.loadTexture(this.urlTextura),
            sphereGeometry = new THREE.SphereGeometry(this.radio, this.RESOLUCION, this.RESOLUCION),
            sphereMaterial;
        if (luz) {
            sphereMaterial = new THREE.MeshBasicMaterial({map: sphereTexture});
        } else {
            //sphereMaterial = new THREE.MeshPhongMaterial({map: sphereTexture});
            sphereMaterial = new THREE.MeshBasicMaterial({map: sphereTexture});
        }
        this.sphere = new THREE.Mesh(sphereGeometry, sphereMaterial);
        this.sphere.position.x = -this.distancia;
        this.sphere.position.y = 0;
        this.sphere.position.z = 0;
        if (luz) {
            this.sphere.castShadow = false;
        } else {
            this.sphere.castShadow = true;
        }
        this.sphere.name = "objeto-" + scene.children.length;
        scene.add(this.sphere);
        if (luz) {
            var luz = new Luz(0xFFFFFF, this.sphere.position.x, this.sphere.position.y, this.sphere.position.z, true);
            scene.add(luz);
        }
    };
    
    this.animate = function (matrix) {        
        var rotOrbMatrix = new THREE.Matrix4().makeRotationY(this.velRotOrb);
        var translationMatrix = new THREE.Matrix4().makeTranslation(this.distancia / 100, 0, 0);
        var rotMatrix = new THREE.Matrix4().makeRotationY(this.velRot);
        
        if(matrix !== undefined) {
            rotOrbMatrix.multilply(matrix);
        }
        
        //translationMatrix.multiply(rotOrbMatrix);
        
        for (var i = 0; i < this.satelites.length; i++) {
            this.satelites[i].animate(translationMatrix);    
        }
        
        rotMatrix.multiply(translationMatrix);
        //this.sphere.applyMatrix(rotMatrix);
        this.sphere.applyMatrix(translationMatrix);
    };
    
    this.addSatelite = function (satelite) {
        this.satelites.push(satelite);
    };
    
};