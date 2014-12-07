package smartroads.primitives;

import java.util.ArrayList;
import java.util.List;

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
        double angleInRadians = angleDegrees *(Math.PI/180);
        double cosTheta = Math.cos(angleInRadians);
        double sinTheta =Math.sin(angleInRadians);
        double newX=(cosTheta*(x-pivotPoint.getX())-sinTheta*(y-pivotPoint.getY())+pivotPoint.getX());
        double newY=(sinTheta*(x-pivotPoint.getX())+cosTheta*(y-pivotPoint.getY())+pivotPoint.getY());
        x= (float)newX;
        y=(float)newY;
        rotation+=angleDegrees;
        //System.out.println("x"+x+" y"+y);
        //System.out.println(pivotPoint.toString());
        //System.out.println("r"+rotation);
        //System.out.println("angle"+angleDegrees);
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
    
}
