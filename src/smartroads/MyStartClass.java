package smartroads;


import smartroads.visual.display.MyCanvasWindow;
import smartroads.visual.drawables.MyDrawableWorld;


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
        
        MyDrawableWorld world = new MyDrawableWorld();
        MyCanvasWindow cw= new MyCanvasWindow(300, 300, world);
        cw.startWindow();
    }
    
}
