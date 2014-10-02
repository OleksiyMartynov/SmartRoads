package smartroads.primitives;

import java.util.List;

/**
 *
 * @author Oleksiy
 */
public abstract class MyShape implements IMyPhysical
{
    private List<MyLine> lines;
    private MyShape(){}
    public MyShape(List<MyLine> lines)
    {
        this.lines=lines;
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
}
