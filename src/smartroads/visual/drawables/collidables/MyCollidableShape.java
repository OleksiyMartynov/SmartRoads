package smartroads.visual.drawables.collidables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import smartroads.helpers.MyMathHelper;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.MyDrawableLine;
import smartroads.visual.drawables.MyDrawablePoint;
import smartroads.visual.drawables.MyDrawableShape;
import smartroads.visual.drawables.MyDrawableWorld;

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
    public ArrayList<MyPoint> isColliding(IMyCollidable other)
    {
        ArrayList<MyPoint> cps=MyMathHelper.intersect(this, other);
        cps.forEach(p->{
            if(p!=null)
        {
            MyDrawablePoint dp = new MyDrawablePoint(p.getX(), p.getY());
            dp.setColor(Color.BLACK);            
            MyDrawableWorld.getInstance().addDrawables(dp);
        }
        });      
        
        return cps;
    }

    
    
}
