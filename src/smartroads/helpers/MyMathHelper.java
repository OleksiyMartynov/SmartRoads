package smartroads.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.collidables.IMyCollidable;

/**
 *
 * @author Oleksiy
 */
public class MyMathHelper
{
    //finds intersect of two lines given the coordinates
    public static MyPoint intersect(float lOnePOneX, float lOnePOneY, float lOnePTwoX, float lOnePTwoY,  float lTwoPOneX, float lTwoPOneY, float lTwoPTwoX, float lTwoPTwoY)
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
        //System.out.println("s:"+s+" t:"+t);
        if (s >= 0 && s <= 1 && t >= 0 && t <= 1)//collision
        {
            outPX = lOnePOneX + (t * lOneSlopeX);
            outPY = lOnePOneY + (t * lOneSlopeY);            
            return new MyPoint(outPX,outPY);
        }

        return null; 
    }
    
    public static MyPoint intersect(MyLine cur,MyLine other)
    {
        return intersect(cur.getpOne().getX(), cur.getpOne().getY(), cur.getpTwo().getX(), cur.getpTwo().getY(), other.getpOne().getX(), other.getpOne().getY(), other.getpTwo().getX(), other.getpTwo().getY());
    }
    
    public static ArrayList<MyPoint> intersect(IMyCollidable cur, IMyCollidable other)
    {
        final ArrayList<MyPoint> list = new ArrayList<>();
        cur.getLines().forEach(l1->{other.getLines().forEach(l2->{list.add(MyMathHelper.intersect(l1, l2));});});
        return list.stream().filter(l->l!=null).collect(Collectors.toCollection(ArrayList::new));
    }
    public static  ArrayList<MyPoint> intersect(List<MyLine> l1, List<MyLine> l2)
    {
        final ArrayList<MyPoint> list = new ArrayList<>();
        l1.forEach(lineOne->{l2.forEach(lineTwo->{list.add(MyMathHelper.intersect(lineOne, lineTwo));});});
        return list.stream().filter(l->l!=null).collect(Collectors.toCollection(ArrayList::new));
    }
    //rotaits point around the origin by given degrees
    public static double[] rotateByDegree(double pivotX, double pivotY, double pointX,double pointY, double angleDegrees)
    {
        double angleInRadians = angleDegrees *(Math.PI/180);
        double cosTheta = Math.cos(angleInRadians);
        double sinTheta =Math.sin(angleInRadians);
        double newX=(cosTheta*(pointX-pivotX)-sinTheta*(pointY-pivotY)+pivotX);
        double newY=(sinTheta*(pointX-pivotX)+cosTheta*(pointY-pivotY)+pivotY);
        return new double[]{newX,newY};
    }
}
