package smartroads;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import smartroads.primitives.MyPoint;
import smartroads.visual.display.MyCanvasWindow;
import smartroads.visual.drawables.base.MyDrawableWorld;
import sun.security.acl.WorldGroupImpl;


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
        
        MyDrawableWorld world = MyDrawableWorld.getInstance();
        MyCanvasWindow cw= new MyCanvasWindow(300, 300, world);
        cw.setMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent me)
            {
                world.spawnCar(new MyPoint(me.getX(), me.getY()));
                System.out.println("clicked at:"+me.getX()+" "+me.getY());
                       
            }

            @Override
            public void mousePressed(MouseEvent me)
            {
            }

            @Override
            public void mouseReleased(MouseEvent me)
            {
            }

            @Override
            public void mouseEntered(MouseEvent me)
            {
            }

            @Override
            public void mouseExited(MouseEvent me)
            {
            }
        });
        cw.startWindow();
    }
    
}
