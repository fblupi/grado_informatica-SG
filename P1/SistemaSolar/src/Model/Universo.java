
package Model;

import javax.media.j3d.BoundingSphere;
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
        
        // Se crean las cámaras
        camaraPlanta = new Camara(false, false, canvas, new Point3d (0,200,0), new Point3d (0,0,0), new Vector3d (0,0,-1), 0.0032f, 0.3f, 100.0f);
        camaraPerspectiva = new Camara(true, true, canvasVariable, new Point3d (80,80,80), new Point3d (0,0,0), new Vector3d (0,1,0), 45.0f, 0.3f, 100.0f);
        camaraNave = new Camara(true, false, canvasVariable, new Point3d (0,0.5,-0.25), new Point3d (0,0,1), new Vector3d (0,1,0), 45.0f, 0.1f, 30.0f);
        camaraLuna = new Camara(true, false, canvasVariable, new Point3d (0,0.5,0), new Point3d (-1,-0.25,0), new Vector3d (1,1,0), 100.0f, 0.05f, 15.0f);
        
        // Se compilan los BG de las cámaras que cuelgan del locale
        camaraPerspectiva.compile();
        camaraPlanta.compile();
        
        // Se cuelgan las cámaras de perspectiva y de planta del locale
        locale.addBranchGraph(camaraPerspectiva);
        locale.addBranchGraph(camaraPlanta);
        
        // Se activan las cámaras de perspectiva y planta
        camaraPerspectiva.activar();
        camaraPlanta.activar();
        
        background = new Fondo("imgs/back.jpg"); // Se crea el fondo
        scene = new Escena(camaraLuna, camaraNave); // Se crea la escena
        
        // Se crea el pick y se cuelga de la escena
        pick = new Pick(canvasVariable);
        pick.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 200.0));
        scene.addChild(pick);
        
        // Se compilan los BG del background y la escena
        background.compile();
        scene.compile();
        
        // Se cuelgan el background y la escena dle locale
        locale.addBranchGraph(background);
        locale.addBranchGraph(scene);
        
        pick.initSearch(scene); // Se inicia la búsqueda del pick
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
