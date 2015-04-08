
package Model;

import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.WakeupOnAWTEvent;

public class Pick extends Behavior {

    private WakeupOnAWTEvent condition;
    private PickCanvas pickCanvas;
    private Canvas3D canvas;

    public Pick(Canvas3D aCanvas) {
        canvas = aCanvas;
        condition = new WakeupOnAWTEvent(MouseEvent.MOUSE_CLICKED);
    }

    public void initSearch(BranchGroup bg) {
        pickCanvas = new PickCanvas(canvas, bg);
        pickCanvas.setTolerance(0.0f); // Tolerancia mínima
        pickCanvas.setMode(PickInfo.PICK_GEOMETRY); // Se hará pick sobre geometrías
        pickCanvas.setFlags(PickInfo.SCENEGRAPHPATH | PickInfo.CLOSEST_INTERSECTION_POINT); // Se recoge el punto intersecado más cercano y el grafo desde el origen a este
        setEnable(true); // Se habilita el pick
    }

    @Override
    public void initialize() {
        setEnable(false); // El pick comienza deshabilitado esperando al initSearch
        wakeupOn(condition);
    }

    @Override
    public void processStimulus(Enumeration cond) {
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent mouse = (MouseEvent) e[0];
        pickCanvas.setShapeLocation(mouse);
        PickInfo pi = pickCanvas.pickClosest();
        if(pi != null) { // Si hay algo seleccionado
            SceneGraphPath sgp = pi.getSceneGraphPath();
            Astro a = (Astro)sgp.getNode(sgp.nodeCount()-2); // Se recoge el objeto astro seleccionado
            a.setRotationOnOff(); // Se para o vuelve a iniciar la rotación
        }
        wakeupOn(condition);
    }
    
}
