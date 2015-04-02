
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

public class Sol extends Astro {
    private PointLight luz;
            
    public Sol(float diametro, long velRotacion, String textura) {
        super(
            diametro, 
            0l, // vel rotación alrededor
            velRotacion, 
            0f, // distancia
            textura,
            new Material(
                new Color3f (1f, 1f, 1f), 
                new Color3f (1f, 1f, 1f), 
                new Color3f (1f, 1f, 1f), 
                new Color3f (1f, 1f, 1f), 
                100f
            )
        );
        // Se crea la luz
        
        luz = new PointLight (
            new Color3f (0.9f, 0.9f, 0.9f), 
            new Point3f (0.0f, 0.0f, 0.0f),
            new Point3f (1.0f, 0.0f, 0.0f)
        );
        luz.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 120.0));
        luz.setEnable (true);
        
        // Se cuelgan del grafo las transformaciones y la figura
        rotation = rotar(); // Se crea la transformación para la rotación
        BranchGroup figure = new BranchGroup (); // Se crea la rama desde la que cuelga la geometría y apariencia del astro
        figure.addChild (new Sphere (diametro/2, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 64, ap)); // se crea la figura y se cuelga del nodo figura
        rotation.addChild(figure); // la figura se cuelga de la rotación
        
        this.addChild(rotation); // la rotación alrededor se cuelga del BranchGroup del planeta
        this.addChild(luz);
    }
    
}
