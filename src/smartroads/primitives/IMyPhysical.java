
package smartroads.primitives;

import java.util.List;

/**
 *
 * @author Oleksiy
 */
public interface IMyPhysical
{
    public void translate(MyPoint vec);
    public void rotateByDeg(MyPoint pivotPoint, double angleDegrees);
    public double getRotationDeg();
    public MyPoint getCenter();
    public void setPivotPoint(MyPoint p);
    public void rotateByDeg(double angleDegrees);
    public List<MyLine> getLines();
}
