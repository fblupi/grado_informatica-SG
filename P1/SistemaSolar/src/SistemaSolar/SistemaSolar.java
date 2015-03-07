
package SistemaSolar;

import GUI.ControlWindow;
import Model.Universo;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;

/**
 *
 * @author fvelasco
 */
public class SistemaSolar {

  public static void main(String[] args) {

    // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
    Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
    // Se le da el tamaño deseado
    canvas.setSize(900, 650);
    
    // Se crea el Universo con dicho Canvas3D
    Universo universe = new Universo (canvas);
    // Se crea la GUI a partir del Canvas3D y del Universo
    ControlWindow controlWindow = new ControlWindow (canvas, universe);
    // Se muestra la ventana principal de la aplicación
    controlWindow.showWindow ();
  }
  
}
