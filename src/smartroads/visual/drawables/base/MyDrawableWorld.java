package smartroads.visual.drawables.base;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.collidables.IMyCollidable;
import smartroads.visual.drawables.collidables.MyCollidableLine;
import smartroads.visual.drawables.collidables.MyCollidableRectangle;
import smartroads.visual.drawables.objects.MyCar;
/**
 *
 * @author Oleksiy
 */
public class MyDrawableWorld implements IMyDrawable
{
    private List<IMyCollidable> colidables = new ArrayList<>();
    private List<IMyCollidable> colidablesQueue = new ArrayList<>();
    private List<IMyDrawable> drawables = new ArrayList<>();
    private List<IMyDrawable>drawablesQueue=new ArrayList<>();
    private static MyDrawableWorld instance;
    public static MyDrawableWorld getInstance()
    {
        if(instance==null)
        {
            instance =new MyDrawableWorld();
            return instance;
        }
        return instance;
    }
    private MyDrawableWorld()
    {
        init();        
    }
    public void addCollidable(IMyCollidable c)
    {
        if(c!=null)
        {
            colidablesQueue.add(c);
        }
    }
    public void addDrawables(IMyDrawable d)
    {
        if(d!=null)
        {
            drawablesQueue.add(d);
        }
    }
    private void drawWalls()
    {
        MyCollidableRectangle tw=MyCollidableRectangle.initCollidableRect(0, 0, 280, 10, Color.BLACK);
        tw.rotateByDeg(1);
        MyCollidableRectangle bw=MyCollidableRectangle.initCollidableRect(0, 250, 280, 10, Color.BLACK);
        bw.rotateByDeg(1);
        MyCollidableRectangle lw=MyCollidableRectangle.initCollidableRect(0, 10, 10, 240, Color.BLACK);
        lw.rotateByDeg(1);
        MyCollidableRectangle rw=MyCollidableRectangle.initCollidableRect(270, 10, 10, 240, Color.BLACK);
        rw.rotateByDeg(1);
        colidables.add(tw);
        colidables.add(bw);
        colidables.add(lw);
        colidables.add(rw);
    }
    public void spawnCar(MyPoint p)
    {
         MyCar car = new MyCar(p);
        car.pressGasPedal();
        colidablesQueue.add(car);
    }
    private void  init()
    {
        drawWalls();
        
        
        
        MyCollidableLine dl = new MyCollidableLine(new MyDrawablePoint(10f, 90f), new MyDrawablePoint(200f, 200f));  
        dl.setColor(Color.BLACK);
        dl.setPivotPoint(dl.getCenter());
        System.out.println(dl.getCenter().toString());
        //dl.rotateByDeg(-53.5f);
        //dl.rotateByDeg(dl.getCenter(), 20);
        //dl.setRotationVelocityDeg(1.0f);
        //dl.setVelocity(new MyPoint(0f,-2f));
        
        
        MyCollidableLine dl2 = new MyCollidableLine(new MyDrawablePoint(120f, 70f),new MyDrawablePoint(200f, 150f));
        dl2.setColor(Color.BLACK);
        dl2.setPivotPoint(dl2.getCenter());
        //dl2.setVelocity(new MyPoint(0f,1f));
        dl2.setRotationVelocityDeg(-5.0f);
        
        MyCollidableLine dl3 = new MyCollidableLine(new MyDrawablePoint(120f, 130f), new MyDrawablePoint(200f, 250f));  
        dl3.setColor(Color.BLACK);
        dl3.setRotationVelocityDeg(-4.0f);
        
        
        MyCollidableRectangle dr=MyCollidableRectangle.initCollidableRect(100, 130, 50, 50, Color.BLACK);
        //dr.setRotationVelocityDeg(3.3f);        
        //dr.setVelocity(new MyPoint(0, -1));
        dr.rotateByDeg(dr.getCenter(), 45);
        //dr.setVelocity(new MyPoint(2f,-2f));
        
        MyCar car = new MyCar(new MyPoint(124f, 10f));
        car.pressGasPedal();
        //car.turnRight();
        MyCar car2 = new MyCar(new MyPoint(124f,220f));
        car2.pressBrakePedal();
        //car2.turnLeft();
        //car.turnRight();
        
        
        
        
        //colidables.add(car);
        //colidables.add(car2);
        //colidables.add(dl);
        //colidables.add(dl2);
        //colidables.add(dl3);
        colidables.add(dr);
    }
    @Override
    public void draw(Graphics2D g)
    {
        colidables.forEach(d->{if(d!=null){d.draw(g);}});
        try
        {
            drawables.forEach(d->{if(d!=null){d.draw(g);}});        
        }
        catch(Exception e)
        {
            
        }
    }

    @Override
    public void update(int delta)
    {
        colidables.addAll(colidablesQueue);
        colidablesQueue.clear();
        
        drawables.addAll(drawablesQueue);
        drawablesQueue.clear();
        
        colidables.forEach(d->{d.update(delta);});
        colidables.stream().forEach(d1->{colidables.stream().forEach(d2->{
            if(d2!=d1)
            {
                d2.isColliding(d1);
            }
        });});
        colidables.stream().forEach(d1->{colidables.stream().forEach(d2->{
            if(d2!=d1)
            {
                d2.willCollide(d1);
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

    @Override
    public float getWeight()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
