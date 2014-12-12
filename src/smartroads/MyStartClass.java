package smartroads;


import brain.genetic.IMyFitnessTestFunction;
import brain.genetic.MyPopulation;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import smartroads.primitives.MyPoint;
import smartroads.visual.display.MyCanvasWindow;
import smartroads.visual.drawables.base.MyDrawableWorld;
import sun.security.acl.WorldGroupImpl;


/**
 *
 * @author Oleksiy
 */
public class MyStartClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {   
        /*
        MyDrawableWorld world = MyDrawableWorld.getInstance();
        MyCanvasWindow cw= new MyCanvasWindow(300, 300, world);
        cw.setMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent me)
            {
                world.spawnCar(new MyPoint(me.getX(), me.getY()));
                System.out.println("clicked at:"+me.getX()+" "+me.getY());
                       
            }

            @Override
            public void mousePressed(MouseEvent me)
            {
            }

            @Override
            public void mouseReleased(MouseEvent me)
            {
            }

            @Override
            public void mouseEntered(MouseEvent me)
            {
            }

            @Override
            public void mouseExited(MouseEvent me)
            {
            }
        });
        cw.startWindow();*/
        IMyFitnessTestFunction testFunc = new IMyFitnessTestFunction()
        {

            @Override
            public int testFitness(List<Integer> list)
            {
                //System.out.println("list size:"+list.size());
                return (int)list.stream().mapToInt(i->evaluate(i)).sum()/list.size();
            }
            private int evaluate(int given)
            {
                //System.out.println("g"+given);
                if(given%2==0)
                {
                    return 100;
                }
                else
                {
                    return 0;
                }
            }
        };
        MyPopulation pop = new MyPopulation(25, 7, testFunc);
        for(int i=0;i<1000; i++)
        {
            try {
                pop.nextGeneration();
            } catch (Exception ex) {
                Logger.getLogger(MyStartClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Population #"+i);
            System.out.println("-average fitnes:"+pop.getAverageFitness());            
        }
        pop.getMostFitIndividual().getData().forEach(i->{System.out.println(""+i);});
    }
    
}
