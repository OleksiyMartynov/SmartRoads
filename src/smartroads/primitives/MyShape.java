package smartroads.primitives;

import java.util.List;

/**
 *
 * @author Oleksiy
 */
public abstract class MyShape
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
    public void rotateByDeg(MyPoint pivotPoint, double angleDegrees)
    {
        lines.forEach((line)->{line.rotateByDeg(pivotPoint, angleDegrees);});
    }
    public void translate(MyPoint vec)
    {
        lines.forEach((line)->{line.translate(vec);});
    }
}
