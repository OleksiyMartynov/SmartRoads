package smartroads.primitives;

/**
 *
 * @author Oleksiy
 */

public class MyPoint implements IMyPhysical
{
    private float x,y;

    private MyPoint(){}
    
    public MyPoint(float x, float y)
    {
        this.x = x;
        this.y = y;
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
        x=x+other.getX();
        y=y+other.getY();
    }
    @Override
    public void rotateByDeg(MyPoint pivotPoint, double angleDegrees)
    {
        double angleInRadians = angleDegrees *(Math.PI/180);
        double cosTheta = Math.cos(angleInRadians);
        double sinTheta =Math.sin(angleInRadians);
        x=(int)(cosTheta*(x-pivotPoint.getX())-sinTheta*(y-pivotPoint.getY())+pivotPoint.getX());
        y=(int)(sinTheta*(x-pivotPoint.getX())+cosTheta*(y-pivotPoint.getY())+pivotPoint.getX());
    }
}
