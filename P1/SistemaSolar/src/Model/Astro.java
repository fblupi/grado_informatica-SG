
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class Astro extends BranchGroup {
    
    protected boolean movimiento;
    protected float diametro;
    protected long velTraslacion;
    protected long velRotacion;
    protected float distancia;
    protected Texture texture;
    protected TextureAttributes textureAttributes;
    protected Material material;
    protected Appearance ap;
    protected RotationInterpolator rotator;
    protected RotationInterpolator rotatorAround;
    protected TransformGroup rotationAround;
    protected TransformGroup rotation;
    protected TransformGroup translation;
    
    public Astro(float diametro, long velTraslacion, long velRotacion, float distancia, String texturePath, Material material) {
        this.setPickable(true);
        this.setCapability(Node.ENABLE_PICK_REPORTING);
        
        this.movimiento = true;
        this.diametro = diametro;
        this.velTraslacion = velTraslacion;
        this.velRotacion = velRotacion;
        this.distancia = distancia;
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
        rotationAround = rotarAlrededor(); // Se crea la transformación para la rotación alrededor del sol o planeta
        translation = trasladar(); // Se crea la transformación para la traslación
        rotation = rotar(); // Se crea la transformación para la rotación
        BranchGroup figure = new BranchGroup(); // Se crea la rama desde la que cuelga la geometría y apariencia del astro
        
        figure.addChild(new Sphere (diametro/2, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 64, ap)); // se crea la figura y se cuelga del nodo figura 
        rotation.addChild(figure); // la figura se cuelga de la rotación
        translation.addChild(rotation); // la rotación se cuelga de la traslación
        rotationAround.addChild(translation); // la traslación se cuelga de la rotación alrededor
        this.addChild(rotationAround); // la rotación alrededor se cuelga del BranchGroup del planeta
    }
    
    // Este constructor será llamado desde la subclase Sol
    public Astro(float diametro, long velRotacion, String texturePath) {
        this.setPickable(true);
        this.setCapability(Node.ENABLE_PICK_REPORTING);
        
        this.diametro = diametro;
        this.velTraslacion = 0l;
        this.velRotacion = velRotacion;
        this.distancia = 0.0f;
        this.material = new Material(
            new Color3f (1f, 1f, 1f), 
            new Color3f (1f, 1f, 1f), 
            new Color3f (1f, 1f, 1f), 
            new Color3f (1f, 1f, 1f), 
            100f
        );
        
        // creación de la apariencia: textura + material
        texture = new TextureLoader (texturePath, null).getTexture();
        textureAttributes = new TextureAttributes(); 
        textureAttributes.setTextureMode(TextureAttributes.MODULATE);
        ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(textureAttributes);
        ap.setMaterial(this.material);
    }
    
    public void addSatelite(Astro astro) {
        translation.addChild(astro);
    }
    
    public void addAnillo(Anillo anillo) {
        translation.addChild(anillo);
    }
    
    public void addCamara(Camara camara) {
        rotation.addChild(camara);
    }
    
    public void setRotationOnOff() {
        movimiento = !movimiento; // Si se mueve se para, si no se hace mover
        rotator.setEnable(movimiento); // Se habilita o deshabilita el interpolador
    }
    
    protected TransformGroup rotar() {
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
    
    protected TransformGroup rotarAlrededor() {
        TransformGroup t = new TransformGroup (); // Se crea el nodo de transformación: Todo lo que cuelgue de él rotará
        t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); // Se le permite que se cambie en tiempo de ejecución
        Transform3D t3d = new Transform3D (); // Se crea la matriz de rotación
        Alpha value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, velTraslacion, 0, 0, 0, 0, 0); // Valor numérico que se ira modificando en tiempo de ejecución
        rotatorAround = new RotationInterpolator (value, t, t3d, 0.0f, (float) Math.PI*2.0f); // Se crea el interpolador de rotación, las figuras iran rotando
        rotatorAround.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 200.0)); // Se le pone el entorno de activación
        rotatorAround.setEnable(true); // Se activa
        t.addChild(rotatorAround); // Se cuelga del grupo de transformación
        
        return t;
    }

    protected TransformGroup trasladar() {
        TransformGroup t = new TransformGroup (); // Se crea el nodo de transformación de traslación en el eje x
        Transform3D t3d = new Transform3D (); // Se crea la matriz de transformación
        t3d.setTranslation(new Vector3f(distancia,0,0) ); // Se define la traslación
        t.setTransform(t3d); // Se aplica la traslación al nodo de transformación
        
        return t;
    }
    
}
