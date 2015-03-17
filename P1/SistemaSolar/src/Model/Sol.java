
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

public class Sol extends Astro {
    private PointLight luz;
    public Sol(float diametro, long velRotacion, String textura) {
        super(
            diametro, 
            0l, 
            velRotacion, 
            0f, 
            textura,
            new Material(
                new Color3f (1f, 1f, 1f), 
                new Color3f (1f, 1f, 1f), 
                new Color3f (1f, 1f, 1f), 
                new Color3f (1f, 1f, 1f), 
                100f
            )
        );
        luz = new PointLight (
            new Color3f (0.9f, 0.9f, 0.9f), 
            new Point3f (0.0f, 0.0f, 0.0f),
            new Point3f (1.0f, 0.0f, 0.0f)
        );
        luz.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 120.0));
        luz.setEnable (true);
    }
    
    @Override
    public BranchGroup dibujar() {
        BranchGroup bg = new BranchGroup (); // Se crea la rama desde la que cuelga todo
        
        // Se recorren todos los satelites y colgamos del nodo al que se le 
        // aplica la rotación alrededor y la traslación
        for(Astro astro : satelites) {
            BranchGroup bgSatelite = astro.dibujar();
            bg.addChild(bgSatelite);
        }
        
        TransformGroup rotation = rotar(); // Se crea la transformación para la rotación
        
        BranchGroup figure = new BranchGroup (); // Se crea la rama desde la que cuelga la geometría y apariencia del astro
        
        figure.addChild (new Sphere (diametro/2, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 64, ap)); // se crea la figura y se cuelga del nodo figura 
        
        rotation.addChild(figure); // la figura se cuelga de la rotación
        
        bg.addChild(rotation); // la rotación alrededor se cuelga del BranchGroup del planeta
        bg.addChild(luz);

        return bg;
    }
    
}
