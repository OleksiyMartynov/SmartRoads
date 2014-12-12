package smartroads.visual.graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.base.IMyDrawable;
import smartroads.visual.drawables.base.MyDrawableLine;
import smartroads.visual.drawables.base.MyDrawablePoint;

/**
 *
 * @author Oleksiy
 */
public class MyDrawableGraph implements IMyDrawable
{
    public enum SortByAxis {xAxis,yAxis};
    public enum SortDirection {asc,desc};
    List<MyGraphPoint> graphData = new ArrayList<>();
    List<MyGraphPoint> tempGraphData = new ArrayList<>();
    private SortByAxis sortAxis;
    private SortDirection sortDirection;
    private int windowWidth,windowHeight;
    int minX,minY,maxX,maxY;
    public MyDrawableGraph(SortByAxis sa, SortDirection sd, int windowWidth, int windowHeight)
    {
        this.sortAxis=sa;
        this.sortDirection=sd;
        this.windowWidth=windowWidth;
        this.windowHeight=windowHeight;
    }
    public void addGraphItem(MyGraphPoint p) throws Exception
    {
        if(p!=null)
        {
            tempGraphData.add(p);
        }
        else
        {
            throw new Exception("cannot add null item to graph");
        }
    }
    @Override
    public void draw(Graphics2D g)
    {
        //System.out.println("minX"+minX);
        //System.out.println("minY"+minY);
        //System.out.println("maxX"+maxX);
        //System.out.println("maxY"+maxY);
        g.clearRect(0, 0, windowWidth, windowHeight);
        MyPoint translateByVector=new MyPoint(-minX,-minY);
        float scaleX,scaleY;
        try{
        scaleX=(maxX==0)?0:windowWidth/(maxX-minX);
        scaleY=(maxY==0)?0:windowHeight/(maxY-minY);
        }
        catch(Exception e)
        {
            scaleX=0;
            scaleY=0;
        }
        
        MyPoint scaleByVector=new MyPoint(scaleX, scaleY);
        translateByVector.scale(scaleByVector);
        MyDrawablePoint previousPoint=null;
        for(MyGraphPoint gp : graphData)
        {
            MyDrawablePoint thisPoint = new MyDrawablePoint(gp.getX().intValue(), gp.getY().intValue());
            thisPoint.scale(scaleByVector); 
            thisPoint.translate(translateByVector);
                       
            thisPoint=new MyDrawablePoint(thisPoint.getX(),windowHeight-thisPoint.getY());
            if(previousPoint==null)
            {
                previousPoint=thisPoint;
            }
            MyDrawableLine dl = new MyDrawableLine(thisPoint, previousPoint);
            dl.setColor(Color.BLACK);
            dl.draw(g);
            previousPoint=thisPoint;
        }
    }

    @Override
    public void update(int delta)
    {
        graphData.addAll(tempGraphData);
        tempGraphData.clear();
        if(!graphData.isEmpty())
        {
            if(sortAxis==SortByAxis.xAxis)
            {
                if(sortDirection==SortDirection.desc)
                {
                    graphData=graphData.stream().sorted((a,b)->a.getX().intValue()-b.getX().intValue()).collect(Collectors.toList());
                }
                else
                {
                    graphData=graphData.stream().sorted((a,b)->b.getX().intValue()-a.getX().intValue()).collect(Collectors.toList());
                }
            }
            else
            {
                if(sortDirection==SortDirection.desc)
                {
                    graphData=graphData.stream().sorted((a,b)->a.getY().intValue()-b.getY().intValue()).collect(Collectors.toList());
                }
                else
                {
                    graphData=graphData.stream().sorted((a,b)->b.getY().intValue()-a.getY().intValue()).collect(Collectors.toList());
                }
            }
            //System.out.println("Testing:");
            //graphData.forEach(t->{System.out.println(t.toString());});
            minX=graphData.stream().mapToInt(i->i.getX().intValue()).min().getAsInt();
            minY=graphData.stream().mapToInt(i->i.getY().intValue()).min().getAsInt();
            maxX=graphData.stream().mapToInt(i->i.getX().intValue()).max().getAsInt();
            maxY=graphData.stream().mapToInt(i->i.getY().intValue()).max().getAsInt();
        }
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
