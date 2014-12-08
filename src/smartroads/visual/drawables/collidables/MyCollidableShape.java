package smartroads.visual.drawables.collidables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import smartroads.helpers.MyMathHelper;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.base.MyDrawableLine;
import smartroads.visual.drawables.base.MyDrawablePoint;
import smartroads.visual.drawables.base.MyDrawableShape;
import smartroads.visual.drawables.base.MyDrawableWorld;

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
            MyDrawablePoint dp = new MyDrawablePoint(p.getX(), p.getY());
            dp.setColor(Color.RED);            
            MyDrawableWorld.getInstance().addDrawables(dp);
        
        });      
        
        return cps;
    }

    

    

    
    
}
