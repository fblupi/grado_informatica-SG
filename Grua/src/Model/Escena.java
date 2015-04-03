
package Model;

import javax.media.j3d.BranchGroup;

class Escena extends BranchGroup {
    
    private Grua grua;
  
    Escena() {
        grua = new Grua();
        this.addChild(grua);
    }
  
    void aumentarRotacion() {
        grua.aumentarRotacion();
    }
  
    void disminuirRotacion() {
        grua.disminuirRotacion();
    }
  
    void aumentarDesplazamiento() {
        grua.aumentarDesplazamiento();
    }

    void disminuirDesplazamiento() {
        grua.disminuirDesplazamiento();
    }

    void aumentarElevacion() {
        grua.aumentarElevacion();
    }

    void disminuirElevacion() {
        grua.disminuirElevacion();
    }
    
}
