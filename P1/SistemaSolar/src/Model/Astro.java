
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.util.ArrayList;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author FranciscoJavier
 */
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
        // Se crea la rama desde la que cuelga todo
        BranchGroup bg = new BranchGroup ();
        
        // Se crea la transformación para la rotación alrededor
        TransformGroup rotationAround = rotarAlrededor();
        
        // Se crea la transformación para la traslacion
        TransformGroup translation = trasladar();
        
        for(Astro astro : satelites) {
            BranchGroup bgSatelite = astro.dibujar();
            translation.addChild(bgSatelite);
        }
        
        // Se crea la transformación para la rotación
        TransformGroup rotation = rotar();
        
        // Se crea la rama desde la que cuelga todo
        BranchGroup figure = new BranchGroup ();

        // Se le dan permisos para poder intercambiar las figuras
        figure.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        figure.setCapability(Group.ALLOW_CHILDREN_WRITE);

        // Y le ponemos una figura
        Appearance ap = new Appearance();
        Texture aTexture = new TextureLoader (textura, null).getTexture();
        ap.setTexture (aTexture);
        figure.addChild (new Sphere (diametro/2, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 64, ap));
        
        rotation.addChild(figure);
        
        translation.addChild(rotation);
        
        rotationAround.addChild(translation);
        
        bg.addChild(rotationAround);

        return bg;
    }
    
    private TransformGroup rotar() {
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velRotacion, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotator = new RotationInterpolator (value, transform, yAxis, 0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 100.0));
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotator);
        return transform;
    }
    
    private TransformGroup rotarAlrededor() {
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velTraslacion, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotatorAround = new RotationInterpolator (value, transform, yAxis, 0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotatorAround.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 100.0));
        rotatorAround.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotatorAround);
        return transform;
    }

    private TransformGroup trasladar() {
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D translation = new Transform3D ();
        translation.setTranslation(new Vector3f(distancia,0,0) );
        transform.setTransform(translation);
        return transform;
    }
}
