package smartroads;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import smartroads.brain.genetic.IMyFitnessTestFunction;
import smartroads.brain.genetic.IMyRandomDataFunction;
import smartroads.brain.neural.IMyNeuronFunction;
import smartroads.brain.neural.MyNeuronNetworkTester;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.base.IMyDrawable;
import smartroads.visual.drawables.base.MyDrawablePoint;
import smartroads.visual.drawables.collidables.MyCollidableLine;
import smartroads.visual.drawables.collidables.MyCollidableRectangle;

/**
 *
 * @author Oleksiy
 */
public class MySmartCarTester extends MyNeuronNetworkTester<Double> implements IMyDrawable
{    
    private static final int inputCount=4;
    private static final int outputCount=2;
    private MyPoint targetPoint=new MyPoint(130,10);
    private MyCollidableRectangle targetRect=MyCollidableRectangle.initCollidableRect(targetPoint.getX()-5, targetPoint.getY()-5, 10, 10, Color.red);
    private MyPoint carStart=new MyPoint(250,200);
    private MyCollidableLine line ;
    private IMyRandomDataFunction<Double>rf=new IMyRandomDataFunction()
        {
            private final Random r = new Random();
            @Override
            public Double getRandom()
            {
                return Math.random();
            }
        };
    
    public MySmartCarTester() throws Exception
    {
        super(inputCount, outputCount, new IMyNeuronFunction<Double>()
        {
            @Override
            public Double process(List<Double> inData, List<Double> weights, Double threshold) throws Exception
            {
                if(inData.size()!=weights.size())
                {
                    throw new Exception("inData size doesnt match weights size");
                }
                if(threshold==null)
                {
                    throw new Exception("threshold cannot be null");
                }
                double total=0;
                for(int i =0; i<inData.size(); i++)
                {
                    //System.out.println("n+"+mapIntegerToSpecialDouble(inData.get(i))+" w:"+mapIntegerToSpecialDouble(weights.get(i)));
                    total+=inData.get(i)*weights.get(i);
                }
                Double answer =1 / (1 + Math.exp((-total) / threshold));
                //System.out.println("a:"+answer);
                return answer;
            }            
        });
        init(new IMyFitnessTestFunction<Double>()
        {
            @Override
            public int testFitness(List<Double> list) 
            {
                
                try {
                    
                    
                    resetWeightsAndThresholds(list);
                    List<Double> in = Arrays.asList(new Double[]{(double)targetPoint.getX(),(double)targetPoint.getY(),(double)carStart.getX(),(double)carStart.getY()});
                    //in.forEach(i->{System.out.println(""+i.floatValue());});
                    double sumDataIn = in.stream().mapToDouble(d->d).sum();                    
                    List<Double> output = getNetworkOutput(in);
                    double sumDataOut=output.stream().mapToDouble(d->d).sum();
                    System.out.println("in:"+sumDataIn+" out"+sumDataOut);
                    
                    MyPoint distance=targetPoint.subtract(carStart);
                    MyPoint guessLineShort = new MyPoint(output.get(0).floatValue(), output.get(1).floatValue());
                    MyPoint guessLine = guessLineShort.scale((float)(distance.getX()/guessLineShort.getX()));
                    line=new MyCollidableLine(new MyDrawablePoint(carStart.getX(), carStart.getY()), new MyDrawablePoint(guessLine.getX(), guessLine.getY()));
                    line.setColor(Color.black);
                    //list.forEach(l->{System.out.println(""+l);});
                    //System.out.println("------");
                    List<MyPoint> c = line.isColliding(targetRect);
                    //c.forEach(l->{System.out.println("cp:"+l.toString());});
                    if(c!=null&&!c.isEmpty())
                    {
                        return 100;
                    }
                    else
                    {
                        return 0;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MySmartCarTester.class.getName()).log(Level.SEVERE, null, ex);
                }
                    return 0;
            }           
            
        },rf);
    }

    @Override
    public void draw(Graphics2D g)
    {        
        targetRect.draw(g);
        line.draw(g);
    }

    @Override
    public void update(int delta)
    {
        
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
