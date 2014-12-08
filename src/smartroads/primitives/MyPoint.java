package smartroads.primitives;

import java.util.ArrayList;
import java.util.List;
import smartroads.helpers.MyMathHelper;

/**
 *
 * @author Oleksiy
 */

public class MyPoint implements IMyPhysical
{
    private float x,y;
    private MyPoint pivot;
    private double rotation=0;
    private MyPoint(){}
    
    public MyPoint(float x, float y)
    {
        this.x = x;
        this.y = y;
        
    }
    public MyPoint(float x, float y, MyPoint pivot)
    {
        this.x = x;
        this.y = y;
        this.pivot=pivot;
    }
    @Override
    public MyPoint getCenter()
    {
        return new MyPoint(x,y);
    }
    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }
    @Override
    public void translate(MyPoint other)
    {
        x+=other.getX();
        y+=other.getY();
    }
    @Override
    public void rotateByDeg(MyPoint pivotPoint, double angleDegrees)
    {
        /*double angleInRadians = angleDegrees *(Math.PI/180);
        double cosTheta = Math.cos(angleInRadians);
        double sinTheta =Math.sin(angleInRadians);
        double newX=(cosTheta*(x-pivotPoint.getX())-sinTheta*(y-pivotPoint.getY())+pivotPoint.getX());
        double newY=(sinTheta*(x-pivotPoint.getX())+cosTheta*(y-pivotPoint.getY())+pivotPoint.getY());*/
        double[] newPoints=MyMathHelper.rotateByDegree(pivotPoint.getX(), pivotPoint.getY(), x, y, angleDegrees);
        x= (float)newPoints[0];
        y=(float)newPoints[1];
        rotation+=angleDegrees;
    }

    @Override
    public double getRotationDeg()
    {
        return rotation;
    }

    @Override
    public void setPivotPoint(MyPoint p)
    {
        pivot=p;
    }

    @Override
    public void rotateByDeg(double angleDegrees)
    {
        if(pivot==null)
        {
            pivot=new MyPoint(x,y);
            //System.out.println("null pivot");
        }
        rotateByDeg(pivot, angleDegrees);
    }

    @Override
    public String toString()
    {
        return "MyPoint{" + "x=" + x + ", y=" + y + ", pivot=" + pivot + ", rotation=" + rotation + '}';
    }

    @Override
    public List<MyLine> getLines()
    {
        ArrayList<MyLine> list = new ArrayList<>();
        list.add(new MyLine(this.getCenter(), this.getCenter()));
        return list;
    }
    @Override
    public float getWeight()
    {
        return 1;
    }
    public MyPoint add(MyPoint p)
    {
        return new MyPoint(this.x+p.getX(), this.y+p.getY());
    }
    public MyPoint subtract(MyPoint p)
    {
        return new MyPoint(this.x-p.getX(), this.y-p.getY());
    }
    public MyPoint multiply(MyPoint p)
    {
        return new MyPoint(this.x*p.getX(), this.y*p.getY());
    }
    public MyPoint divide(MyPoint p)
    {
        return new MyPoint(this.x/p.getX(), this.y/p.getY());
    }
    public double dot(MyPoint p)
    {
        return x*p.getX()+y*p.getY();
    }
    public double magnitude()//aka length aka hypotenuse
    {
        return Math.sqrt((x*x)+(y*y));
    }
    public MyPoint normal()
    {
        return new MyPoint((float)(x/magnitude()), (float)(y/magnitude()));
    }
    
}
