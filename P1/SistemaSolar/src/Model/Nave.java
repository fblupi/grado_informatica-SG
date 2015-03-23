
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
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;

public class Nave {
    private String obj;
    private long velocidad;
    private float[] knots;
    private Point3f[] posiciones;
    private Quat4f[] angulos;
    private RotPosPathInterpolator interpolator;
    
    public Nave(String obj, int puntos, long velocidad) {
        this.obj = obj;
        this.velocidad = velocidad;
        knots = new float[puntos];
        posiciones = new Point3f[puntos];
        angulos = new Quat4f[puntos];
    }
    
    public void setPunto(int punto, Point3f posicion, Quat4f angulo, float knot) {
        if(punto<posiciones.length && punto>=0) {
            posiciones[punto] = posicion;
            angulos[punto] = angulo;
            knots[punto] = knot;
        }
    }
    
    public BranchGroup dibujar() {
        BranchGroup bg = new BranchGroup();  // Se crea la rama desde la que cuelga todo
        
        TransformGroup transform = mover(); // Se crea el nodo de transformación de traslación
        
        BranchGroup figure = new BranchGroup (); // Se crea la rama desde la que cuelga la geometría y apariencia de la nave
        
        figure.addChild(importarModelo()); // la figura se cuelga de la transformación
        
        transform.addChild(figure); // la figura se cuelga de la transformación
        bg.addChild(transform); // la transformación se cuelga del nodo devuelto
        
        return bg;
    }
    
        private TransformGroup mover() {
        TransformGroup transform = new TransformGroup (); // Se crea el nodo de transformación: Todo lo que cuelgue de él rotará
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); // Se le permite que se cambie en tiempo de ejecución
         
        Transform3D t3d = new Transform3D (); // Se crea la matriz de rotación
        
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velocidad, 0, 0, 0, 0, 0); // Valor numérico que se ira modificando en tiempo de ejecución
        interpolator = new RotPosPathInterpolator (value, transform, t3d, knots, angulos, posiciones); // Se crea el interpolador de rotación, las figuras iran rotando
        interpolator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 100.0)); // Se le pone el entorno de activación
        interpolator.setEnable(true); // Se activa
        
        transform.addChild(interpolator); // Se cuelga del grupo de transformación
        
        return transform;
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
