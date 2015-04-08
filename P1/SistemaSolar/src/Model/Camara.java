
package Model;

import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
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
    private TransformGroup tg;
    private ViewPlatform vp;
    private View view;
    private Canvas3D canvas;
    private Point3d posicion;   // Posición de la cámara
    private Point3d interes;    // Dónde mira
    private Vector3d vUp;       // View Up
    private double anguloOEscala;
    private double planoDelantero;
    private double planoTrasero;
    
    public Camara(boolean perspectiva, boolean movimiento, Canvas3D canvas, Point3d posicion, Point3d interes, Vector3d vUp, double anguloOEscala, double planoDelantero, double planoTrasero) {
        this.setPickable(false);
        
        this.activa = false;
        this.posicion = posicion;
        this.interes = interes;
        this.vUp = vUp;
        this.anguloOEscala = anguloOEscala;
        this.planoDelantero = planoDelantero;
        this.planoTrasero = planoTrasero;
        this.canvas = canvas;
        
        Transform3D transform = new Transform3D(); // Se crea la matriz de transformación para colocar la cámara
        transform.lookAt(this.posicion, this.interes, this.vUp); // Se define la posición y hacia donde mira la cámara
        transform.invert();
        
        tg = new TransformGroup(transform); // Se aplica la transformación al nodo de transformación
        vp = new ViewPlatform(); // Se crea el ViewPlatform
        
        if(movimiento) { // Si se permite mover la cámara se crean los comportamientos del ratón
            tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            
            MouseRotate myMouseRotate = new MouseRotate(MouseBehavior.INVERT_INPUT);
            myMouseRotate.setFactor(0.005);
            myMouseRotate.setTransformGroup(tg);
            myMouseRotate.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));

            MouseTranslate myMouseTranslate = new MouseTranslate(MouseBehavior.INVERT_INPUT);
            myMouseTranslate.setFactor(0.1);
            myMouseTranslate.setTransformGroup(tg);
            myMouseTranslate.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));

            MouseWheelZoom myMouseZoom = new MouseWheelZoom(MouseBehavior.INVERT_INPUT);
            myMouseZoom.setFactor(2.0);
            myMouseZoom.setTransformGroup(tg);
            myMouseZoom.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));
            
            tg.addChild(myMouseRotate);
            tg.addChild(myMouseTranslate);
            tg.addChild(myMouseZoom);
        }
       
        tg.addChild(vp); // Se agrega el ViewPlatform al nodo de transformación
        
        view = new View(); // Se crea el view
        view.setPhysicalBody(new PhysicalBody());
        view.setPhysicalEnvironment(new PhysicalEnvironment());
        
        if(perspectiva) {
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
        
        view.attachViewPlatform(vp); // Se agrega el ViewPlatform al View
        this.addChild(tg); // Se agrega el nodo de transformación al BranchGroup de salida
    }
    
    public void activar() {
        if(!activa) { // Si no está activa
            view.addCanvas3D(this.canvas); // Se agrega el Canvas al View
            activa = true;
        }
    }
    
    public void desactivar() {
        if(activa) { // Si está activa
            view.removeCanvas3D(this.canvas); // Se quita el Canvas al View
            activa = false;
        }
    }
    
}
