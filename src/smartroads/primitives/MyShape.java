package smartroads.primitives;

import java.util.List;

/**
 *
 * @author Oleksiy
 */
public abstract class MyShape implements IMyPhysical
{
    private List<MyLine> lines;
    private MyPoint pivot;
    private MyShape(){}
    public MyShape(List<MyLine> lines)
    {
        this.lines=lines;
        setPivotPoint(getCenter());
    }

    @Override
    public MyPoint getCenter()
    {
        MyPoint avgPoint = new MyPoint(0, 0);
        MyPoint countPoint = new MyPoint(0,0);
        lines.forEach(l->{
            avgPoint.translate(l.getCenter());
            countPoint.translate(new MyPoint(1,1));
        });
        return new MyPoint(avgPoint.getX()/countPoint.getX(),avgPoint.getY()/countPoint.getY());
    }
    
    public List<MyLine> getLines()
    {
        return lines;
    }
    public boolean intersects(MyShape other)
    {
        
        return lines.parallelStream().anyMatch((MyLine line)->{return other.getLines().stream().anyMatch((MyLine otherLine)->{return line.isIntersecting(otherLine);});});
    }
    @Override
    public void rotateByDeg(MyPoint pivotPoint, double angleDegrees)
    {
        lines.parallelStream().forEach((line)->{line.rotateByDeg(pivotPoint, angleDegrees);});
    }
    @Override
    public void translate(MyPoint vec)
    {
        lines.parallelStream().forEach((line)->{line.translate(vec);});
    }
     @Override
    public double getRotationDeg()
    {
        MyPoint sum = new MyPoint(0, 0);
        lines.parallelStream().forEach((MyLine l)->{sum.translate(new MyPoint((float)l.getRotationDeg(), 1));});
        return sum.getX()/sum.getY();
    }
    @Override
    public void setPivotPoint(MyPoint p)
    {
        pivot=p;
        lines.parallelStream().forEach(l->{l.setPivotPoint(p);});
    }

    @Override
    public void rotateByDeg(double angleDegrees)
    {
        rotateByDeg(pivot, angleDegrees);
    }
}
