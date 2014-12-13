package smartroads.brain.neural;

import java.util.List;

/**
 *
 * @author Oleksiy
 * @param <U>
 */
public class MyNeuron <U extends Number> 
{
    private List<U> weights;
    private IMyNeuronFunction<U> calcFunc;
    private U threshold;

    public MyNeuron(List<U> weights, IMyNeuronFunction<U> calcFunc, U threshold) throws Exception
    {
        if(weights!=null&&!weights.isEmpty()&&calcFunc!=null&&threshold!=null)
        {
            this.weights = weights;
            this.calcFunc = calcFunc;
            this.threshold = threshold;
        }
        else
        {
            throw new Exception("null parameter in neuron constructor");
        }
    }

    public void setWeights(List<U> weights)
    {
        this.weights = weights;
    }

    public void setThreshold(U threshold)
    {
        this.threshold = threshold;
    }
    
    public U getOutput(List<U>inputs) throws Exception
    {
        return this.calcFunc.process(inputs,weights, threshold);
    }
    
    public int getWeightsCount()
    {
        return weights.size();
    }
}
