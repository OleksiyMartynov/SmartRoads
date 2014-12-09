package smartroads.visual.display;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import smartroads.visual.drawables.base.IMyDrawable;

/**
 *
 * @author Oleksiy
 */
public class MyCanvasWindow extends Component
{
    int windowWidth, windowHeight;
    //Pixel[] pixels = new Pixel[0];
    IMyDrawable mainDrawable;
    boolean drawing =false;
    int miliPerFrame ;
    MouseListener mouseListener;
    JFrame frame;
    public MyCanvasWindow(int windowWidth, int windowHeight, IMyDrawable drawable) 
    {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;        
        mainDrawable=drawable;
        miliPerFrame=1000/29;
        //startWindow();        
        
    }
    public MouseListener getMouseListener()
    {
        return mouseListener;
    }

    public void setMouseListener(MouseListener mouseListener)
    {
        this.mouseListener = mouseListener;
        if(frame!=null)
        {
            frame.addMouseListener(mouseListener);
        }
    }
    
    public void setFPS(int frames)
    {
        miliPerFrame=1000/frames;
    }
    public void startWindow()
    {
        frame= new JFrame();
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(this);  
        if(mouseListener!=null)
        {
            frame.addMouseListener(mouseListener);
        }
        startUpdates();
    }
    @Override
    public void paint(Graphics graphics)
    {
        drawing=true;
        Graphics2D g2d=(Graphics2D)graphics;
       mainDrawable.draw(g2d);
       drawing=false;
    }
    private void startUpdates()
    {
         Timer timer = new Timer();
         timer.schedule(new TimerTask()
         {

             @Override
             public void run()
             {
                 mainDrawable.update(miliPerFrame);
                 if(!isDrawing())
                 {
                    repaint();
                 }
             }
         }, 0, miliPerFrame);
    }
    public boolean isDrawing()
    {
        return drawing;
    }
    
}
