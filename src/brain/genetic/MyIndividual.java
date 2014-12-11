package brain.genetic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleksiy
 * @param <U>
 */
public class MyIndividual
{
    private List<Integer> data = new ArrayList<>();
    private boolean isMutated =false;
    private boolean allowDataSizeMutation=true;
    private boolean allowMutationProbabilityDrift=true;
    private double mutationProbability; 
    private IMyFitnessTestFunction fitnessTest;
    public MyIndividual(int dataSize, boolean allowDataSizeMutation, double mutationProbabilityPerGeneration, boolean allowMutationProbabilityDrift, IMyFitnessTestFunction fitnessTest) throws Exception
    {
        if(dataSize>0)
        {
            this.data= makeRandomList(dataSize);
            init(allowDataSizeMutation, mutationProbabilityPerGeneration, allowMutationProbabilityDrift,fitnessTest);
        }
        else
        {
            throw new Exception("invalid dataSize parameter.");
        }
    }
    public MyIndividual(List<Integer> data, boolean isMutated, boolean allowDataSizeMutation, double mutationProbabilityPerGeneration , boolean allowMutationProbabilityDrift,  IMyFitnessTestFunction fitnessTest) throws Exception
    {
        if(data!=null||!data.isEmpty())
        {
            this.data=data;
            this.isMutated=isMutated;
            init(allowDataSizeMutation, mutationProbabilityPerGeneration, allowMutationProbabilityDrift, fitnessTest);
        }
        else
        {
            throw new Exception("Data array is null or empty.");
        }
    }
    private void init(boolean allowDataSizeMutation, double mutationProbabilityPerGeneration , boolean allowMutationProbabilityDrift,  IMyFitnessTestFunction fitnessTest) throws Exception
    {
        if(mutationProbabilityPerGeneration>=0&&mutationProbabilityPerGeneration<=1.0)
        {
            this.allowDataSizeMutation=allowDataSizeMutation;
            this.allowMutationProbabilityDrift=allowMutationProbabilityDrift;
            this.mutationProbability=mutationProbabilityPerGeneration;
            if(fitnessTest!=null)
            {
                this.fitnessTest=fitnessTest;
            }
            else
            {
                throw new Exception("fitnesTestFunction cannot be null");
            }
        }
        else
        {
            throw new Exception("invalid mutationProbability paramiter. Must be between 0 and 1");
        }
        
    }
    public MyIndividual getOffspring(MyIndividual other)
    {
        return null;
    }
    public float getFitness()
    {
        return 0;
    }    
    public List<Integer> getData()
    {
        return data;
    }
    public boolean isMutated()
    {
        return this.isMutated;
    }
    private List<Integer> makeRandomList(int size)
    {
        List<Integer> d= new ArrayList<>(size);
        for(int i =0; i< d.size(); i++)
        {
            d.set(i, (int)(Math.random()*(Integer.MAX_VALUE-1)));
        }
        //todo test random list
        return d;
    }
}
