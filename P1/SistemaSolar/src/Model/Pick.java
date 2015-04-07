
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
        System.out.println("creado");
    }

    public void initSearch(BranchGroup bg) {
        pickCanvas = new PickCanvas(canvas, bg);
        pickCanvas.setTolerance(0.0f);
        pickCanvas.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvas.setFlags(PickInfo.SCENEGRAPHPATH | PickInfo.CLOSEST_INTERSECTION_POINT);
        setEnable(true);
        System.out.println("initSearch");
    }

    @Override
    public void initialize() {
        setEnable(false);
        wakeupOn(condition);
        System.out.println("inicializado");
    }

    @Override
    public void processStimulus(Enumeration cond) {
        System.out.println("estímulo");
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent mouse = (MouseEvent) e[0];
        pickCanvas.setShapeLocation(mouse);
        PickInfo pi = pickCanvas.pickClosest();
        if(pi != null) {
            SceneGraphPath sgp = pi.getSceneGraphPath();
            System.out.println(sgp.toString());
        }
        // Selecciona el Astro y realiza el método setRotationOnOff()
        
        wakeupOn(condition);
    }
}
