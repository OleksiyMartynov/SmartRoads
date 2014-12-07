package smartroads.visual.drawables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Oleksiy
 */
public class MyDrawableRectangle extends MyDrawableShape
{

    public MyDrawableRectangle(List<MyDrawableLine> drawableLines, Color c)
    {
        super(drawableLines, c);
    }
    public static MyDrawableRectangle initDefaultDrawableRect()
    {
        ArrayList<MyDrawableLine> lines = new ArrayList<>();
        lines.add(new MyDrawableLine(new MyDrawablePoint(0, 0), new MyDrawablePoint(1, 0)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(1, 0), new MyDrawablePoint(1, 1)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(1, 1), new MyDrawablePoint(0, 1)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(0, 1), new MyDrawablePoint(0, 0)));
        return new MyDrawableRectangle(lines, Color.BLACK);
    }
    public static MyDrawableRectangle initDrawableRect(float x, float y, float width, float height , Color c)
    {
        ArrayList<MyDrawableLine> lines = new ArrayList<>();
        lines.add(new MyDrawableLine(new MyDrawablePoint(x, y), new MyDrawablePoint(x+width, y)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(x+width, y), new MyDrawablePoint(x+width, y+height)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(x+width, y+height), new MyDrawablePoint(x, y+height)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(x, y+height), new MyDrawablePoint(x, y)));
        return new MyDrawableRectangle(lines,c);
    }
    
}
