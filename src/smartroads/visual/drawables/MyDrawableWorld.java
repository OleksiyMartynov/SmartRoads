package smartroads.visual.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.collidables.IMyCollidable;
import smartroads.visual.drawables.collidables.MyCollidableLine;
import smartroads.visual.drawables.collidables.MyCollidablePoint;
import smartroads.visual.drawables.collidables.MyCollidableRectangle;
import smartroads.visual.drawables.objects.MyCar;
/**
 *
 * @author Oleksiy
 */
public class MyDrawableWorld implements IMyDrawable
{
    private List<IMyCollidable> drawables = new ArrayList<>();

    public MyDrawableWorld()
    {
        init();
    }
    private void  init()
    {
        MyCollidableRectangle dr=MyCollidableRectangle.initCollidableRect(100, 100, 50, 50, Color.BLACK);
        dr.setRotationVelocityDeg(1);
        //dr.rotateByDeg(dr.getCenter(), 90);
        //dr.setVelocity(new MyPoint(1f,1f));
        
        MyCollidableLine dl = new MyCollidableLine(new MyDrawablePoint(50f, 50f), new MyDrawablePoint(100f, 150f));  
        dl.setColor(Color.BLACK);
        dl.setPivotPoint(dl.getCenter());
        System.out.println(dl.getCenter().toString());
        //dl.rotateByDeg(dl.getCenter(), 20);
        dl.setRotationVelocityDeg(1.0f);
        //dl.setVelocity(new MyPoint(1f,1f));
        
        MyCollidableLine dl2 = new MyCollidableLine(new MyDrawablePoint(100f, 50f),new MyDrawablePoint(150f, 150f));
        dl2.setColor(Color.BLACK);
        dl2.setPivotPoint(new MyDrawablePoint(100f, 50f));
        System.out.println(dl2.getCenter().toString());
        dl2.setRotationVelocityDeg(-1.0f);
        
        MyCollidablePoint dp= new MyCollidablePoint(77, 77);
        dp.setColor(Color.BLACK);
        
        MyCar car = new MyCar(new MyPoint(50f, 50f));
        //drawables.add(dl);
        drawables.add(dl2);
        drawables.add(dr);
        drawables.add(dp);
    }
    @Override
    public void draw(Graphics2D g)
    {
        drawables.parallelStream().forEach(d->{d.draw(g);});
    }

    @Override
    public void update(int delta)
    {
        drawables.parallelStream().forEach(d->{d.update(delta);});
        drawables.parallelStream().forEach(d1->{drawables.parallelStream().forEach(d2->{
            if(d2!=d1)
            {
                d2.isColliding(d1);
            }
        });});
    }

    @Override
    public MyPoint getVelocity()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVelocity(MyPoint vel)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRotationVelocityDeg(double angleStepDegrees)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getRotationVelocityDeg()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setColor(Color c)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColor()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void translate(MyPoint vec)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateByDeg(MyPoint pivotPoint, double angleDegrees)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getRotationDeg()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MyPoint getCenter()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPivotPoint(MyPoint p)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateByDeg(double angleDegrees)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MyLine> getLines()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
