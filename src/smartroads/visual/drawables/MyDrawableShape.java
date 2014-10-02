package smartroads.visual.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.primitives.MyShape;

/**
 *
 * @author Oleksiy
 */
public class MyDrawableShape extends MyShape implements IMyDrawable
{
    List<MyDrawableLine> drawableLines ;
    private MyDrawableShape(List<MyLine> lines)
    {
        super(lines);
    }

    public MyDrawableShape(List<MyDrawableLine> drawableLines, Color c)
    {
        super((List<MyLine>)(List<?>)drawableLines);
        this.drawableLines = drawableLines;
        this.drawableLines.parallelStream().forEach(l->{l.setColor(c);});
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        drawableLines.parallelStream().forEach(l->{l.draw(g);});
    }

    @Override
    public void update(double delta)
    {
        drawableLines.parallelStream().forEach(l->{l.update(delta);});
    }

    @Override
    public MyPoint getVelocity()
    {
        MyPoint avgVec = new MyPoint(0,0);
        drawableLines.forEach(l->{avgVec.translate(l.getVelocity());});
        return avgVec;
    }

    @Override
    public void setVelocity(MyPoint vel)
    {
        drawableLines.parallelStream().forEach(l->{l.setVelocity(vel);});
    }

    @Override
    public void setColor(Color c)
    {
        drawableLines.parallelStream().forEach(l->{l.setColor(c);});
    }

    @Override
    public Color getColor()
    {        
        MyColor avgColor = new MyColor();
        drawableLines.forEach(l->{
            avgColor.add(l.getColor());
        });
        return avgColor.toSystemColor();
    }
    
}
