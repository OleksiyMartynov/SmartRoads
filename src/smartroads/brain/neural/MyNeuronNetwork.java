package smartroads.brain.neural;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleksiy
 * @param <U>
 */
public class MyNeuronNetwork<U extends Number>
{
    List<MyNeuronLayer<U>> layers;
    int inputDataSize;
    public MyNeuronNetwork(int inputs, int outputs)
    {
        //todo calculate # of inputs per layer to the number of desired outputs
        //todo create IMyNeuronFunction instance
        //todo fill each layer with new neurons
        
    }
    public List<U> getOutput(List<U>inData,List<U>thresholds) throws Exception
    {
        if(inData.size()!=inputDataSize)
        {
            throw new Exception("inData size doesnt match promissed size");
        }
        if(inData.size()!=layers.get(0).getInputCount())
        {
            throw new Exception("first layer input count doesnt match inData count");
        }        
        for(MyNeuronLayer<U> layer: layers)
        {            
            inData=layer.getOutput(inData);
        }
        return inData;
    }
    public void setWeights(List<U> weights) throws Exception
    {
        int index=0;
        for(MyNeuronLayer layer : layers)
        {
            layer.setWeights(weights.subList(index, layer.getInputCount()));
            index+=layer.getInputCount();
        }
    }
    public void setThresholds(List<U> thresholds) throws Exception
    {
        int totalThresholds = getTotalNeuronCount();
        if(thresholds.size()!=totalThresholds)
        {
            throw new Exception("total threshold count doesnt match the total number of neurons in the network");
        }
        int neuronIndex=0;
        for(MyNeuronLayer<U> layer: layers)
        {
            layer.setThresholds( thresholds.subList(neuronIndex, layer.getNeuronCount()));
        }
    }
    public int getTotalNeuronCount()
    {
        return layers.stream().mapToInt(l->l.getNeuronCount()).sum();
    }
    public int getTotalInputCount()
    {
        return layers.stream().mapToInt(l->l.getInputCount()).sum();
    }
}
