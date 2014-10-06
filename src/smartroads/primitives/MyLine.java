/*

 */

package smartroads.primitives;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleksiy
 */
public class MyLine implements IMyPhysical
{
    private MyPoint pOne, pTwo,pivot;
    
    private MyLine(){}
    public MyLine(MyPoint pOne, MyPoint pTwo)
    {
        this.pOne = pOne;
        this.pTwo = pTwo;
        //pivot = getCenter();
    }
    public MyPoint getpOne()
    {
        return pOne;
    }

    public MyPoint getpTwo()
    {
        return pTwo;
    }
    @Override
    public MyPoint getCenter()
    {
        float xS= (pTwo.getX()-pOne.getX())/2.0f+pOne.getX();
        float yS=(pTwo.getY()-pOne.getY())/2.0f+pOne.getY();
        MyPoint np =new MyPoint(xS, yS);
        //System.out.println(np.toString());
        return np;
    }
    public MyPoint getPointOfIntersection(MyLine other)
    {
        return intersect(pOne.getX(), pOne.getY(), pTwo.getX(), pTwo.getY(), other.pOne.getX(), other.pOne.getY(), other.pTwo.getX(), other.pTwo.getX());
    }
    public boolean isIntersecting(MyLine other)
    {
        return getPointOfIntersection(other) != null;
    }
        
    @Override
    public void rotateByDeg(MyPoint pivotPoint, double angleDegrees)
    {
        pOne.rotateByDeg(pivotPoint, angleDegrees);
        pTwo.rotateByDeg(pivotPoint, angleDegrees);
    }
    @Override
    public void translate(MyPoint vec)
    {
        pOne.translate(vec);
        pTwo.translate(vec);
    }

    @Override
    public double getRotationDeg()
    {
        return (pOne.getRotationDeg()+pTwo.getRotationDeg())/2.0;
    }
    
    @Override
    public void setPivotPoint(MyPoint p)
    {
        pivot=p;
        pOne.setPivotPoint(p);
        pTwo.setPivotPoint(p);
    }

    @Override
    public void rotateByDeg(double angleDegrees)
    {
        if(pivot==null)
        {
            pivot=getCenter();
        }
        rotateByDeg(pivot, angleDegrees);
    }
    
    @Override
    public List<MyLine> getLines()
    {
        ArrayList<MyLine> list = new ArrayList<>();
        list.add(this);
        return list;
    }
}
