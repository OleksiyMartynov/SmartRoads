package smartroads.visual.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import smartroads.primitives.MyPoint;

/**
 *
 * @author Oleksiy
 */
public class MyDrawablePoint extends MyPoint implements IMyDrawable
{
    private MyPoint velocity = new MyPoint(0, 0);
    private double rotationVelocityDeg =0;
    private Color color = new Color(255, 255, 255);
    public MyDrawablePoint(float x, float y)
    {
        super(x, y);
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(color);
        g.drawLine((int)getX(),(int)getY(),(int)getX(),(int)getY());
    }

    @Override
    public void update(int delta)
    {
        float ch = (float)delta/1000f;
        float newX = velocity.getX();
        float newY = velocity.getY();
        rotateByDeg(rotationVelocityDeg);
        translate(new MyPoint(newX, newY));
        
    }

    @Override
    public MyPoint getVelocity()
    {
        return velocity;
    }

    @Override
    public void setVelocity(MyPoint vel)
    {
        velocity=vel;
    }

    @Override
    public void setColor(Color c)
    {
        color=c;
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    @Override
    public void setRotationVelocityDeg(double angleStepDegrees)
    {
        rotationVelocityDeg= angleStepDegrees;
    }

    @Override
    public double getRotationVelocityDeg()
    {
        return rotationVelocityDeg;
    }
    
}
