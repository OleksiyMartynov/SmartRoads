package smartroads.visual.drawables.collidables;

import java.awt.Color;
import java.util.ArrayList;
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
