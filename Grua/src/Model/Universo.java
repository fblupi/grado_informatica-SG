
package Model;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class Universo {
    
    private Luces lights;
    private Escena scene;

    // ******* Constructor
    
    public Universo (Canvas3D canvas) {
        // Todo cuelga de un nodo raiz
        BranchGroup root = new BranchGroup();

        // Se crean las luces y se añaden
        lights = new Luces ();
        root.addChild(lights);

        // Se crea y se añade la escena al universo
        scene = new Escena (); 
        root.addChild(scene);

        // Se crea el universo. La parte de la vista
        SimpleUniverse universe = createUniverse (canvas);

        // Se optimiza la escena y se cuelga del universo
        root.compile();
        universe.addBranchGraph(root);
    }
  
    // ******* Private
    
    private SimpleUniverse createUniverse (Canvas3D canvas) {
        // Se crea manualmente un ViewingPlatform para poder personalizarlo y asignárselo al universo
        ViewingPlatform viewingPlatform = new ViewingPlatform();

        // La transformación de vista, dónde se está, a dónde se mira, Vup
        TransformGroup viewTransformGroup = viewingPlatform.getViewPlatformTransform();
        Transform3D viewTransform3D = new Transform3D();
        viewTransform3D.lookAt (new Point3d (50,50,50), new Point3d (0,0,0), new Vector3d (0,1,0));
        viewTransform3D.invert();
        viewTransformGroup.setTransform (viewTransform3D);

        // El comportamiento, para mover la camara con el raton
        OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL);
        orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 100.0f));
        orbit.setZoomFactor (2.0f);
        viewingPlatform.setViewPlatformBehavior(orbit);

        // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
        Viewer viewer = new Viewer (canvas);
        View view = viewer.getView();
        view.setFieldOfView(Math.toRadians(45));
        view.setBackClipDistance(50.0);

        // Se construye y devuelve el Universo con los parametros definidos
        return new SimpleUniverse (viewingPlatform, viewer);
    }
  
    // ******* Public
  
    public void closeApp (int code) {
        System.exit (code);
    }
  
    // Esta clase es la fachada del modelo. 
    // Recibe todas las solicitudes de actuación y las redirige a los objetos que corresponden
    public void aumentarRotacion() {
        scene.aumentarRotacion();
    }
  
    public void disminuirRotacion() {
        scene.disminuirRotacion();
    }
  
    public void aumentarDesplazamiento() {
        scene.aumentarDesplazamiento();
    }
  
    public void disminuirDesplazamiento() {
        scene.disminuirDesplazamiento();
    }
  
    public void aumentarElevacion() {
        scene.aumentarElevacion();
    }
  
    public void disminuirElevacion() {
        scene.disminuirElevacion();
    }
    
}
