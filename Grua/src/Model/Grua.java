
package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Grua extends BranchGroup {
    
    private float rotacion = 0;
    private float desplazamiento = 5;
    private float elevacion = 5;
    private BranchGroup base;
    private BranchGroup torre;
    private BranchGroup pluma;
    private BranchGroup carro;
    private BranchGroup cuerda;
    private BranchGroup gancho;
    private TransformGroup tRot;
    private TransformGroup tDes;
    private TransformGroup tEleCuerda;
    private TransformGroup tEleGancho;
    
    Grua() {
        base = dibujarBase();
        torre = dibujarTorre();
        pluma = dibujarPluma();
        carro = dibujarCarro();
        cuerda = dibujarCuerda();
        gancho = dibujarGancho();
        
        tRot = new TransformGroup();
        tRot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE );
        Transform3D rot = new Transform3D();
        rot.setRotation(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(rotacion)));
        tRot.setTransform(rot);
        
        tDes = new TransformGroup();
        tDes.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE );
        Transform3D des = new Transform3D();
        des.setTranslation(new Vector3f(desplazamiento, 0.0f, 0.0f));
        tDes.setTransform(des);
        
        tEleCuerda = new TransformGroup();
        tEleCuerda.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE );
        Transform3D eleCuerda1 = new Transform3D();
        eleCuerda1.setTranslation(new Vector3f(0.0f, 15.0f, 0.0f));
        Transform3D eleCuerda2 = new Transform3D();
        eleCuerda2.setScale(new Vector3d(1.0d, elevacion, 1.0d));
        Transform3D eleCuerda3 = new Transform3D();
        eleCuerda3.setTranslation(new Vector3f(0.0f, -15.0f, 0.0f));
        eleCuerda1.mul(eleCuerda2);
        eleCuerda1.mul(eleCuerda3);
        tEleCuerda.setTransform(eleCuerda1);
        
        tEleGancho = new TransformGroup();
        tEleGancho.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE );
        Transform3D eleGancho = new Transform3D();
        eleGancho.setTranslation(new Vector3f(0.0f, -elevacion, 0.0f));
        tEleGancho.setTransform(eleGancho);
        
        tEleCuerda.addChild(cuerda);
        tEleGancho.addChild(gancho);
        tDes.addChild(tEleCuerda);
        tDes.addChild(tEleGancho);
        tDes.addChild(carro);
        tRot.addChild(tDes);
        tRot.addChild(torre);
        tRot.addChild(pluma);
        this.addChild(tRot);
        this.addChild(base);
    }
    
    private BranchGroup dibujarBase() {
        BranchGroup bg = new BranchGroup();
        
        TransformGroup tg = new TransformGroup();
        
        Transform3D t3d = new Transform3D(); 
        t3d.setTranslation(new Vector3f(0.0f, 0.5f, 0.0f));
        tg.setTransform(t3d);
        
        Appearance ap = new Appearance();
        ap.setMaterial(new Material(
            new Color3f (0.5f, 0.5f, 0.0f),   // Color ambiental
            new Color3f (0.5f, 0.5f, 0.0f),   // Color emisivo
            new Color3f (0.5f, 0.5f, 0.0f),   // Color difuso
            new Color3f (0.6f, 0.6f, 0.6f),   // Color especular
            10.0f));                          // Brillo
        
        Cylinder aCylinder = new Cylinder(2.5f, 1.0f,
            Primitive.GENERATE_NORMALS, 16, 16,  
            ap);
        
        tg.addChild(aCylinder);
        bg.addChild(tg);
        
        return bg;
    }
    
    private BranchGroup dibujarTorre() {
        BranchGroup bg = new BranchGroup();
        
        TransformGroup tg = new TransformGroup();
        
        Transform3D t3d = new Transform3D(); 
        t3d.setTranslation(new Vector3f(0.0f, 8.5f, 0.0f));
        tg.setTransform(t3d);
        
        Appearance ap = new Appearance();
        ap.setMaterial(new Material(
            new Color3f (0.5f, 0.5f, 0.0f),   // Color ambiental
            new Color3f (0.5f, 0.5f, 0.0f),   // Color emisivo
            new Color3f (0.5f, 0.5f, 0.0f),   // Color difuso
            new Color3f (0.6f, 0.6f, 0.6f),   // Color especular
            10.0f));                          // Brillo
        
        Cylinder aCylinder = new Cylinder(0.5f, 15.0f,
            Primitive.GENERATE_NORMALS, 16, 16,  
            ap);
        
        tg.addChild(aCylinder);
        bg.addChild(tg);
        
        return bg;
    }
    
    private BranchGroup dibujarPluma() {
        BranchGroup bg = new BranchGroup();
        
        TransformGroup tg = new TransformGroup();
        
        Transform3D t3d = new Transform3D(); 
        t3d.setTranslation(new Vector3f(-7.5f, 17.0f, 0.0f));
        tg.setTransform(t3d);
        
        Appearance ap = new Appearance();
        ap.setMaterial(new Material(
            new Color3f (0.5f, 0.0f, 0.0f),   // Color ambiental
            new Color3f (0.5f, 0.0f, 0.0f),   // Color emisivo
            new Color3f (0.5f, 0.0f, 0.0f),   // Color difuso
            new Color3f (0.6f, 0.6f, 0.6f),   // Color especular
            10.0f));                          // Brillo
        
        Box aBox = new Box(10.0f, 1.0f, 1.0f,
            Primitive.GENERATE_NORMALS,  
            ap);
        
        tg.addChild(aBox);
        bg.addChild(tg);
        
        return bg;
    }
    
    private BranchGroup dibujarCarro() {
        BranchGroup bg = new BranchGroup();
        
        TransformGroup tg = new TransformGroup();
        
        Transform3D t3d = new Transform3D(); 
        t3d.setTranslation(new Vector3f(-16.5f, 15.5f, 0.0f));
        tg.setTransform(t3d);
        
        Appearance ap = new Appearance();
        ap.setMaterial(new Material(
            new Color3f (0.0f, 0.5f, 0.0f),   // Color ambiental
            new Color3f (0.0f, 0.5f, 0.0f),   // Color emisivo
            new Color3f (0.0f, 0.5f, 0.0f),   // Color difuso
            new Color3f (0.6f, 0.6f, 0.6f),   // Color especular
            10.0f));                          // Brillo
        
        Box aBox = new Box(1.0f, 0.5f, 1.0f,
            Primitive.GENERATE_NORMALS,  
            ap);
        
        tg.addChild(aBox);
        bg.addChild(tg);
        
        return bg;
    }
    
    private BranchGroup dibujarCuerda() {
        BranchGroup bg = new BranchGroup();
        
        TransformGroup tg = new TransformGroup();
        
        Transform3D t3d = new Transform3D(); 
        t3d.setTranslation(new Vector3f(-16.5f, 14.5f, 0.0f));
        tg.setTransform(t3d);
        
        Appearance ap = new Appearance();
        ap.setMaterial(new Material(
            new Color3f (0.5f, 0.5f, 0.0f),   // Color ambiental
            new Color3f (0.5f, 0.5f, 0.0f),   // Color emisivo
            new Color3f (0.5f, 0.5f, 0.0f),   // Color difuso
            new Color3f (0.6f, 0.6f, 0.6f),   // Color especular
            10.0f));                          // Brillo
        
        Cylinder aCylinder = new Cylinder(0.25f, 1.0f,
            Primitive.GENERATE_NORMALS, 16, 16,  
            ap);
        
        tg.addChild(aCylinder);
        bg.addChild(tg);
        
        return bg;
    }
    
    private BranchGroup dibujarGancho() {
        BranchGroup bg = new BranchGroup();
        
        TransformGroup tg = new TransformGroup();
        
        Transform3D t3d = new Transform3D(); 
        t3d.setTranslation(new Vector3f(-16.5f, 14.75f, 0.0f));
        tg.setTransform(t3d);
        
        Appearance ap = new Appearance();
        ap.setMaterial(new Material(
            new Color3f (0.0f, 0.0f, 0.5f),   // Color ambiental
            new Color3f (0.0f, 0.0f, 0.5f),   // Color emisivo
            new Color3f (0.0f, 0.0f, 0.5f),   // Color difuso
            new Color3f (0.6f, 0.6f, 0.6f),   // Color especular
            10.0f));                          // Brillo
        
        Cylinder aCylinder = new Cylinder(1.5f, 0.5f,
            Primitive.GENERATE_NORMALS, 16, 16,  
            ap);
        
        tg.addChild(aCylinder);
        bg.addChild(tg);
        
        return bg;
    }
    
    public void aumentarRotacion() {
        if(rotacion < 360.0f) {
            rotacion += 5.0f;
            Transform3D rot = new Transform3D();
            rot.setRotation(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(rotacion)));
            tRot.setTransform(rot);
        }
    }
    
    public void disminuirRotacion() {
        if(rotacion > 0.0f) {
            rotacion -= 5.0f;
            Transform3D rot = new Transform3D();
            rot.setRotation(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.toRadians(rotacion)));
            tRot.setTransform(rot);
        }
    }
    
    public void aumentarDesplazamiento() {
        if(desplazamiento < 10.0f) {
            desplazamiento += 1.0f;
            Transform3D des = new Transform3D();
            des.setTranslation(new Vector3f(desplazamiento, 0.0f, 0.0f));
            tDes.setTransform(des);
        }
    }
    
    public void disminuirDesplazamiento() {
        if(desplazamiento > 0.0f) {
            desplazamiento -= 1.0f;
            Transform3D des = new Transform3D();
            des.setTranslation(new Vector3f(desplazamiento, 0.0f, 0.0f));
            tDes.setTransform(des);
        }
    }
    
    public void aumentarElevacion() {
        if(elevacion < 14.5f) {
            elevacion += 0.5f;
            Transform3D eleCuerda1 = new Transform3D();
            eleCuerda1.setTranslation(new Vector3f(0.0f, 15.0f, 0.0f));
            Transform3D eleCuerda2 = new Transform3D();
            eleCuerda2.setScale(new Vector3d(1.0d, elevacion, 1.0d));
            Transform3D eleCuerda3 = new Transform3D();
            eleCuerda3.setTranslation(new Vector3f(0.0f, -15.0f, 0.0f));
            eleCuerda1.mul(eleCuerda2);
            eleCuerda1.mul(eleCuerda3);
            tEleCuerda.setTransform(eleCuerda1);
            Transform3D eleGancho = new Transform3D();
            eleGancho.setTranslation(new Vector3f(0.0f, -elevacion, 0.0f));
            tEleGancho.setTransform(eleGancho);
        }
    }
    
    public void disminuirElevacion() {
        if(elevacion > 0.0f) {
            elevacion -= 0.5f;
            Transform3D eleCuerda1 = new Transform3D();
            eleCuerda1.setTranslation(new Vector3f(0.0f, 15.0f, 0.0f));
            Transform3D eleCuerda2 = new Transform3D();
            eleCuerda2.setScale(new Vector3d(1.0d, elevacion, 1.0d));
            Transform3D eleCuerda3 = new Transform3D();
            eleCuerda3.setTranslation(new Vector3f(0.0f, -15.0f, 0.0f));
            eleCuerda1.mul(eleCuerda2);
            eleCuerda1.mul(eleCuerda3);
            tEleCuerda.setTransform(eleCuerda1);
            Transform3D eleGancho = new Transform3D();
            eleGancho.setTranslation(new Vector3f(0.0f, -elevacion, 0.0f));
            tEleGancho.setTransform(eleGancho);
        }
    }
    
}
