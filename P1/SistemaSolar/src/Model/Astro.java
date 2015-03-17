
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.util.ArrayList;
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
import javax.vecmath.Vector3f;

public class Astro {
    
    protected float diametro;
    protected long velTraslacion;
    protected long velRotacion;
    protected float distancia;
    protected boolean movimiento;
    protected String texturePath;
    protected Texture texture;
    protected TextureAttributes textureAttributes;
    protected Material material;
    protected Appearance ap;
    protected ArrayList<Astro> satelites;    
    protected ArrayList<Anillo> anillos;
    protected RotationInterpolator rotator; // El objeto que controla la rotación continua de la figura
    protected RotationInterpolator rotatorAround;
    
    public Astro(float diametro, long velTraslacion, long velRotacion, float distancia, String texturePath, Material material) {
        this.diametro = diametro;
        this.velTraslacion = velTraslacion;
        this.velRotacion = velRotacion;
        this.distancia = distancia;
        this.movimiento = true;
        this.texturePath = texturePath;
        this.material = material;
        satelites = new ArrayList();
        anillos = new ArrayList();
        // creación de la apariencia: textura + material
        texture = new TextureLoader (this.texturePath, null).getTexture();
        textureAttributes = new TextureAttributes(); 
        textureAttributes.setTextureMode(TextureAttributes.MODULATE);
        ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(textureAttributes);
        ap.setMaterial(this.material);
    }
    
    public void addSatelite(Astro astro) {
        satelites.add(astro);
    }
    
    public void removeSatelite(Astro astro) {
        satelites.remove(astro);
    }
    
    public void addAnillo(Anillo anillo) {
        anillos.add(anillo);
    }
    
    public void removeAnillo(Anillo anillo) {
        anillos.remove(anillo);
    }
    
    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
    }
    
    public BranchGroup dibujar() {
        BranchGroup bg = new BranchGroup (); // Se crea la rama desde la que cuelga todo
        
        TransformGroup rotationAround = rotarAlrededor(); // Se crea la transformación para la rotación alrededor del sol o planeta
        
        TransformGroup translation = trasladar(); // Se crea la transformación para la traslacion
        
        // Se recorren todos los satelites y colgamos del nodo al que se le 
        // aplica la rotación alrededor y la traslación
        for(Astro astro : satelites) {
            BranchGroup bgSatelite = astro.dibujar();
            translation.addChild(bgSatelite);
        }
        
        for(Anillo anillo : anillos) {
            BranchGroup bgAnillo = anillo.dibujar();
            translation.addChild(bgAnillo);
        }
        
        TransformGroup rotation = rotar(); // Se crea la transformación para la rotación
        
        BranchGroup figure = new BranchGroup (); // Se crea la rama desde la que cuelga la geometría y apariencia del astro
        
        figure.addChild (new Sphere (diametro/2, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 64, ap)); // se crea la figura y se cuelga del nodo figura 
        
        rotation.addChild(figure); // la figura se cuelga de la rotación
        translation.addChild(rotation); // la rotación se cuelga de la traslación
        rotationAround.addChild(translation); // la traslación se cuelga de la rotación alrededor
        bg.addChild(rotationAround); // la rotación alrededor se cuelga del BranchGroup del planeta

        return bg;
    }
    
    protected TransformGroup rotar() {
        TransformGroup transform = new TransformGroup (); // Se crea el nodo de transformación: Todo lo que cuelgue de él rotará
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); // Se le permite que se cambie en tiempo de ejecución
         
        Transform3D t3d = new Transform3D (); // Se crea la matriz de rotación
        
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velRotacion, 0, 0, 0, 0, 0); // Valor numérico que se ira modificando en tiempo de ejecución
        rotator = new RotationInterpolator (value, transform, t3d, 0.0f, (float) Math.PI*2.0f); // Se crea el interpolador de rotación, las figuras iran rotando
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 100.0)); // Se le pone el entorno de activación
        rotator.setEnable(true); // Se activa
        
        transform.addChild(rotator); // Se cuelga del grupo de transformación
        
        return transform;
    }
    
    protected TransformGroup rotarAlrededor() {
        TransformGroup transform = new TransformGroup (); // Se crea el nodo de transformación: Todo lo que cuelgue de él rotará
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); // Se le permite que se cambie en tiempo de ejecución
        
        
        Transform3D t3d = new Transform3D (); // Se crea la matriz de rotación
        
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velTraslacion, 0, 0, 0, 0, 0); // Valor numérico que se ira modificando en tiempo de ejecución
        rotatorAround = new RotationInterpolator (value, transform, t3d, 0.0f, (float) Math.PI*2.0f); // Se crea el interpolador de rotación, las figuras iran rotando
        rotatorAround.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 100.0)); // Se le pone el entorno de activación
        rotatorAround.setEnable(true); // Se activa
        
        transform.addChild(rotatorAround); // Se cuelga del grupo de transformación
        
        return transform;
    }

    protected TransformGroup trasladar() {
        TransformGroup transform = new TransformGroup (); // Se crea el nodo de transformación de traslación en el eje x
        
        Transform3D t3d = new Transform3D (); // Se crea la matriz de rotación
        t3d.setTranslation(new Vector3f(distancia,0,0) ); // Se define la traslación
        transform.setTransform(t3d); // Se aplica la traslación al nodo de transformación
        
        return transform;
    }
}
