package smartroads;


import smartroads.brain.genetic.IMyFitnessTestFunction;
import smartroads.brain.genetic.MyPopulation;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import smartroads.primitives.MyPoint;
import smartroads.visual.display.MyCanvasWindow;
import smartroads.visual.drawables.base.MyDrawableWorld;
import smartroads.visual.graph.MyDrawableGraph;
import smartroads.visual.graph.MyGraphPoint;
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
        
        try 
        {
            MyDrawableGraph dg= new MyDrawableGraph(MyDrawableGraph.SortByAxis.xAxis, MyDrawableGraph.SortDirection.asc, 800, 600);
            MyCanvasWindow graphWindow = new MyCanvasWindow(800, 600, dg);
            graphWindow.startWindow();
            MyPopulation pop = new MyPopulation(50, 100, testFunc);
            for(int i=0;i<1000; i++)
        {
            try 
            {
                pop.nextGeneration();
                int avgFitness =pop.getAverageFitness();
               List<MyGraphPoint> graphPoints=pop.getPopulation().stream().map(p->new MyGraphPoint(pop.getGenerationCount(),avgFitness)).collect(Collectors.toList());
                graphPoints.stream().forEach(si->{try {
                    dg.addGraphItem(si);
                    } catch (Exception ex) {
                        Logger.getLogger(MyStartClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    });
                System.out.println("Population #"+i);
                System.out.println("-average fitnes:"+avgFitness); 
                if(avgFitness>=99)
                {
                    break;
                }
                //Thread.sleep(100);
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(MyStartClass.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
        }
        pop.getMostFitIndividual().getData().forEach(i->{System.out.println(""+i);});
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(MyStartClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
