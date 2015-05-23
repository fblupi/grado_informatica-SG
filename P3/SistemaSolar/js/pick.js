function animacionOnOff(interpolator) {
    var ts = document.getElementById("sistemaSolar__" + interpolator);
    ts.getAttribute("enabled") === "true" ? ts.setAttribute("enabled", "false") : ts.setAttribute("enabled", "true");
}