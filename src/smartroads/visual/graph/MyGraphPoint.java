package smartroads.visual.graph;

/**
 *
 * @author Oleksiy
 * @param <U>
 * @param <V>
 */
public class MyGraphPoint<U extends Number,V extends Number>
{
    private U x;
    private V y;

    public MyGraphPoint(U x, V y)
    {
        this.x = x;
        this.y = y;
    }

    public U getX()
    {
        return x;
    }

    public V getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return "MyGraphPoint{" + "x=" + x + ", y=" + y + '}';
    }
    
}
