package smartroads.visual.drawables.collidables;

import java.awt.Color;
import java.util.ArrayList;
import smartroads.helpers.MyMathHelper;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.MyDrawablePoint;
import smartroads.visual.drawables.MyDrawableWorld;

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
    public ArrayList<MyPoint> isColliding(IMyCollidable other)
    {
       ArrayList<MyPoint> cps=MyMathHelper.intersect(this, other);
        cps.forEach(p->{
            if(p!=null)
        {
            MyDrawablePoint dp = new MyDrawablePoint(p.getX(), p.getY());
            dp.setColor(Color.RED);            
            MyDrawableWorld.getInstance().addDrawables(dp);
        }
        });
        
        
        return cps;
    }

    
    
    
    
}
