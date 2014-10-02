/*

 */

package smartroads.primitives;

/**
 *
 * @author Oleksiy
 */
public class MyLine
{
    private MyPoint pOne, pTwo;
    private MyLine(){}
    public MyLine(MyPoint pOne, MyPoint pTwo)
    {
        this.pOne = pOne;
        this.pTwo = pTwo;
    }
    public void rotateByDeg(MyPoint pivotPoint, double angleDegrees)
    {
        pOne.rotateByDeg(pivotPoint, angleDegrees);
        pTwo.rotateByDeg(pivotPoint, angleDegrees);
    }

    public MyPoint getpOne()
    {
        return pOne;
    }

    public MyPoint getpTwo()
    {
        return pTwo;
    }
    public void translate(MyPoint vec)
    {
        pOne.translate(vec);
        pTwo.translate(vec);
    }
    public MyPoint getCenter()
    {
        float xS= (pOne.getX()+pTwo.getX())/2.0f;
        float yS=(pOne.getY()+pTwo.getY())/2.0f;
        return new MyPoint(xS, yS);
    }
    public MyPoint getPointOfIntersection(MyLine other)
    {
        return intersect(pOne.getX(), pOne.getY(), pTwo.getX(), pTwo.getY(), other.pOne.getX(), other.pOne.getY(), other.pTwo.getX(), other.pTwo.getX());
    }
    public boolean isIntersecting(MyLine other)
    {
        return getPointOfIntersection(other) != null;
    }
    private MyPoint intersect(float lOnePOneX, float lOnePOneY, float lOnePTwoX, float lOnePTwoY,  float lTwoPOneX, float lTwoPOneY, float lTwoPTwoX, float lTwoPTwoY)
    {
        float outPX,outPY;
        float lOneSlopeX, lOneSlopeY, lTwoSlopeX, lTwoSlopeY;
        lOneSlopeX = lOnePTwoX - lOnePOneX;     
        lOneSlopeY = lOnePTwoY - lOnePOneY;
        lTwoSlopeX = lTwoPTwoX - lTwoPOneX;     
        lTwoSlopeY = lTwoPTwoY - lTwoPOneY;

        float s, t;
        float d =(-lTwoSlopeX * lOneSlopeY + lOneSlopeX * lTwoSlopeY);
        s = (-lOneSlopeY * (lOnePOneX - lTwoPOneX) + lOneSlopeX * (lOnePOneY - lTwoPOneY)) / d;
        t = ( lTwoSlopeX * (lOnePOneY - lTwoPOneY) - lTwoSlopeY * (lOnePOneX - lTwoPOneX)) / d;
        System.out.println("s:"+s+" t:"+t);
        if (s >= 0 && s <= 1 && t >= 0 && t <= 1)//collision
        {
            outPX = lOnePOneX + (t * lOneSlopeX);
            outPY = lOnePOneY + (t * lOneSlopeY);            
            return new MyPoint(outPX,outPY);
        }

        return null; 
    }
}
