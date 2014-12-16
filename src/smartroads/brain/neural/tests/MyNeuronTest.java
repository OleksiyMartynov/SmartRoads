package smartroads.brain.neural.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import smartroads.brain.neural.IMyNeuronFunction;
import smartroads.brain.neural.MyNeuron;

/**
 *
 * @author Oleksiy
 */
public class MyNeuronTest
{
    private MyNeuron<Integer> n;
    public void setUpNeuron() throws Exception
    {
        List<Integer> weights = new ArrayList(Arrays.asList(new Integer[]{Integer.MAX_VALUE/2,Integer.MAX_VALUE/2}));
        n= new MyNeuron(weights, new IMyNeuronFunction<Integer>()
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
                    System.out.println("n:"+mapIntegerToSpecialDouble(inData.get(i))+" w:"+mapIntegerToSpecialDouble(weights.get(i)));
                    System.out.println("t:"+mapIntegerToSpecialDouble(threshold));
                    total+=mapIntegerToSpecialDouble(inData.get(i))*mapIntegerToSpecialDouble(weights.get(i));
                }
                double answer=1.0 / (1.0 + Math.exp((-total) / mapIntegerToSpecialDouble(threshold)));
                System.out.println("a:"+answer);
                return mapSpecialDoubleToInteger(answer);
            }
            private double mapIntegerToSpecialDouble(Integer in) //returns double between -1 and 1
            {
                if(in>=0)
                {
                    return (double)in/(double)Integer.MAX_VALUE;
                }
                else
                {
                    return (double)in/(double)Integer.MIN_VALUE;
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
        }, Integer.MAX_VALUE/2);
    }
    public void feedNeuron() throws Exception
    {
        Integer output=n.getOutput(new ArrayList(Arrays.asList(new Integer[]{Integer.MAX_VALUE/3,Integer.MAX_VALUE/9})));
        System.out.println("neuron output:"+output);
    }
}
