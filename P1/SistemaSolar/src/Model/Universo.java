
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
    
    private Canvas3D canvas;
    private VirtualUniverse universe;
    private Locale locale;
    private Camara camara;
    private Camara camaraPlanta;

    // ******* Constructor
  
    public Universo (Canvas3D canvas) {
        
        // Se crea el universo. La parte de la vista
        universe = new VirtualUniverse();
        locale = new Locale(universe);
        
        camara = new Camara(true, true, canvas, new Point3d (80,80,80), new Point3d (0,0,0), new Vector3d (0,1,0), 45.0f, 0.1f, 100.0f);
        camaraPlanta = new Camara(false, false, canvas, new Point3d (0,200,0), new Point3d (0,0,0), new Vector3d (0,0,-1), 0.002f, 0.1f, 200.0f);
        
        locale.addBranchGraph(camara);
        locale.addBranchGraph(camaraPlanta);
        
        camara.activar();
        camara.desactivar();
        camaraPlanta.activar();
        
        background = new Fondo("imgs/back.jpg");
        scene = new Escena(); 
        
        locale.addBranchGraph(background);
        locale.addBranchGraph(scene);
    }
  
    // ******* Public

    public void activarPlanta() {
        camara.desactivar();
        camaraPlanta.activar();
    }
    
    public void activarPerspectiva() {
        camaraPlanta.desactivar();
        camara.activar();
    }
    
    public void closeApp (int code) {
        System.exit (code);
    }
}
