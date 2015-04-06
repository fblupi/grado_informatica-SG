
package Model;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class Universo {
    
    private Fondo background;
    private Escena scene;
    private VirtualUniverse universe;
    private Locale locale;
    private Camara camaraPerspectiva;
    private Camara camaraPlanta;    
    private Camara camaraLuna;
    private Camara camaraNave;
    private Pick pick;

    // ******* Constructor
  
    public Universo (Canvas3D canvas, Canvas3D canvasVariable) {
        // Se crea el universo. La parte de la vista
        universe = new VirtualUniverse();
        locale = new Locale(universe);
        
        camaraPlanta = new Camara(false, false, canvas, new Point3d (0,200,0), new Point3d (0,0,0), new Vector3d (0,0,-1), 0.0032f, 0.3f, 100.0f);
        camaraPerspectiva = new Camara(true, true, canvasVariable, new Point3d (80,80,80), new Point3d (0,0,0), new Vector3d (0,1,0), 45.0f, 0.3f, 100.0f);
        camaraNave = new Camara(true, false, canvasVariable, new Point3d (0,0.5,-0.25), new Point3d (0,0,1), new Vector3d (0,1,0), 45.0f, 0.1f, 30.0f);
        camaraLuna = new Camara(true, false, canvasVariable, new Point3d (1.6,0.5,0), new Point3d (-1,-0.25,0), new Vector3d (1,1,0), 60.0f, 0.1f, 30.0f);
        
        locale.addBranchGraph(camaraPerspectiva);
        locale.addBranchGraph(camaraPlanta);
        
        camaraPerspectiva.activar();
        camaraPlanta.activar();
        
        background = new Fondo("imgs/back.jpg");
        scene = new Escena();
        
        scene.addCamaraLuna(camaraLuna);
        scene.addCamaraNave(camaraNave);

        pick = new Pick(canvasVariable);
        scene.setPickable(true);
        scene.addChild(pick);
        pick.initSearch(scene);
        
        locale.addBranchGraph(background);
        locale.addBranchGraph(scene);
    }
  
    // ******* Public
    
    public void activarCamaraPerspectiva() {
        camaraLuna.desactivar();
        camaraNave.desactivar();
        camaraPerspectiva.activar();
    }
    
    public void activarCamaraNave() {
        camaraLuna.desactivar();
        camaraPerspectiva.desactivar();
        camaraNave.activar();
    }
    
    public void activarCamaraLuna() {
        camaraNave.desactivar();
        camaraPerspectiva.desactivar();
        camaraLuna.activar();
    }
    
    public void closeApp (int code) {
        System.exit (code);
    }
    
}
