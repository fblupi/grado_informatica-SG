
package Model;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class Camara extends BranchGroup {
    
    private boolean activa;
    private Transform3D transform;
    private TransformGroup tg;
    private ViewPlatform vp;
    private View view;
    private Canvas3D canvas;
    private boolean perspectiva;
    private boolean movimiento;
    private Point3d posicion;
    private Point3d interes;
    private Vector3d vUp;
    private double anguloOEscala;
    private double planoDelantero;
    private double planoTrasero;
    
    public Camara(boolean perspectiva, boolean movimiento, Canvas3D canvas, Point3d posicion, Point3d interes, Vector3d vUp, double anguloOEscala, double planoDelantero, double planoTrasero) {
        this.activa = false;
        this.perspectiva = perspectiva;
        this.movimiento = movimiento;
        this.posicion = posicion;
        this.interes = interes;
        this.vUp = vUp;
        this.anguloOEscala = anguloOEscala;
        this.planoDelantero = planoDelantero;
        this.planoTrasero = planoTrasero;
        
        transform = new Transform3D();
        transform.lookAt(this.posicion,this.interes,this.vUp);
        transform.invert();
        
        tg = new TransformGroup(transform);
        
        vp = new ViewPlatform();
        
        /*
        if(this.movimiento) {
            OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL);
            orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 200.0f));
            orbit.setZoomFactor (2.0f);
            vp.setViewPlatformBehavior(orbit);
        }
        */
       
        tg.addChild(vp);
        
        view = new View();
        view.setPhysicalBody(new PhysicalBody());
        view.setPhysicalEnvironment(new PhysicalEnvironment());
        
        if(this.perspectiva) {
            view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
            view.setFieldOfView(Math.toRadians(this.anguloOEscala));
            view.setFrontClipDistance(this.planoDelantero);
            view.setBackClipDistance(this.planoTrasero);
        } else {
            view.setProjectionPolicy(View.PARALLEL_PROJECTION);
            view.setScreenScalePolicy(View.SCALE_EXPLICIT);
            view.setScreenScale(this.anguloOEscala);
            view.setFrontClipDistance(this.planoDelantero);
            view.setBackClipDistance(this.planoTrasero);
        }
        
        this.canvas = canvas;
        
        view.attachViewPlatform(vp);
        
        this.addChild(tg);
    }
    
    public void activar() {
        if(!activa) {
            view.addCanvas3D(this.canvas);
            activa = true;
        }
    }
    
    public void desactivar() {
        if(activa) {
            view.removeCanvas3D(this.canvas);
            activa = false;
        }
    }
}
