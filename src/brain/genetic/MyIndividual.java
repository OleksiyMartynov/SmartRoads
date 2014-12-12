package brain.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private Integer fitness =null;
    private IMyFitnessTestFunction fitnessTest;
    public static final int MIN_DATA_SIZE =0;
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
    public MyIndividual getOffspring(MyIndividual other) throws Exception
    {
        if(this!=other)
        {
            List<Integer> d= mixTwoLists(data, other.getData());
            //System.out.println("Creating offspring:");
            if(this.allowMutationProbabilityDrift)
            {
                double r =Math.random();
                if(r<0.5)
                {
                    mutationProbability*=1.0-r;
                }
                else
                {
                    mutationProbability*=1.0+r;
                }
                if(mutationProbability>1.0)
                {
                    mutationProbability=1.0;
                }
                else if(mutationProbability<0)
                {
                    mutationProbability=0;
                }
            }
            //System.out.println("-mutation probablity [0..1]:"+mutationProbability);
            double r = Math.random();
            Random rand= new Random();   
            if(r<mutationProbability) 
            {       
                //System.out.println("-has mutation");
                if(allowDataSizeMutation&&r<mutationProbability/d.size())// try and make a new random list 
                {
                    double s =Math.random();
                    int newSize =data.size();
                    if(s<0.5)
                    {
                        newSize*=1.0-s;
                    }
                    else
                    {
                        newSize*=1.0+s;
                    }
                    if(newSize<MIN_DATA_SIZE)
                    {
                        newSize=data.size();
                    }
                    System.out.println("-data size mutated to "+newSize);
                    return new MyIndividual(makeRandomList(newSize),true, allowDataSizeMutation, (mutationProbability+other.mutationProbability)/2, allowMutationProbabilityDrift, fitnessTest);
                }
                else//make one item random
                {
                    //System.out.println("-data item mutated");
                    return new MyIndividual(mutateRandomItem(d),true, allowDataSizeMutation, mutationProbability, allowMutationProbabilityDrift, fitnessTest);
                }

            }
            return new MyIndividual(d,false, allowDataSizeMutation, mutationProbability, allowMutationProbabilityDrift, fitnessTest);
        }
        else
        {
            throw new Exception("cannot mix two of the same instance");
        }
    }
    private List<Integer>mixTwoLists(List<Integer> l1, List<Integer> l2)
    {
        
        int newSize =(l1.size()+l2.size())/2;
        //System.out.println("newSize"+newSize);
        List<Integer> newList = new ArrayList<>();
        for(int i=0; i<newSize; i++)
        {
            newList.add(0);
        }
        boolean flag = true;
        Random r= new Random();
        for(int i =0; i<newSize; i++)
        {
            if(Math.random()<0.5)
            {                
                if(i<l1.size())
                {
                    newList.set(i, l1.get(i));
                }
                else
                {
                    newList.set(i, l2.get(i));
                }
            }
            else
            {
                if(i<l2.size())
                {
                    newList.set(i, l2.get(i));
                }
                else
                {
                    newList.set(i, l1.get(i));
                }
            }
        }
        //debugging
        /*
        System.out.println("List one");
        l1.forEach(x->{System.out.println(""+x);});
        System.out.println("List two");
        l2.forEach(y->{System.out.println(""+y);});
        System.out.println("List mixed");
        newList.forEach(z->{System.out.println(""+z);});
                */
        return newList;
    }
    public int getFitness()
    {
        if(fitness==null)
        {
            fitness =this.fitnessTest.testFitness(this.data);
        }
        return fitness;
    }    
    public List<Integer> getData()
    {
        return data;
    }
    public boolean isMutated()
    {
        return this.isMutated;
    }
    private List<Integer> mutateRandomItem(List<Integer> list)
    {
        Random r = new Random();
        int rIndex= r.nextInt(list.size());
        list.set(rIndex, r.nextInt());
        return list;
    }
    private List<Integer> makeRandomList(int size)
    {
        Random r = new Random();
        List<Integer> d= new ArrayList<>();
        for(int i =0; i< size; i++)
        {
            d.add( r.nextInt());
        }
        return d;
    }
}
