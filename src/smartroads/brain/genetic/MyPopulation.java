package smartroads.brain.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Oleksiy
 * @param <U>
 */
public class MyPopulation <U extends Number>
{    
    private List<MyIndividual<U>> population;
    int generationCount=0;
    public MyPopulation(int populationSize,int dataSize,IMyFitnessTestFunction<U> fitnessTester, IMyRandomDataFunction<U> rFunc, boolean allowDataSizeMutation) throws Exception
    {
        if(populationSize<4)
        {
            throw new Exception("population size should be 4 or greater");
        }
        if(dataSize<MyIndividual.MIN_DATA_SIZE)
        {
            throw new Exception("data size should be 4 or greater");
        }
        if(fitnessTester==null)
        {
            throw new Exception("fitnessTester function Cannot be null");
        }
        population=new ArrayList<>();
        try
        {
            for(int i =0; i<populationSize;i++)
            {                
                population.add( new MyIndividual(dataSize, false, 0.99, true, fitnessTester, rFunc));
            }
        }
        catch(Exception e)
        {
            System.err.println("ex in individual's constructor:"+e.getMessage());
        }
    }
    public MyPopulation(int populationSize,int dataSize,IMyFitnessTestFunction<U> fitnessTester, IMyRandomDataFunction<U> rFunc) throws Exception
    {
        if(populationSize<4)
        {
            throw new Exception("population size should be 4 or greater");
        }
        if(dataSize<MyIndividual.MIN_DATA_SIZE)
        {
            throw new Exception("data size should be 4 or greater");
        }
        if(fitnessTester==null)
        {
            throw new Exception("fitnessTester function Cannot be null");
        }
        population=new ArrayList<>();
        try
        {
            for(int i =0; i<populationSize;i++)
            {                
                population.add( new MyIndividual(dataSize, true, 0.99, true, fitnessTester, rFunc));
            }
        }
        catch(Exception e)
        {
            System.err.println("ex in individual's constructor:"+e.getMessage());
        }
    }
    public void nextGeneration() throws Exception
    {
        generationCount++;
        List<MyIndividual<U>> left,right;
        left=new ArrayList<>();
        right=new ArrayList<>();
        for(int i =0; i<population.size(); i++)
        {
            if(i%2==0)
            {
                left.add(population.get(i));
            }
            else
            {
                right.add(population.get(i));
            }
        }
        
        List<MyIndividual<U>> newPopulation = new ArrayList<>();
        left=left.stream().sorted((one,two)-> two.getFitness()-one.getFitness()).collect(Collectors.toList());
        right=right.stream().sorted((one,two)-> two.getFitness()-one.getFitness()).collect(Collectors.toList());
        
        for(int i =0; i<left.size(); i++)
        {
            //System.out.println("i"+i);
            try
            {
                if(Math.random()<0.25)//might need tweekeing
                {
                    newPopulation.add(left.get(i).getOffspring(right.get(i)));
                    newPopulation.add(right.get(i).getOffspring(left.get(i)));
                    newPopulation.add(left.get(i).getOffspring(right.get(i)));
                }
                else
                {
                    newPopulation.add(left.get(i).getOffspring(right.get(i)));
                    newPopulation.add(right.get(i).getOffspring(left.get(i)));
                }
                
            }
            catch (Exception e)
            {
                System.err.println("i:"+i+" ex:"+e.getMessage());
            }
        }
        if(newPopulation.size()<population.size())
        {
            newPopulation.add(left.get(1).getOffspring(right.get(1)));
        }
        this.population = newPopulation.subList(0, population.size());  //maybe mutate the population size
        System.out.println("population size"+newPopulation.size());
    }

    public List<MyIndividual<U>> getPopulation()
    {
        return population;
    }
    
    public int getMaxFitness()
    {        
        return population.stream().mapToInt(i->i.getFitness()).max().getAsInt();
    }
    public int getAverageFitness()
    {        
        return population.stream().mapToInt(i->i.getFitness()).sum()/population.size();
    }
    public MyIndividual getMostFitIndividual()
    {
        return population.stream().sorted((a,b)->a.getFitness()-b.getFitness()).findFirst().get();
    }
    public int getGenerationCount()
    {
        return generationCount;
    }
    
}
