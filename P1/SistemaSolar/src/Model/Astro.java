
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.util.ArrayList;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class Astro {
    
    private float diametro;
    private long velTraslacion;
    private long velRotacion;
    private float distancia;
    private boolean movimiento;
    private String textura;
    private ArrayList<Astro> satelites;
    private RotationInterpolator rotator; // El objeto que controla la rotación continua de la figura
    private RotationInterpolator rotatorAround;
    
    public Astro(float diametro, long velTraslacion, long velRotacion, float distancia, String textura) {
        this.diametro = diametro;
        this.velTraslacion = velTraslacion;
        this.velRotacion = velRotacion;
        this.distancia = distancia;
        this.movimiento = true;
        this.textura = textura;
        satelites = new ArrayList();
    }
    
    public void addSatelite(Astro astro) {
        satelites.add(astro);
    }
    
    public void removeSatelite(Astro astro) {
        satelites.remove(astro);
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
        
        TransformGroup rotation = rotar(); // Se crea la transformación para la rotación
        
        BranchGroup figure = new BranchGroup (); // Se crea la rama desde la que cuelga la geometría y apariencia del astro

        Appearance ap = new Appearance(); // Se crea una nueva apariencia
        Texture aTexture = new TextureLoader (textura, null).getTexture(); // Se carga la textura
        ap.setTexture (aTexture); // Se asigna la textura
        
        figure.addChild (new Sphere (diametro/2, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 64, ap)); // se crea la figura y se cuelga del nodo figura 
        
        rotation.addChild(figure); // la figura se cuelga de la rotación
        translation.addChild(rotation); // la rotación se cuelga de la traslación
        rotationAround.addChild(translation); // la traslación se cuelga de la rotación alrededor
        bg.addChild(rotationAround); // la rotación alrededor se cuelga del BranchGroup del planeta

        return bg;
    }
    
    private TransformGroup rotar() {
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
    
    private TransformGroup rotarAlrededor() {
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

    private TransformGroup trasladar() {
        TransformGroup transform = new TransformGroup (); // Se crea el nodo de transformación de traslación en el eje x
        
        Transform3D t3d = new Transform3D (); // Se crea la matriz de rotación
        t3d.setTranslation(new Vector3f(distancia,0,0) ); // Se define la traslación
        transform.setTransform(t3d); // Se aplica la traslación al nodo de transformación
        
        return transform;
    }
}
