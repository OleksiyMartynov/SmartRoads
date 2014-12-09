package smartroads.visual.drawables.collidables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import smartroads.helpers.MyMathHelper;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.base.MyDrawableLine;
import smartroads.visual.drawables.base.MyDrawablePoint;
import smartroads.visual.drawables.base.MyDrawableWorld;

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
            dp.setColor(Color.RED);            
            MyDrawableWorld.getInstance().addDrawables(dp);
        }
        });    
        return cps;
    }
       
    @Override
    public ArrayList<MyPoint> willCollide(IMyCollidable other)
    {
        List<MyLine> nextFrameVectorLines = new ArrayList<>();
        getLines().forEach(l->{
            nextFrameVectorLines.add(new MyLine(l.getpOne(), this.getVelocity()));
            nextFrameVectorLines.add(new MyLine(l.getpTwo(), this.getVelocity()));
        });
         ArrayList<MyPoint> cps=MyMathHelper.intersect(other.getNextFrameLines(), nextFrameVectorLines);
        return cps;
    }

    @Override
    public List<MyLine> getNextFrameLines()
    {
        List<MyLine> rl= getLines().stream().map(l->{return new MyLine(l.getpOne().copy(), l.getpTwo().copy());}).collect(Collectors.toList());
        rl.forEach(l->{l.rotateByDeg(getRotationVelocityDeg());l.translate(new MyPoint(getVelocity().getX(), getVelocity().getY()));});
        return rl;
    }
}
