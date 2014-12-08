package smartroads.visual.drawables.collidables;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import smartroads.primitives.MyPoint;
import smartroads.visual.drawables.MyDrawableLine;

/**
 *
 * @author Oleksiy
 */
public class MyBouncyRectangle extends MyCollidableRectangle
{

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
            
            for(MyPoint p : collisionPoints)
            {
                if(p!=null)
                {
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
                    double optimizedP=(1.1*(a1-a2))/(w1+w2);
                    
                    MyPoint v1=this.getVelocity().subtract(n.multiply(new MyPoint((float)(optimizedP*w2),(float)(optimizedP*w2))));
                    //MyPoint v2=other.getVelocity().add(n.multiply(new MyPoint((float)(optimizedP*w1),(float)(optimizedP*w1))));
                    this.setVelocity(v1);
                    //other.setVelocity(v2);
                    break;
                }
            }            
        }
        return collisionPoints;
    }
    
    
}
