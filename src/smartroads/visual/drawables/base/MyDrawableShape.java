package smartroads.visual.drawables.base;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.primitives.MyShape;
import smartroads.visual.drawables.MyColor;

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
        //System.out.println("draw callaed");
    }

    @Override
    public void update(int delta)
    {
        drawableLines.parallelStream().forEach(l->{l.update(delta);});
        //System.out.println("update callaed");
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

    @Override
    public void setRotationVelocityDeg(double angleStepDegrees)
    {
        drawableLines.parallelStream().forEach(l->{l.setRotationVelocityDeg(angleStepDegrees);});
    }

    @Override
    public double getRotationVelocityDeg()
    {
        MyPoint sum = new MyPoint(0, 0);
        drawableLines.parallelStream().forEach((MyDrawableLine l)->{sum.translate(new MyPoint((float)l.getRotationVelocityDeg(), 1));});
        return sum.getX()/sum.getY();
    }

    @Override
    public float getWeight()
    {
        double perimiter =0;
        perimiter = getLines().stream().map((l) -> l.getLength()).reduce(perimiter, (accumulator, _item) -> accumulator + _item);
        return (float)perimiter;
    }


    
}
