package smartroads.visual.drawables;

import java.awt.Color;

/**
 *
 * @author Oleksiy
 */
public class MyColor
{
    private int red=0;
    private int green=0;
    private int blue=0;
    
    public MyColor()
    {
    }
    
    public MyColor(Color c)
    {
        red=c.getRed();
        green=c.getGreen();
        blue=c.getBlue();
    }
    public MyColor(int r, int g, int b)
    {
        red=r;
        green=g;
        blue=blue;
    }

    public int getRed()
    {
        return red;
    }

    public int getGreen()
    {
        return green;
    }

    public int getBlue()
    {
        return blue;
    }

    public void setRed(int red)
    {
        this.red = red;
    }

    public void setGreen(int green)
    {
        this.green = green;
    }

    public void setBlue(int blue)
    {
        this.blue = blue;
    }
    
    public void add(MyColor color)
    {
        this.red= (this.red+color.getRed())/2;
        this.green= (this.green+color.getGreen())/2;
        this.blue= (this.blue+color.getBlue())/2;
    }
    public void add(Color color)
    {
        this.red= (this.red+color.getRed())/2;
        this.green= (this.green+color.getGreen())/2;
        this.blue= (this.blue+color.getBlue())/2;
    }
    public Color toSystemColor()
    {
        return new Color(this.red,this.green,this.blue);
    }
}
