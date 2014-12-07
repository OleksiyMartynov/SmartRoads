package smartroads.helpers;

import java.util.ArrayList;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.collidables.IMyCollidable;

/**
 *
 * @author Oleksiy
 */
public class MyMathHelper
{
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
    
    public static MyPoint intersect(IMyCollidable cur, IMyCollidable other)
    {
        ArrayList<MyPoint> list = new ArrayList<>();
        cur.getLines().forEach(l1->{other.getLines().forEach(l2->{list.add(MyMathHelper.intersect(l1, l2));});});
        return list.get(0);
    }
}
