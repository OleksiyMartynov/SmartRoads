package smartroads.visual.drawables.bouncables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.base.MyDrawableLine;
import smartroads.visual.drawables.collidables.IMyCollidable;
import smartroads.visual.drawables.collidables.MyCollidableRectangle;

/**
 *
 * @author Oleksiy
 */
public class MyBouncyRectangle extends MyCollidableRectangle implements IMyBouncyObject
{
    private boolean colisionEnabled=true;
    public MyBouncyRectangle(List<MyDrawableLine> drawableLines, Color c)
    {
        super(drawableLines, c);
    }

    @Override
    public ArrayList<MyPoint> isColliding(IMyCollidable other)
    {        
        
        
        ArrayList<MyPoint> collisionPoints=super.isColliding(other); 
        if(collisionPoints!=null&&collisionPoints.size()>0)//&&colisionEnabled
        {            
            for(MyPoint p : collisionPoints)
            {                
                    //colisionEnabled=false;
                    //System.out.println("collison disabled");
                    MyPoint n = this.getCenter().subtract(other.getCenter());
                    n=n.normal();
                    double a1=this.getVelocity().dot(n);
                    double a2=other.getVelocity().dot(n);
                    float w1=getWeight();
                    
                    float w2=other.getWeight();
                    if(!(other instanceof MyBouncyRectangle))//if not bouncy then it is static or unmovable
                    {
                        w2=w1*10000;
                    }
                    double optimizedP=(2.0*(a1-a2))/(w1+w2);
                    MyPoint v1=this.getVelocity().subtract(n.multiply(new MyPoint((float)(optimizedP*w2),(float)(optimizedP*w2)))).multiply(new MyPoint(0.4f, 0.4f));
                    //MyPoint v2=other.getVelocity().add(n.multiply(new MyPoint(x*w1,x*w1)));
                    this.setVelocity(v1);
                    //other.setVelocity(v2);
                    break;
                
            }            
        }
        /*else
        {
            colisionEnabled=true;            
            System.out.println("collison enabled");
        }*/
        return collisionPoints;
    }
    
    
}
