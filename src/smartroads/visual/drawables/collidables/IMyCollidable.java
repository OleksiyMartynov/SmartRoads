package smartroads.visual.drawables.collidables;

import java.util.ArrayList;
import java.util.List;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.base.IMyDrawable;

/**
 *
 * @author Oleksiy
 */
public interface IMyCollidable extends IMyDrawable
{
    public ArrayList<MyPoint> isColliding(IMyCollidable other);
    public ArrayList<MyPoint> willCollide(IMyCollidable other);
    public List<MyLine> getNextFrameLines();
    
    public interface IMyAfterCollisionFunction
    {
        public void doThisOnUpdate(int delta);
    }
}
