package smartroads.visual.drawables.collidables;

import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.IMyDrawable;

/**
 *
 * @author Oleksiy
 */
public interface IMyCollidable extends IMyDrawable
{
    public MyPoint isColliding(IMyCollidable other);
    public MyPoint willCollideAfterUpdate(IMyCollidable other);
}
