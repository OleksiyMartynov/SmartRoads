package smartroads.brain.neural;

import java.util.List;
import smartroads.brain.genetic.IMyFitnessTestFunction;
import smartroads.brain.genetic.IMyRandomDataFunction;
import smartroads.brain.genetic.MyPopulation;

/**
 *
 * @author Oleksiy
 * @param <U>
 */
public abstract class MyNeuronNetworkTester<U extends Number>
{
    MyNeuronNetwork<U> network;
    MyPopulation<U> population;
    private boolean initCalled=false;
    public MyNeuronNetworkTester(int inputs,int outputs,  IMyNeuronFunction<U> calcFunction) throws Exception
    {
        network=new MyNeuronNetwork<>(inputs,outputs,calcFunction);        
    }
    public void init(IMyFitnessTestFunction<U> tFunc, IMyRandomDataFunction<U> rFunc) throws Exception
    {
        population=new MyPopulation<>(50,network.totalDataCountNeeded(),tFunc,rFunc,false);
        initCalled=true;
    }
    public void setNetworkWeightsAndThresholds(List<U> weightAndThresholds) throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() must be called before setNetworkWeightsAndThresholds");
        }
        network.init(weightAndThresholds);
    }
    public List<U> getNetworkOutput(List<U>inData) throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() must be called before getNetworkOutput");
        }
        network.init(inData);
        return network.getOutput(inData);
    }
    public void evolveNetwork() throws Exception
    {        
        if(!initCalled)
        {
            throw new Exception("init() must be called before evolveNetwork");
        }
        population.nextGeneration();
    }
}
