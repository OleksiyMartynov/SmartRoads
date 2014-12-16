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
    protected MyNeuronNetworkTester(int inputs,int outputs,  IMyNeuronFunction<U> calcFunction) throws Exception
    {
        network=new MyNeuronNetwork<>(inputs,outputs,calcFunction);        
    }
    protected void init(IMyFitnessTestFunction<U> tFunc, IMyRandomDataFunction<U> rFunc) throws Exception
    {
        population=new MyPopulation<>(50,network.totalDataCountNeeded(),tFunc,rFunc,false);
        initCalled=true;
    }
    protected void setNetworkWeightsAndThresholds(List<U> weightAndThresholds) throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() must be called before setNetworkWeightsAndThresholds");
        }
        network.init(weightAndThresholds);
    }
    public void resetWeightsAndThresholds(List<U>inData) throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() must be called before getNetworkOutput");
        }
        network.init(inData);
    }
    public List<U> getNetworkOutput(List<U>inData) throws Exception
    {
        if(!initCalled)
        {
            throw new Exception("init() must be called before getNetworkOutput");
        }
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
    public int getFitness()
    {
        return population.getAverageFitness();
    }
    protected MyPopulation<U> getPopulation()
    {
        return this.population;
    }
}
