package smartroads.visual.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;

/**
 *
 * @author Oleksiy
 */
public class MyDrawableLine extends MyLine implements IMyDrawable
{
    MyDrawablePoint pDrOne,pDrTwo;
    private Color color = new Color(255, 255, 255);
    
    private MyDrawableLine(MyPoint pOne, MyPoint pTwo)
    {
        super(pOne, pTwo);
    }
    public MyDrawableLine(MyDrawablePoint pOne, MyDrawablePoint pTwo)
    {
        super(pOne, pTwo);
        pDrOne=pOne;
        pDrTwo=pTwo;
    }
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(color);
        g.drawLine((int)pDrOne.getX(),(int)pDrOne.getY(),(int)pDrTwo.getX(),(int)pDrTwo.getY());
    }

    @Override
    public void update(double delta)
    {
        pDrOne.update(delta);
        pDrTwo.update(delta);
    }

    @Override
    public MyPoint getVelocity()
    {
        if(pDrOne.getVelocity()!=pDrTwo.getVelocity())
        {
            System.out.println("Point velocities of line dont match! Check logic! Will return avg vel");
            return new MyPoint((pDrOne.getVelocity().getX()+pDrTwo.getVelocity().getX())/2f, (pDrOne.getVelocity().getY()+pDrTwo.getVelocity().getY())/2f);
        }
        return pDrOne.getVelocity();
    }

    @Override
    public void setVelocity(MyPoint vel)
    {
        pDrOne.setVelocity(vel);
        pDrTwo.setVelocity(vel);
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
    
}
