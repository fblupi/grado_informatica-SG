
package Model;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

public class Anillo extends BranchGroup {
    
    private float radioInterno;
    private float radioExterno;
    private long velRotacion;
    private Texture texture;
    private TextureAttributes textureAttributes;
    private Material material;
    private Appearance ap;
    private RotationInterpolator rotator; // El objeto que controla la rotación continua de la figura
    private TransformGroup rotation;
    
    public Anillo(float radioInterno, float radioExterno, long velRotacion, String texturePath, Material material) {
        this.setPickable(false);
        this.radioInterno = radioInterno;
        this.radioExterno = radioExterno;
        this.velRotacion = velRotacion;
        this.material = material;
        
        // creación de la apariencia: textura + material
        texture = new TextureLoader (texturePath, null).getTexture();
        textureAttributes = new TextureAttributes(); 
        textureAttributes.setTextureMode(TextureAttributes.MODULATE);
        ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(textureAttributes);
        ap.setMaterial(this.material);
        
        // Se cuelgan del grafo las transformaciones y la figura
        rotation = rotar(); // Se crea la transformación para la rotación
        BranchGroup figure = new BranchGroup (); // Se crea la rama desde la que cuelga la geometría y apariencia del astro
        figure.addChild(new Torus(radioInterno,radioExterno,64,2,ap)); // Anillo Torus con res2=2
        rotation.addChild(figure); // la figura se cuelga de la rotación
        
        this.addChild(rotation); // la rotación se cuelga del BranchGroup del planeta
    }
    
    private TransformGroup rotar() {
        TransformGroup t = new TransformGroup (); // Se crea el nodo de transformación: Todo lo que cuelgue de él rotará
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); // Se le permite que se cambie en tiempo de ejecución
        Transform3D t3d = new Transform3D (); // Se crea la matriz de rotación
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velRotacion, 0, 0, 0, 0, 0); // Valor numérico que se ira modificando en tiempo de ejecución
        rotator = new RotationInterpolator (value, t, t3d, 0.0f, (float) Math.PI*2.0f); // Se crea el interpolador de rotación, las figuras iran rotando
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 200.0)); // Se le pone el entorno de activación
        rotator.setEnable(true); // Se activa
        t.addChild(rotator); // Se cuelga del grupo de transformación
        
        return t;
    }
    
}
