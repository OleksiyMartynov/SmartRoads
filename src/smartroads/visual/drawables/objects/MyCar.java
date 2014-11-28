package smartroads.visual.drawables.objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import smartroads.visual.drawables.MyDrawableLine;
import smartroads.visual.drawables.MyDrawablePoint;
import smartroads.visual.drawables.collidables.MyCollidableRectangle;

/**
 *
 * @author Oleksiy
 */
public class MyCar extends MyCollidableRectangle
{
    public final static float CAR_WIDTH = 10f;
    pubic final static float CAR_HEIGHT=20f;
    public MyCar(MyPoint startPoint)
    {
        
        super(new ArrayList<MyDrawableLine>(new MyDrawableLine{
        new MyDrawableLine(new MyDrawablePoint(startPoint.getX(), startPoint.getY()), new MyDrawablePoint(startPoint.getX()+CAR_WIDTH, startPoint.getY())),
        new MyDrawableLine(new MyDrawablePoint(startPoint.getX()+CAR_WIDTH, startPoint.getY()), new MyDrawablePoint(startPoint.getX()+CAR_WIDTH, startPoint.getY()+CAR_HEIGHT)),
        new MyDrawableLine(new MyDrawablePoint(startPoint.getX()+CAR_WIDTH, startPoint.getY()+CAR_HEIGHT), new MyDrawablePoint(startPoint.getX(), startPoint.getY()+CAR_HEIGHT)),
        new MyDrawableLine(new MyDrawablePoint(startPoint.getX(), startPoint.getY()+CAR_HEIGHT), new MyDrawablePoint(startPoint.getX(), startPoint.getY()))
        }), Color.BLUE);
        
        List<MyDrawableLine> drawableLines=new ArrayList<>();
        
    }
    
}
