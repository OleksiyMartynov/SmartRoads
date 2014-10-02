package smartroads.visual.display;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Oleksiy
 */
public class CanvasWindow extends Component
{
    int windowWidth, windowHeight;
    //Pixel[] pixels = new Pixel[0];
    public CanvasWindow(int windowWidth, int windowHeight) 
    {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        startWindow();
    }
    
    private void startWindow()
    {
        JFrame frame= new JFrame();
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(this);
    }
    @Override
    public void paint(Graphics graphics)
    {
        Graphics2D g2d=(Graphics2D)graphics;
        /*
        for(int i =0; i<pixels.length; i++)
        {
            graphics.setColor(new Color((int)pixels[i].b&0xff,(int)pixels[i].g&0xff, (int)pixels[i].r&0xff) );
            graphics.drawLine(pixels[i].x, pixels[i].y, pixels[i].x, pixels[i].y);
        }*/
    }
    /*
    @Override
    public void render(Pixel[] pixels) 
    {
        if(pixels!=null)
        {
        this.pixels = pixels;
        this.repaint();
        }
        else
        {
            System.out.println("null pixels");
        }
    }*/
}
