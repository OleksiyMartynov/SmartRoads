package smartroads.visual.drawables.objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.MyDrawableLine;
import smartroads.visual.drawables.MyDrawablePoint;
import smartroads.visual.drawables.MyDrawableWorld;
import smartroads.visual.drawables.collidables.IMyCollidable;
import smartroads.visual.drawables.collidables.MyCollidablePoint;
import smartroads.visual.drawables.collidables.MyCollidableRectangle;

/**
 *
 * @author Oleksiy
 */
public class MyCar extends MyCollidableRectangle
{
    public final static float CAR_WIDTH = 10f;
    public final static float CAR_HEIGHT=20f;
    private final static float CAR_TURN_MAX=0.5f;
    private final static float CAR_TURN_ACC_PERFRAME=10f;
    private final static float CAR_VEL_ACC_PERFRAME=1f;
    public MyCar(MyPoint startPoint)
    {        
        super(new ArrayList<MyDrawableLine>(Arrays.asList(new MyDrawableLine[]{
        new MyDrawableLine(new MyDrawablePoint(startPoint.getX(), startPoint.getY()), new MyDrawablePoint(startPoint.getX()+CAR_WIDTH, startPoint.getY())),
        new MyDrawableLine(new MyDrawablePoint(startPoint.getX()+CAR_WIDTH, startPoint.getY()), new MyDrawablePoint(startPoint.getX()+CAR_WIDTH, startPoint.getY()+CAR_HEIGHT)),
        new MyDrawableLine(new MyDrawablePoint(startPoint.getX()+CAR_WIDTH, startPoint.getY()+CAR_HEIGHT), new MyDrawablePoint(startPoint.getX(), startPoint.getY()+CAR_HEIGHT)),
        new MyDrawableLine(new MyDrawablePoint(startPoint.getX(), startPoint.getY()+CAR_HEIGHT), new MyDrawablePoint(startPoint.getX(), startPoint.getY()))
        })), Color.BLUE);
        setPivotPoint(new MyPoint(startPoint.getX()+CAR_WIDTH/2, startPoint.getY()+CAR_HEIGHT));
    }
    public void turnLeft()
    {
        setRotationVelocityDeg(-CAR_TURN_MAX);
    }
    public void turnRight()
    {
        setRotationVelocityDeg(CAR_TURN_MAX);
    }
    public void turnForward()
    {
        setRotationVelocityDeg(0);
    }
    public void pressGasPedal()
    {
        MyPoint nvel = new MyPoint(getVelocity().getX(),getVelocity().getY()+CAR_VEL_ACC_PERFRAME);
        setVelocity(nvel);
    }
    public void pressBrakePedal()
    {
        MyPoint nvel = new MyPoint(getVelocity().getX(),getVelocity().getY()-CAR_VEL_ACC_PERFRAME);
        setVelocity(nvel);
    }

    @Override
    public MyPoint isColliding(IMyCollidable other)
    {        
        MyPoint outPoint = super.isColliding(other);
        if(outPoint!=null)
        {
            //float ch = (float)delta/1000f;
            //the following reverses the previous update translations and rotations
            //maybe move this to onUpdate method
            //float newX = getVelocity().getX();
            //float newY = getVelocity().getY();
            //rotateByDeg(-getRotationVelocityDeg());
            //translate(new MyPoint(-newX, -newY));
            
            //setVelocity(new MyPoint(0,0));
            //setRotationVelocityDeg(0);
            MyDrawablePoint dp = new MyDrawablePoint(outPoint.getX(), outPoint.getY());
            dp.setColor(Color.BLACK);            
            MyDrawableWorld.getInstance().addDrawables(dp);
        }
        return  outPoint;
    }
    
    
}