
package SistemaSolar;

import GUI.ControlWindow;
import Model.Universo;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;

public class SistemaSolar {

    public static void main(String[] args) {
        // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
        Canvas3D canvasPlanta = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        Canvas3D canvasVariable = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        // Se le da el tamaño deseado
        canvasPlanta.setSize(300, 300);
        canvasVariable.setSize(900, 600);

        // Se crea el Universo con dicho Canvas3D
        Universo universe = new Universo(canvasPlanta, canvasVariable);
        // Se crea la GUI a partir del Canvas3D y del Universo
        ControlWindow controlWindow = new ControlWindow(canvasPlanta, canvasVariable, universe);
        // Se muestra la ventana principal de la aplicación
        controlWindow.showWindow ();
    }
  
}
