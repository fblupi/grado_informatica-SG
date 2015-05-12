var scene = new THREE.Scene(),
    camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 0.1, 1000),
    renderer = new THREE.WebGLRenderer({alpha: true}),
    sol,
    tierra,
    luna;

function renderScene() {
    tierra.animate();
    requestAnimationFrame(renderScene);
    renderer.render(scene, camera);
}

function main() {
    renderer.setClearColor(0x000000, 0.0);
    renderer.setSize(window.innerWidth, window.innerHeight);
    renderer.shadowMapEnabled = true;

    // Añadir cámara
    camera.position.x = 0;
    camera.position.y = 60;
    camera.position.z = 0;
    camera.lookAt(scene.position);

    // Añadir luz
    var luz = new Luz(0xFFFFFF, 0, 0, 0, true);
    luz.model(scene);
    
    // Modelo
    sol = new Astro(6 / 2, "../res/sol.jpg", 0, 0, 0.002, false, true);
    sol.model(scene);
    
    //tierra = new Astro(1.27 / 2, "../res/tierra.jpg", 1, 0.001, 0.005, false, false);
    tierra = new Astro(1.27 / 2, "../res/tierra.jpg", 8, 0.001, 0.005, false, false);
    tierra.model(scene);
    
    luna = new Astro(0.34 / 2, "../res/luna.jpg", 1, 0.01, 0, true, false);
    luna.model(scene);
    
    //tierra.addSatelite(luna);

    // Añadir fondo 
    /*
    var backgroundTexture = THREE.ImageUtils.loadTexture("../res/bg.jpg"),
        backgroundGeometry  = new THREE.SphereGeometry(200, 32, 32),
        backgroundMaterial  = new THREE.MeshBasicMaterial({map: backgroundTexture, side: THREE.BackSide}),
        background = new THREE.Mesh(backgroundGeometry, backgroundMaterial);
    scene.add(background);
    */
    
    $("#canvas").append(renderer.domElement);

    renderScene();
}

main();