package smartroads;

import java.util.List;
import java.util.Random;
import smartroads.brain.genetic.IMyFitnessTestFunction;
import smartroads.brain.genetic.IMyRandomDataFunction;
import smartroads.brain.genetic.MyPopulation;
import smartroads.brain.neural.IMyNeuronFunction;
import smartroads.brain.neural.MyNeuronNetworkTester;
import smartroads.primitives.MyPoint;

/**
 *
 * @author Oleksiy
 */
public class MySmartCarTester extends MyNeuronNetworkTester<Integer>
{
    MyPopulation<Integer> population;
    private static final int inputCount=4;
    private static final int outputCount=2;
    private IMyRandomDataFunction<Integer>rf=new IMyRandomDataFunction()
        {
            private final Random r = new Random();
            @Override
            public Integer getRandom()
            {
                return r.nextInt();
            }
        };
    
    public MySmartCarTester() throws Exception
    {
        super(inputCount, outputCount, new IMyNeuronFunction<Integer>()
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
        });
        init(new IMyFitnessTestFunction<Integer>()
        {

            @Override
            public int testFitness(List<Integer> list) throws Exception
            {
                List<Integer> output = getNetworkOutput(list);
                MyPoint targetPoint=new MyPoint(130,120);
                MyPoint aiPoint= new MyPoint(list.get(0),list.get(1));
                return 100-(int)(targetPoint.subtract(aiPoint).magnitude());
            }
            
            
        },rf);
    }
    
}
