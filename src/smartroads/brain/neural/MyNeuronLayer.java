package smartroads.brain.neural;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleksiy
 * @param <U>
 */
public class MyNeuronLayer <U extends Number>
{
    private List<MyNeuron<U>> neurons;

    public MyNeuronLayer(List<MyNeuron<U>> neurons) throws Exception
    {
        if(neurons!=null&&!neurons.isEmpty())
        {
            this.neurons = neurons;
        }
        else
        {
            throw new Exception("neurons cannot be null or empty");
        }
    }
    public void setWeights(List<U>weights) throws Exception
    {        
        if(weights.size()!=getInputCount())
        {
            throw new Exception("weight count doesnt match neuron inputs for this layer");
        }
        int index=0;
        for(MyNeuron n : neurons)
        {
            n.setWeights(weights.subList(index, n.getWeightsCount()));
            index+=n.getWeightsCount();
        }
    }
    public void setThresholds(List<U> thresholds) throws Exception 
    {
        if(thresholds.size()!=neurons.size())
        {
            throw new Exception("threshold count doesnt match neuron count in this layer");
        }
        for(int i =0; i<neurons.size(); i++)
        {
            neurons.get(i).setThreshold(thresholds.get(i));
        }
    }
    public List<U> getOutput(List<U> inData) throws Exception
    {        
        int neuronsTotalInputs=getInputCount();
        if(inData.size()!=neuronsTotalInputs)
        {
            int index=0;
            List<U>outData = new ArrayList<>();
            for(MyNeuron<U> n : neurons)
            {
                
                n.getOutput(inData.subList(index, n.getWeightsCount()));
                index+=n.getWeightsCount();
            }
            return outData;
        }
        else
        {
            throw new Exception("inData item count doesnt match the number of neuron inputs in this layer.");
        }        
    }
    public int getInputCount()
    {
        return neurons.stream().mapToInt(i->i.getWeightsCount()).sum(); 
    }
    public int getNeuronCount()
    {
        return this.neurons.size();
    }
}
