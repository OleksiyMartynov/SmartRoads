package smartroads.visual.drawables;

import java.awt.Graphics2D;
import smartroads.primitives.IMyPhysical;

/**
 *
 * @author Oleksiy
 */
public interface IMyDrawable extends IMyPhysical
{
   public void draw(Graphics2D g); 
   public void update(double delta);
}
