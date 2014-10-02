/*

 */

package smartroads.primitives;

/**
 *
 * @author Oleksiy
 */
public class MyRectDEPR
{
    private int x,y,width,height;

    public MyRectDEPR(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public boolean intersects(MyRectDEPR other)
    {
        if(contains(other.getTopLeft()))
        {
            return true;
        }
        else if(contains(other.getTopRight()))
        {
            return true;
        }
        else if(contains(other.getBottomLeft()))
        {
            return true;
        }
        else if(contains(other.getBottomRight()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean contains(MyPoint point)
    {
        return x<=point.getX()&&(x+width)>=point.getX() && y<=point.getY()&&(y+height)>=point.getY();       
    }
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
    public MyPoint getTopLeft()
    {
        return new MyPoint(x,y);
    }
    public MyPoint getTopRight()
    {
        return new MyPoint(x+width,y);
    }
    public MyPoint getBottomLeft()
    {
        return new MyPoint(x,y+height);
    }
    public MyPoint getBottomRight()
    {
        return new MyPoint(x+width,y+height);
    }
    public MyPoint getCenter()
    {
        return new MyPoint((x+width)/2,(y+height)/2);
    }
}
