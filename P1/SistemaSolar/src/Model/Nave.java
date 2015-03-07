
package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Nave {
    private ArrayList<Vector3f> posiciones;
    private ArrayList<Vector3f> angulos;
    
    public Nave() {
        
    }
    
    public void addPunto(Vector3f posicion, Vector3f angulo) {
        posiciones.add(posicion);
        angulos.add(angulo);
    }
    
    public void removePunto(int i) {
        posiciones.remove(i);
        angulos.remove(i);
    }
    
    public BranchGroup dibujar() {
        BranchGroup bg = new BranchGroup();
        
        TransformGroup transform = new TransformGroup ();
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D t3d = new Transform3D ();
        t3d.setScale(new Vector3d(100,100,100));
        transform.setTransform(t3d);
        
        transform.addChild(importarModelo());
        
        return bg;
    }
    
    private BranchGroup importarModelo() {
        BranchGroup bg = new BranchGroup();
        Scene modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {
            modelo = archivo.load ("models/nave/naveEspacial.obj");
        } catch (FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
        }
        bg.addChild ( modelo.getSceneGroup() );
        return bg;
    }
}
