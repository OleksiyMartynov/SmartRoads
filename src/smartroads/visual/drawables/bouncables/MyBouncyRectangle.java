package smartroads.visual.drawables.bouncables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.base.MyDrawableLine;
import smartroads.visual.drawables.base.MyDrawablePoint;
import smartroads.visual.drawables.base.MyDrawableWorld;
import smartroads.visual.drawables.collidables.IMyCollidable;
import smartroads.visual.drawables.collidables.MyCollidableRectangle;

/**
 *
 * @author Oleksiy
 */
public class MyBouncyRectangle extends MyCollidableRectangle implements IMyBouncyObject
{
    private boolean colisionEnabled=true;
    private IMyAfterCollisionFunction afterCollisionFunction =null;
    public MyBouncyRectangle(List<MyDrawableLine> drawableLines, Color c)
    {
        super(drawableLines, c);
    }

    @Override
    public ArrayList<MyPoint> isColliding(IMyCollidable other)
    {
        
        ArrayList<MyPoint> collisionPoints=super.isColliding(other); 
        
        if(collisionPoints!=null&&collisionPoints.size()>0)
        {       
            //this.setVelocity(new MyPoint(0, 0));
            /*
            for(MyPoint p : collisionPoints)
            {                                  
                    if(colisionEnabled)//todo find the point closest to colision point and work with that point
                    {                        
                        MyPoint n = this.getCenter().subtract(other.getCenter());
                        n=n.normal();
                        double a1=this.getVelocity().dot(n);
                        double a2=other.getVelocity().dot(n);
                        float w1=getWeight();

                        float w2=other.getWeight();
                        if(!(other instanceof MyBouncyRectangle))//if not bouncy then it is static or unmovable
                        {
                            w2=w1*100000;
                        }
                        double optimizedP=(2.0*(a1-a2))/(w1+w2);
                        MyPoint v1=this.getVelocity().subtract(n.multiply(new MyPoint((float)(optimizedP*w2),(float)(optimizedP*w2)))).scale(0.2f);
                        //MyPoint v2=other.getVelocity().add(n.multiply(new MyPoint(x*w1,x*w1)));
                        this.setVelocity(v1);
                        //other.setVelocity(v2);
                        colisionEnabled=false;
                        System.out.println("colison disabled");                        
                    }
                    break;
                
            }    
                    */
        }  
        /*
        else 
        {            
            if(!colisionEnabled)
            {
            colisionEnabled=true;
            System.out.println("colision enabled");
            }
        }*/
              
        
        return collisionPoints;
    }

    @Override
    public ArrayList<MyPoint> willCollide(IMyCollidable other)
    {
        ArrayList<MyPoint> collisionPoints= super.willCollide(other);
        if(collisionPoints.size()>0&&colisionEnabled)
        {
            colisionEnabled=false;
            System.out.println("colision disabled");
            ArrayList<MyPoint> shapePoints = new ArrayList<>();
            getLines().forEach(l->{shapePoints.add(l.getpOne());shapePoints.add(l.getpTwo());});            
            
            MyPoint collisionPoint = MyPoint.averagePoint(collisionPoints);
            
            MyDrawablePoint dcp= new MyDrawablePoint(collisionPoint.getX(), collisionPoint.getY());
            dcp.setColor(Color.PINK);
            MyDrawableWorld.getInstance().addDrawables(dcp);
            
             MyPoint closestPoint = null;
             ArrayList<MyPoint> sameMagnitudePoints = new ArrayList<>();
            for(MyPoint p : shapePoints)
            {
                if(p!=null)
                {
                    if(closestPoint==null)
                    {
                        closestPoint=p;
                    }
                    else
                    {
                        MyLine l1 = new MyLine(collisionPoint,p);
                        MyLine l2 = new MyLine(collisionPoint,closestPoint);
                        if(l1.getLength()<l2.getLength())
                        {
                            closestPoint=p;
                            sameMagnitudePoints.clear();
                            sameMagnitudePoints.add(closestPoint);
                        }
                        else if(l1.getLength()==l2.getLength())
                        {
                            sameMagnitudePoints.add(p);
                        }
                    }
                }                
            }
            if(closestPoint!=null)
            {
                closestPoint = MyPoint.averagePoint(sameMagnitudePoints);
                
                //debugging
                MyDrawablePoint dp= new MyDrawablePoint(closestPoint.getX(), closestPoint.getY());
                dp.setColor(Color.GREEN);
                MyDrawableWorld.getInstance().addDrawables(dp);
                
                //bounce logic
                MyPoint pointToWallDistance = closestPoint.subtract(collisionPoint);
                MyPoint distanceRemainder= this.getVelocity().subtract(pointToWallDistance);
                
                
                MyPoint n = collisionPoint.subtract(this.getCenter());//this.getCenter().subtract(other.getCenter());
                n=n.normal();
                double a1=this.getVelocity().dot(n);
                double a2=other.getVelocity().dot(n);
                float w1=getWeight();

                float w2=other.getWeight();
                
                double optimizedP=(2.0*(a1-a2))/(w1+w2);
                MyPoint v1=this.getVelocity().subtract(n.multiply(new MyPoint((float)(optimizedP*w2),(float)(optimizedP*w2)))).scale(0.28f);
                this.setVelocity(v1);
                if(other instanceof MyBouncyRectangle)//if not bouncy then it is static or unmovable
                {
                    
                    MyPoint v2=other.getVelocity().add(n.multiply(new MyPoint((float)(optimizedP*w1),(float)(optimizedP*w1)))).scale(0.28f);
                    other.setVelocity(v2);
                    
                }
                
                
                //bounce logic ends
            }     
            
        
        }
        else
        {
            if(!colisionEnabled)
            {
            colisionEnabled=true;
            System.out.println("colision enabled");
            }
        }
        return collisionPoints;
    }

    @Override
    public void update(int delta)
    {
        if(afterCollisionFunction==null)
        {
            super.update(delta); 
        }
        else
        {
            afterCollisionFunction.doThisOnUpdate(delta);
            afterCollisionFunction = null;
        }
    }
    
    
}
