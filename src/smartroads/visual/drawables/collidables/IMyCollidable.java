package smartroads.visual.drawables.collidables;

import java.util.ArrayList;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.base.IMyDrawable;

/**
 *
 * @author Oleksiy
 */
public interface IMyCollidable extends IMyDrawable
{
    public ArrayList<MyPoint> isColliding(IMyCollidable other);
}
