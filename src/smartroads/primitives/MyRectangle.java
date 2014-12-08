package smartroads.primitives;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleksiy
 */
public class MyRectangle extends MyShape
{
    public MyRectangle(List<MyLine> lines)
    {
        super(lines);
    }
    public static MyRectangle initDefaultRect()
    {
        ArrayList<MyLine> lines = new ArrayList<>();
        lines.add(new MyLine(new MyPoint(0, 0), new MyPoint(1, 0)));
        lines.add(new MyLine(new MyPoint(1, 0), new MyPoint(1, 1)));
        lines.add(new MyLine(new MyPoint(1, 1), new MyPoint(0, 1)));
        lines.add(new MyLine(new MyPoint(0, 1), new MyPoint(0, 0)));
        return new MyRectangle(lines);
    }
    public static MyRectangle initRect(float x, float y, float width, float height)
    {
        ArrayList<MyLine> lines = new ArrayList<>();
        lines.add(new MyLine(new MyPoint(x, y), new MyPoint(x+width, y)));
        lines.add(new MyLine(new MyPoint(x+width, y), new MyPoint(x+width, y+height)));
        lines.add(new MyLine(new MyPoint(x+width, y+height), new MyPoint(x, y+height)));
        lines.add(new MyLine(new MyPoint(x, y+height), new MyPoint(x, y)));
        return new MyRectangle(lines);
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
