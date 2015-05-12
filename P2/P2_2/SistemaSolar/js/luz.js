function Luz (color, posX, posY, posZ, shadow) {
    
    this.color = color;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.shadow = shadow;
    this.light = null;
    
    this.model = function (scene) {
        this.light = new THREE.PointLight(this.color, 1, 0);
        this.light.position.set(this.posX, this.posY, this.posZ);
        this.light.castShadow = this.shadow;
        scene.add(this.light);
    };
    
};