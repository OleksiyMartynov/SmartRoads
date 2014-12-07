package smartroads.visual.drawables.collidables;

import java.awt.Color;
import smartroads.helpers.MyMathHelper;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.MyDrawableLine;
import smartroads.visual.drawables.MyDrawablePoint;
import smartroads.visual.drawables.MyDrawableWorld;

/**
 *
 * @author Oleksiy
 */
public class MyCollidableLine extends MyDrawableLine implements IMyCollidable
{

    public MyCollidableLine(MyDrawablePoint pOne, MyDrawablePoint pTwo)
    {
        super(pOne, pTwo);
    }

    @Override
    public MyPoint isColliding(IMyCollidable other)
    {
        MyPoint p =MyMathHelper.intersect(this, other);
        if(p!=null)
        {
            MyDrawablePoint dp = new MyDrawablePoint(p.getX(), p.getY());
            dp.setColor(Color.BLACK);            
            MyDrawableWorld.getInstance().addDrawables(dp);
        }
        return p;
    }

    @Override
    public MyPoint willCollideAfterUpdate(IMyCollidable other)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
