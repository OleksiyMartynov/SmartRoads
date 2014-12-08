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
        float xS= (pTwo.getX()-pOne.getX())/2.0f+pOne.getX();
        float yS=(pTwo.getY()-pOne.getY())/2.0f+pOne.getY();
        pivot =new MyPoint(xS, yS);
        this.pOne.setPivotPoint(pivot);
        this.pTwo.setPivotPoint(pivot);
    }
    public MyPoint getpOne()
    {
        return pOne;
    }

    public MyPoint getpTwo()
    {
        return pTwo;
    }
    public double getLength()
    {
        return this.pOne.subtract(this.pTwo).magnitude();
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
    @Override
    public float getWeight()
    {
        float perimiter =0;
        for(MyLine l : getLines())
        {
            perimiter+=l.getLength();
        }
        return perimiter;
    }
}
