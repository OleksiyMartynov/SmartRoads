package smartroads.visual.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import smartroads.primitives.IMyPhysical;
import smartroads.primitives.MyPoint;

/**
 *
 * @author Oleksiy
 */
public interface IMyDrawable extends IMyPhysical
{
   public void draw(Graphics2D g); 
   public void update(int delta);
   public MyPoint getVelocity();
   public void setVelocity(MyPoint vel);
   public void setRotationVelocityDeg(double angleStepDegrees);
   public double getRotationVelocityDeg();
   public void setColor(Color c);
   public Color getColor();
}
