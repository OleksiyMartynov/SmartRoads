package smartroads.visual.drawables.collidables;

import java.awt.Color;
import java.util.List;
import smartroads.helpers.MyMathHelper;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.MyDrawableLine;
import smartroads.visual.drawables.MyDrawableShape;

/**
 *
 * @author Oleksiy
 */
public class MyCollidableShape extends MyDrawableShape implements IMyCollidable
{

    public MyCollidableShape(List<MyDrawableLine> drawableLines, Color c)
    {
        super(drawableLines, c);
    }

    @Override
    public MyPoint isColliding(IMyCollidable other)
    {
        MyPoint p =MyMathHelper.intersect(this, other);
        //if(p!=null){System.out.println("collision at"+p.toString());}
        return p;
    }

    @Override
    public MyPoint willCollideAfterUpdate(IMyCollidable other)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
