package smartroads.visual.drawables.collidables;

import smartroads.helpers.MyMathHelper;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.MyDrawablePoint;

/**
 *
 * @author Oleksiy
 */
public class MyCollidablePoint extends MyDrawablePoint implements IMyCollidable
{

    public MyCollidablePoint(float x, float y)
    {
        super(x, y);
    }

    @Override
    public MyPoint isColliding(IMyCollidable other)
    {
       MyPoint p =MyMathHelper.intersect(this, other);
        if(p!=null){System.out.println("collision at"+p.toString());}
        return p;
    }

    @Override
    public MyPoint willCollideAfterUpdate(IMyCollidable other)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}