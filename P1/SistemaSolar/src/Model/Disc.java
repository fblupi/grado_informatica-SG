
package Model;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;
import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;

public class Disc extends Shape3D {
    public Disc(float radioInterno, float radioExterno, int res, Appearance app) {
        setGeometry(generarGeometria(radioExterno,radioInterno,res));
        setAppearance(app);
    }

    private GeometryArray generarGeometria(float radioInterno, float radioExterno, int res) {
        // 1 poligono de dos contornos
        int[] contornosPorPoligono = {2};
        // En total, 2 contornos
        int[] verticesPorContorno = {res,res};
        // En total son 2*res vértices
        Point3f[] vertices = calcularVertices(radioExterno,radioInterno,res);
        // Se genera la geometría
        GeometryInfo gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY) ;
        gi.setCoordinates(vertices);
        gi.setContourCounts(contornosPorPoligono);
        gi.setStripCounts(verticesPorContorno);
        // Se generan las normales
        NormalGenerator nomGen = new NormalGenerator(Math.toRadians(30));
        nomGen.generateNormals(gi);
        // Se generan las cadenas de triángulos
        Stripifier cadenas = new Stripifier();
        cadenas.stripify(gi);
        // Se obtiene la geometría
        GeometryArray geometria = gi.getGeometryArray();
        return geometria;	
    }

    private Point3f[] calcularVertices(float radioInterno, float radioExterno, int res) {
        Point3f p;
        Transform3D rotacion;
        float alfa;
        Point3f[] coord = new Point3f[2*res];
        // Contorno exterior
        for(int i=0; i<res; i++) {
            alfa = (float)(i*2*Math.PI/res);
            p = new Point3f(radioExterno,0f,0f);
            rotacion = new Transform3D();
            rotacion.rotY(alfa);
            rotacion.transform(p);
            coord[i]=p;
        }
        // Contorno interior
        for(int i=0; i<res; i++) {
            alfa = (float)(-i*2*Math.PI/res);
            p = new Point3f(radioInterno,0f,0f);
            rotacion = new Transform3D();
            rotacion.rotY(alfa);
            rotacion.transform(p);
            coord[i+res]=p;
        }
        return coord;
    }
}
