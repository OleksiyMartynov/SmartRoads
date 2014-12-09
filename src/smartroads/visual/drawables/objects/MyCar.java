package smartroads.visual.drawables.objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.base.MyDrawableLine;
import smartroads.visual.drawables.base.MyDrawablePoint;
import smartroads.visual.drawables.base.MyDrawableWorld;
import smartroads.visual.drawables.collidables.IMyCollidable;
import smartroads.visual.drawables.bouncables.MyBouncyRectangle;

/**
 *
 * @author Oleksiy
 */
public class MyCar extends MyBouncyRectangle
{
    public final static float CAR_WIDTH = 10f;
    public final static float CAR_HEIGHT=20f;
    private final static float CAR_TURN_MAX=0.5f;
    private final static float CAR_TURN_ACC_PERFRAME=10f;
    private final static float CAR_VEL_ACC_PERFRAME=2f;
    private final static float CAR_DECELERATION_RATE=1f;    
    
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
    public ArrayList<MyPoint> isColliding(IMyCollidable other)
    {        
        ArrayList<MyPoint> outPoints = super.isColliding(other);
        if(outPoints.size()>0)
        {
            setRotationVelocityDeg(0);
        }
        outPoints.forEach(p->{            
            MyDrawablePoint dp = new MyDrawablePoint(p.getX(), p.getY());
            dp.setColor(Color.RED);            
            MyDrawableWorld.getInstance().addDrawables(dp);
            System.out.println("hit");
            //setVelocity(new MyPoint(0, 0));
            
        });        
        return  outPoints;
    }

    @Override
    public void update(int delta)
    {
        super.update(delta);
        //System.err.println("rv"+getRotationVelocityDeg());
        //System.err.println("r"+getRotationDeg());
    }
    
    
}
