package smartroads;

import java.awt.Color;
import smartroads.primitives.MyPoint;
import smartroads.visual.display.MyCanvasWindow;
import smartroads.visual.drawables.MyDrawableLine;
import smartroads.visual.drawables.MyDrawablePoint;
import smartroads.visual.drawables.MyDrawableRectangle;

/**
 *
 * @author Oleksiy
 */
public class MyStartClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {   
        MyDrawableRectangle dr=MyDrawableRectangle.initDrawableRect(100, 100, 50, 50, Color.red);
        //dr.setRotationVelocityDeg(1);
        //dr.rotateByDeg(dr.getCenter(), 90);
        dr.setVelocity(new MyPoint(20f,20f));
        MyDrawableLine dl = new MyDrawableLine(new MyDrawablePoint(-51f, -51f), new MyDrawablePoint(50f, 50f));
        dl.setVelocity(new MyPoint(1f,1f));
        MyCanvasWindow cw= new MyCanvasWindow(300, 300, dr);
        cw.startWindow();
    }
    
}
