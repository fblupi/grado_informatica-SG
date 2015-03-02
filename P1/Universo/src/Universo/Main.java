/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universo;

import Modelo.Universo;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
/**
 *
 * @author MiKe
 */
public class Main {
    public static void main(String[] args){
         // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
        Canvas3D canvas = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        // Se le da el tamaño deseado
        canvas.setSize(800, 600);

        // Se crea el Universo con dicho Canvas3D
        Universo universo = new Universo (canvas);
    }
}
