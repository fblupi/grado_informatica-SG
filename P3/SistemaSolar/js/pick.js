var estado = {
    "TimeOrbitalMercurio": true,
    "TimeOrbitalVenus": true,
    "TimeOrbitalTierra": true,
    "TimeOrbitalMarte": true,
    "TimeOrbitalJupiter": true,
    "TimeOrbitalSaturno": true,
    "TimeOrbitalUrano": true,
    "TimeOrbitalNeptuno": true
};

function animacionOnOff(interpolator) {   
    estado[interpolator] = !estado[interpolator];
    document.getElementById(interpolator).setAttribute("enabled", estado[interpolator].toString());
}