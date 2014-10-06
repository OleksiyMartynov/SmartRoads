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
        getLines().forEach(l->{other.getLines().forEach(l2->{MyMathHelper.intersect(lOnePOneX, lOnePOneY, lOnePTwoX, lOnePTwoY, lTwoPOneX, lTwoPOneY, lTwoPTwoX, lTwoPTwoY)});});
    }

    @Override
    public MyPoint willCollideAfterUpdate(IMyCollidable other)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
