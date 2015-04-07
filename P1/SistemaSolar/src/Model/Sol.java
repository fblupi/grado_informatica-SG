
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
        luz.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 200.0));
        luz.setEnable (true);
        
        this.addChild(luz);
    }
    
}
