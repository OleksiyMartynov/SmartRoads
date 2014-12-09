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

    @Override
    public ArrayList<MyPoint> willCollide(IMyCollidable other)
    {        
        List<MyLine> nextFrameVectorLines = new ArrayList<>();
        getLines().forEach(l->{
            //todo fix rotation of the vector lines
            MyLine lineOne =new MyLine(l.getpOne().copy(), l.getpOne().copy().add(this.getVelocity()));
            lineOne.setPivotPoint(l.getpOne().copy());
            lineOne.rotateByDeg(getRotationDeg());
            nextFrameVectorLines.add(lineOne);
            //System.out.println("rv"+getRotationDeg());
            MyLine lineTwo = new MyLine(l.getpTwo().copy(), l.getpTwo().copy().add(this.getVelocity()));
            lineTwo.setPivotPoint(l.getpTwo().copy());
            lineTwo.rotateByDeg(getRotationDeg());
            nextFrameVectorLines.add(lineTwo);
        });
        nextFrameVectorLines.forEach(vl->{
            MyDrawableLine dvl = new MyDrawableLine(new MyDrawablePoint(vl.getpOne().getX(), vl.getpOne().getY()), new MyDrawablePoint(vl.getpTwo().getX(), vl.getpTwo().getY()));
            dvl.setColor(Color.LIGHT_GRAY);
            //MyDrawableWorld.getInstance().addDrawables(dvl);
        });
        
         ArrayList<MyPoint> cps=MyMathHelper.intersect(other.getNextFrameLines(), nextFrameVectorLines);
        return cps;
    }

    @Override
    public List<MyLine> getNextFrameLines()
    {
        List<MyLine> rl= getLines().stream().map(l->{return new MyLine(l.getpOne().copy(), l.getpTwo().copy());}).collect(Collectors.toList());
        rl.forEach(l->{l.translate(new MyPoint(getVelocity().getX(), getVelocity().getY()));l.rotateByDeg(getRotationVelocityDeg());});
        rl.forEach(vl->{
            MyDrawableLine dvl = new MyDrawableLine(new MyDrawablePoint(vl.getpOne().getX(), vl.getpOne().getY()), new MyDrawablePoint(vl.getpTwo().getX(), vl.getpTwo().getY()));
            dvl.setColor(Color.CYAN);
            //MyDrawableWorld.getInstance().addDrawables(dvl);
        });
        return rl;
    }   
}
