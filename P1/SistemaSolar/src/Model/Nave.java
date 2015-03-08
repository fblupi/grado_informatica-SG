
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
import javax.vecmath.Vector3f;

public class Nave {
    private ArrayList<Vector3f> posiciones;
    private ArrayList<Vector3f> angulos;
    
    public Nave() {
        posiciones = new ArrayList();
        angulos = new ArrayList();
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
        BranchGroup bg = new BranchGroup();  // Se crea la rama desde la que cuelga todo
        
        TransformGroup transform = new TransformGroup (); // Se crea el nodo de transformación de traslación
        
        Transform3D t3d = new Transform3D (); // Se crea la matriz de rotación
        t3d.setTranslation(new Vector3f(10,10,10) );  // Se define la traslación
        transform.setTransform(t3d); // Se aplica la traslación al nodo de transformación
        
        transform.addChild(importarModelo()); // la figura se cuelga de la transformación
        bg.addChild(transform); // la transformación se cuelga del nodo devuelto
        
        return bg;
    }
    
    private BranchGroup importarModelo() {
        BranchGroup bg = new BranchGroup();
        Scene modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {
            modelo = archivo.load ("models/naveEspacial/naveEspacial.obj");
        } catch (FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
            System.err.println (e);
            System.exit(1);
        }
        bg.addChild ( modelo.getSceneGroup() );
        return bg;
    }
}
