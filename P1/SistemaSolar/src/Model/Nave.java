
package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.File;
import java.io.FileNotFoundException;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;

public class Nave extends BranchGroup {
    
    private String obj;
    private long velocidad;
    private float[] knots;
    private Point3f[] posiciones;
    private Quat4f[] angulos;
    private RotPosPathInterpolator interpolator;
    private TransformGroup transform;
    
    public Nave(String obj, long velocidad, Point3f[] posiciones, AxisAngle4f[] angulos, float[] knots) {
        this.setPickable(false);
        this.obj = obj;
        this.velocidad = velocidad;
        this.posiciones = new Point3f[posiciones.length];
        this.angulos = new Quat4f[posiciones.length];
        this.knots = new float[posiciones.length];
        for(int i=0; i<posiciones.length; i++) {
            this.posiciones[i] = posiciones[i];
            this.angulos[i] = new Quat4f();
            this.angulos[i].set(angulos[i]);
            this.knots[i] = knots[i];
        }
        
        // Se cuelgan del grafo las transformaciones y la figura
        transform = mover(); // Se crea el nodo de transformación
        
        // Escalado para que la nave sea más grande
        TransformGroup scale = new TransformGroup();
        Transform3D t3d = new Transform3D (); // Se crea la matriz de transformación
        t3d.setScale(1.5); // Se define el escalado
        scale.setTransform(t3d); // Se aplica el escalado al nodo de transformación
        
        BranchGroup figure = new BranchGroup (); // Se crea la rama desde la que cuelga la geometría y apariencia de la nave
        figure.addChild(importarModelo()); // la figura se cuelga de la transformación
        
        scale.addChild(figure);  // la figura se cuelga del escalado
        transform.addChild(scale); // la figura escalada se cuelga de la transformación
        this.addChild(transform); // la transformación se cuelga del nodo devuelto
        
    }
    
    public void addCamara(Camara camara) {
        transform.addChild(camara);
    }
    
    private TransformGroup mover() {
        TransformGroup t = new TransformGroup (); // Se crea el nodo de transformación
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); // Se le permite que se cambie en tiempo de ejecución
        Transform3D t3d = new Transform3D (); // Se crea la matriz de transformación
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velocidad, 0, 0, 0, 0, 0); // Valor numérico que se ira modificando en tiempo de ejecución
        interpolator = new RotPosPathInterpolator (value, t, t3d, knots, angulos, posiciones); // Se crea el interpolador, las figuras iran moviéndose
        interpolator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 200.0)); // Se le pone el entorno de activación
        interpolator.setEnable(true); // Se activa
        t.addChild(interpolator); // Se cuelga del grupo de transformación
        
        return t;
    }
    
    private BranchGroup importarModelo() {
        File file = new File(obj);
        String dir = file.getParentFile().getAbsolutePath();
	String path = file.getAbsolutePath();
        BranchGroup bg = new BranchGroup();
        Scene modelo = null;
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {
            archivo.setBasePath(dir);
            modelo = archivo.load(path);
        } catch (FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
        }
        bg.addChild ( modelo.getSceneGroup() );
        
        return bg;
    }
}
