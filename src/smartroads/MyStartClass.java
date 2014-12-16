package smartroads;


import smartroads.brain.genetic.IMyFitnessTestFunction;
import smartroads.brain.genetic.MyPopulation;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import smartroads.brain.genetic.IMyRandomDataFunction;
import smartroads.brain.genetic.MyIndividual;
import smartroads.brain.neural.IMyNeuronFunction;
import smartroads.brain.neural.tests.MyNeuronTest;
import smartroads.primitives.MyPoint;
import smartroads.visual.display.MyCanvasWindow;
import smartroads.visual.drawables.base.MyDrawableWorld;
import smartroads.visual.graph.MyDrawableGraph;
import smartroads.visual.graph.MyGraphPoint;


/**
 *
 * @author Oleksiy
 */
public class MyStartClass 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {   
        //testNeuron();
        testNeuronNetwork();
        //testGeneticAlgorithmInteger();
        //testGeneticAlgorithmDouble();
    }
    public static void testGraphics()
    {
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
            cw.startWindow();
    }
    public static void testGeneticAlgorithmInteger()
    {
        IMyRandomDataFunction<Integer> rFunc = new IMyRandomDataFunction()
            {
            private final Random r = new Random();
            @Override
            public Integer getRandom()
            {
            return r.nextInt();
            }
            };
            IMyFitnessTestFunction<Integer> tFunc = new IMyFitnessTestFunction<Integer>()
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
            IMyNeuronFunction<Integer> calcFunc = new IMyNeuronFunction<Integer>()
            {
            @Override
            public Integer process(List<Integer> inData, List<Integer> weights, Integer threshold) throws Exception
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
            
            total+=mapIntegerToSpecialDouble(inData.get(i))*mapIntegerToSpecialDouble(weights.get(i));
            }
            return mapSpecialDoubleToInteger(1 / (1 + Math.exp((-total) / mapIntegerToSpecialDouble(threshold))));
            }
            private double mapIntegerToSpecialDouble(Integer in) //returns double between -1 and 1
            {
            if(in>=0)
            {
            return in/Integer.MAX_VALUE;
            }
            else
            {
            return in/Integer.MIN_VALUE;
            }
            }
            private int mapSpecialDoubleToInteger(double in)
            {
            if(in>=0)
            {
            return (int)(Integer.MAX_VALUE*in);
            }
            else
            {
            return (int)(Integer.MIN_VALUE*in);
            }
            }
            };
            try
            {
            MyDrawableGraph dg= new MyDrawableGraph(MyDrawableGraph.SortByAxis.xAxis, MyDrawableGraph.SortDirection.asc, 800, 600);
            MyCanvasWindow graphWindow = new MyCanvasWindow(800, 600, dg);
            graphWindow.startWindow();
            MyPopulation<Integer> pop = new MyPopulation(50, 10, tFunc, rFunc,false);
            for(int i=0;i<1000; i++)
            {
            try 
            {
            pop.nextGeneration();
            int avgFitness =pop.getAverageFitness();
            List<MyGraphPoint<Integer,Integer>> graphPoints=pop.getPopulation().stream().map(p->new MyGraphPoint<>(pop.getGenerationCount(),avgFitness)).collect(Collectors.toList());
            
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
    public static void testGeneticAlgorithmDouble()
    {
        IMyRandomDataFunction<Double> rFunc = new IMyRandomDataFunction()
            {
            private final Random r = new Random();
                @Override
                public Double getRandom()
                {
                    return Math.random();
                }
            };
            IMyFitnessTestFunction<Double> tFunc = new IMyFitnessTestFunction<Double>()
            {
                @Override
                public int testFitness(List<Double> list) 
                {
                //System.out.println("list size:"+list.size());
                    return (int)list.stream().mapToInt(i->evaluate(i)).sum()/list.size();
                }
                private int evaluate(double given)
                {
                    //System.out.println("g"+given);
                    if(given>0.333&&given<0.666)
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
            MyPopulation<Double> pop = new MyPopulation(50, 10, tFunc, rFunc,false);
            for(int i=0;i<1000; i++)
            {
            try 
            {
            pop.nextGeneration();
            int avgFitness =pop.getAverageFitness();
            List<MyGraphPoint<Integer,Integer>> graphPoints=pop.getPopulation().stream().map(p->new MyGraphPoint<>(pop.getGenerationCount(),avgFitness)).collect(Collectors.toList());
            
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
    public static void testNeuronNetwork()
    {
        try 
        {
            MySmartCarTester tester = new MySmartCarTester();
            MyDrawableGraph nnfGraph= new MyDrawableGraph(MyDrawableGraph.SortByAxis.xAxis, MyDrawableGraph.SortDirection.asc, 300, 260);
            MyCanvasWindow nnfgWindow= new MyCanvasWindow(300, 260, nnfGraph);
            //nnfgWindow.startWindow();
            
            MyCanvasWindow worldWindow = new MyCanvasWindow(300, 250,tester);
            worldWindow.startWindow();
            int fitness=0;
            int iterations=0;
            while((fitness=tester.getFitness())<995)
            {
                
                System.out.println("f:"+fitness);
                tester.evolveNetwork();
                ++iterations;
                //nnfGraph.addGraphItem(new MyGraphPoint(iterations, fitness));
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(MyStartClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void testNeuron()
    {
        try {
            MyNeuronTest t = new MyNeuronTest();
            t.setUpNeuron();
            t.feedNeuron();
        } catch (Exception ex) {
            Logger.getLogger(MyStartClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
