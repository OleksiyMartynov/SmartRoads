package smartroads.brain.neural;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import smartroads.brain.genetic.IMyFitnessTestFunction;
import smartroads.brain.genetic.IMyRandomDataFunction;
import smartroads.brain.genetic.MyPopulation;

/**
 *
 * @author Oleksiy
 * @param <U>
 */
public class MyNeuronNetwork<U extends Number>
{
    private List<MyNeuronLayer<U>> layers;
    IMyNeuronFunction<U> calcFunc;
    private int inputDataSize;
    private int outputDataSize;
    private int totalDataCount;
    private static final int inputsPerNeuron=2;
    private boolean initCalled=false;
    public MyNeuronNetwork(int inputs, int outputs,IMyNeuronFunction<U> calcFunc) throws Exception
    {
        if((inputs & (inputs - 1)) != 0)// if not power of two
        {
            throw new Exception("input number must be a power of two");
        }
        if((outputs & (outputs - 1)) != 0 && outputs !=1)//if not power of two or number 1
        {
            throw new Exception("outputs number must be a power of two or number 1 but it is "+outputs);
        }
        if(calcFunc==null)
        {
            throw new Exception("calcFunc cannot be null");
        }
        this.calcFunc=calcFunc;
        inputDataSize=inputs;
        outputDataSize=outputs;
        
        int totalWeights =0;
        int totalNeurons=0;
        int inputsCopy=inputs;
        while(inputsCopy!=outputs)
        {
            totalWeights+=inputsCopy;
            inputsCopy/=inputsPerNeuron;
            totalNeurons+=inputsCopy;
        }
        this.totalDataCount=totalWeights+totalNeurons;      
        
    }
    public void init(List<U>weightsAndThresholds) throws Exception
    {
        if(weightsAndThresholds==null||weightsAndThresholds.size()<inputsPerNeuron+1)//inputsPerNeuron+1 because it must contain atleast 1 threshold
        {
            throw new Exception("weightsAndThresholds list is null or to small to make one neuron");
        }
        
        this.initCalled = true;
        int inputs= inputDataSize;
        int outputs =outputDataSize;
        layers = new ArrayList<>();
        int index=0;
        //System.out.println("size:"+weightsAndThresholds.size());
        while(inputs!=outputs)
        {
           inputs/=inputsPerNeuron;    //neurons per layer       
           List<MyNeuron<U>> neurons = new ArrayList<>();
           for(int i=0; i<inputs; i++)
           {
               //System.out.println("i:"+i);
               neurons.add(new MyNeuron<>(weightsAndThresholds.subList(index, index+inputs),calcFunc,weightsAndThresholds.get(++index)));
               index+=inputs;
           }
           layers.add(new MyNeuronLayer<>(neurons));           
        }
    }
    public List<U> getOutput(List<U>inData) throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() was not called before getOutput");
        }
        if(inData.size()!=inputDataSize)
        {
            throw new Exception("inData size doesnt match promissed size. inData size is "+ inData.size()+" but should be "+inputDataSize);
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
    /*
    public void setWeights(List<U> weights) throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() was not called before setWeights");
        }
        int index=0;
        for(MyNeuronLayer layer : layers)
        {
            layer.setWeights(weights.subList(index, layer.getInputCount()));
            index+=layer.getInputCount();
        }
    }
    public void setThresholds(List<U> thresholds) throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() was not called before setThresholds");
        }
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
    }*/
    public int getTotalNeuronCount() throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() was not called before getTotalNeuronCount");
        }
        return layers.stream().mapToInt(l->l.getNeuronCount()).sum();
    }
    public int getTotalInputCount() throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() was not called before getTotalInputCount");
        }
        return layers.stream().mapToInt(l->l.getInputCount()).sum();
    }
    public int totalDataCountNeeded()
    {
        return totalDataCount;
    }
}
