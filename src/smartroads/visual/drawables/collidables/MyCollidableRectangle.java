package smartroads.visual.drawables.collidables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import smartroads.visual.drawables.base.MyDrawableLine;
import smartroads.visual.drawables.base.MyDrawablePoint;


/**
 *
 * @author Oleksiy
 */
public class MyCollidableRectangle extends MyCollidableShape
{

    public MyCollidableRectangle(List<MyDrawableLine> drawableLines, Color c)
    {
        super(drawableLines, c);
    }
    public static MyCollidableRectangle initDefaultCollidableRect()
    {
        ArrayList<MyDrawableLine> lines = new ArrayList<>();
        lines.add(new MyDrawableLine(new MyDrawablePoint(0, 0), new MyDrawablePoint(1, 0)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(1, 0), new MyDrawablePoint(1, 1)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(1, 1), new MyDrawablePoint(0, 1)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(0, 1), new MyDrawablePoint(0, 0)));
        return new MyCollidableRectangle(lines, Color.BLACK);
    }
    public static MyCollidableRectangle initCollidableRect(float x, float y, float width, float height , Color c)
    {
        ArrayList<MyDrawableLine> lines = new ArrayList<>();
        lines.add(new MyDrawableLine(new MyDrawablePoint(x, y), new MyDrawablePoint(x+width, y)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(x+width, y), new MyDrawablePoint(x+width, y+height)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(x+width, y+height), new MyDrawablePoint(x, y+height)));
        lines.add(new MyDrawableLine(new MyDrawablePoint(x, y+height), new MyDrawablePoint(x, y)));
        return new MyCollidableRectangle(lines,c);
    }
}
