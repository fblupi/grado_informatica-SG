
package Model;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class Universo {
    // Atributos de relación
    private Fondo background;
    private Escena scene;
    
    private VirtualUniverse universe;
    private Locale locale;
    private Camara camara;
    private Camara camaraPlanta;    
    private Camara camaraLuna;
    private Camara camaraNave;

    // ******* Constructor
  
    public Universo (Canvas3D canvas, Canvas3D canvasVariable) {
        
        // Se crea el universo. La parte de la vista
        universe = new VirtualUniverse();
        locale = new Locale(universe);
        
        camaraPlanta = new Camara(false, false, canvas, new Point3d (0,200,0), new Point3d (0,0,0), new Vector3d (0,0,-1), 0.01f, 0.3f, 100.0f);
        camara = new Camara(true, true, canvasVariable, new Point3d (80,80,80), new Point3d (0,0,0), new Vector3d (0,1,0), 45.0f, 0.3f, 100.0f);
        camaraNave = new Camara(true, false, canvasVariable, new Point3d (0.01,0.5,-0.25), new Point3d (0,0,1), new Vector3d (0,1,0), 45.0f, 0.1f, 30.0f);
        camaraLuna = new Camara(true, false, canvasVariable, new Point3d (0.5,0.15,0), new Point3d (-1,-0.05,0), new Vector3d (1,1,0), 90.0f, 0.005f, 2.5f);
        
        locale.addBranchGraph(camara);
        locale.addBranchGraph(camaraPlanta);
        
        camaraLuna.activar();
        camaraPlanta.activar();
        
        background = new Fondo("imgs/back.jpg");
        scene = new Escena(); 
        
        scene.addCamaraLuna(camaraLuna);
        scene.addCamaraNave(camaraNave);
        
        locale.addBranchGraph(background);
        locale.addBranchGraph(scene);
    }
  
    // ******* Public
    
    public void activarCamaraPerspectiva() {
        camaraLuna.desactivar();
        camaraNave.desactivar();
        camara.activar();
    }
    
    public void activarCamaraNave() {
        camaraLuna.desactivar();
        camara.desactivar();
        camaraNave.activar();
    }
    
    public void activarCamaraLuna() {
        camaraNave.desactivar();
        camara.desactivar();
        camaraLuna.activar();
    }
    
    public void closeApp (int code) {
        System.exit (code);
    }
}
