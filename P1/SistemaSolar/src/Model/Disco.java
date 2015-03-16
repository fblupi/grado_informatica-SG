
package Model;

import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3f;

public class Disco extends Shape3D {
    public Disco(float radioInterno, float radioExterno, int res, Appearance app) {
        setGeometry(generarGeometria(radioExterno,radioInterno,res));
        setAppearance(app);
    }
    
    private IndexedGeometryArray generarGeometria(float radioInterno, float radioExterno, int res){
        Point3f[] vertices = calcularVertices(radioInterno, radioExterno, res);
        int[] indicesVertices = calcularIndicesVertices(res);
        Vector3f[] normales = calcularNormales(vertices, indicesVertices);
        int[] indicesNormales = calcularIndicesNormales(res);
        TexCoord2f[] texCoords = {
            new TexCoord2f(0f,0f),
            new TexCoord2f(0f,1f),
            new TexCoord2f(1f,0f),
            new TexCoord2f(1f,1f)            
        };
        int[] indicesTexCoords = calcularIndicesTexCoords(res);
        IndexedGeometryArray geometria = new IndexedTriangleArray(
                (vertices.length<normales.length?normales.length:vertices.length),
                GeometryArray.COORDINATES | GeometryArray.NORMALS | GeometryArray.TEXTURE_COORDINATE_2,
                indicesVertices.length);
        geometria.setCoordinates(0, vertices);
        geometria.setCoordinateIndices(0, indicesVertices);
        geometria.setNormals(0, normales);
        geometria.setNormalIndices(0, indicesNormales);
        geometria.setTextureCoordinates(0,0,texCoords);
        geometria.setTextureCoordinateIndices(0,0,indicesTexCoords);
        
        return geometria;
    }
    
    private Point3f[] calcularVertices(float radioInterno, float radioExterno, int res) {
        Point3f[] vertices = new Point3f[res*2];
        Point3f pInterno, pExterno;
        Transform3D rotacion;
        float alfa;
        for(int i=0; i<res; i++) {
            alfa = (float)(i*2*Math.PI/res);
            pInterno = new Point3f(radioInterno,0f,0f);
            pExterno = new Point3f(radioExterno,0f,0f);
            rotacion = new Transform3D();
            rotacion.rotY(alfa);
            rotacion.transform(pInterno);
            rotacion.transform(pExterno);
            vertices[i*2]=pInterno;
            vertices[i*2+1]=pExterno;
        }
        
        return vertices;
    }
    
    private int[] calcularIndicesVertices(int res) {
        int[] indicesVertices = new int[res*2*3];
        int cnt = 0, supIzda, supDcha, infIzda, infDcha;
        for(int i=0; i<res; i++) {
            infIzda = (i*2)%(res*2);
            infDcha = (i*2+1)%(res*2);
            supIzda = ((i+1)*2)%(res*2);
            supDcha = ((i+1)*2+1)%(res*2);
            indicesVertices[cnt] = infIzda;
            cnt++;
            indicesVertices[cnt] = infDcha;
            cnt++;
            indicesVertices[cnt] = supIzda;
            cnt++;
            indicesVertices[cnt] = infDcha;
            cnt++;
            indicesVertices[cnt] = supDcha;
            cnt++;
            indicesVertices[cnt] = supIzda;
            cnt++;
        }
        
        return indicesVertices;
    }
    
    private Vector3f[] calcularNormales(Point3f[] puntos, int[] triangulos) {
        Vector3f[] normales = new Vector3f[puntos.length];
        Vector3f p, q;
        for(int i=0; i<normales.length; i++) {
            p = new Vector3f(); 
            q = new Vector3f(); 
            normales[i] = new Vector3f();
            p.x = puntos[triangulos[i*3+1]].x - puntos[triangulos[i*3]].x;
            p.y = puntos[triangulos[i*3+1]].y - puntos[triangulos[i*3]].y;
            p.z = puntos[triangulos[i*3+1]].z - puntos[triangulos[i*3]].z;
            q.x = puntos[triangulos[i*3+2]].x - puntos[triangulos[i*3]].x;
            q.y = puntos[triangulos[i*3+2]].y - puntos[triangulos[i*3]].y;
            q.z = puntos[triangulos[i*3+2]].z - puntos[triangulos[i*3]].z;
            normales[i].cross(p, q);
            normales[i].normalize();
        }

        return normales;
    }
        
    private int[] calcularIndicesNormales(int res) {
        int[] indicesNormales = new int[res*3*2];
        for(int i=0; i<indicesNormales.length; i+=3) {
            indicesNormales[i] = i/3;
            indicesNormales[i+1] = i/3;
            indicesNormales[i+2] = i/3;
        }
        
        return indicesNormales;
    }
    
    private int[] calcularIndicesTexCoords(int res) {
        int[] indicesTexCoords = new int[res*3*2];
        for(int i=0; i<indicesTexCoords.length; i+=6) {
            if((i/6)%2==0) {
                indicesTexCoords[i] = 0;
                indicesTexCoords[i+1] = 1;
                indicesTexCoords[i+2] = 2;
                indicesTexCoords[i+3] = 1;
                indicesTexCoords[i+4] = 3;
                indicesTexCoords[i+5] = 2;
            } else {
                indicesTexCoords[i] = 2;
                indicesTexCoords[i+1] = 3;
                indicesTexCoords[i+2] = 0;
                indicesTexCoords[i+3] = 3;
                indicesTexCoords[i+4] = 1;
                indicesTexCoords[i+5] = 0;
            }
        }
        
        return indicesTexCoords;
    }
    
}
