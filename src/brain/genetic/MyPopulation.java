package brain.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Oleksiy
 */
public class MyPopulation
{    
    private List<MyIndividual> population;
    private Integer maxFitness, avgFitness;
    public MyPopulation(int populationSize,int dataSize,IMyFitnessTestFunction fitnessTester)
    {
        population=new ArrayList<>();
        try
        {
            for(int i =0; i<populationSize;i++)
            {
                population.add( new MyIndividual(dataSize, true, 0.99, true, fitnessTester));
            }
        }
        catch(Exception e)
        {
            System.err.println("ex in population constructor:"+e.getMessage());
        }
    }
    public void nextGeneration() throws Exception
    {
        List<MyIndividual> left,right;
        left=population.subList(0, population.size()/2);
        right=population.subList(population.size()/2, population.size());
        List<MyIndividual> newPopulation = new ArrayList<>();
        left=left.stream().sorted((one,two)-> one.getFitness()-two.getFitness()).collect(Collectors.toList());
        right=right.stream().sorted((one,two)-> one.getFitness()-two.getFitness()).collect(Collectors.toList());
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
        System.out.println("new population size"+newPopulation.size());
    }
    public int getMaxFitness()
    {
        if(maxFitness==null)
        {
            maxFitness = population.stream().mapToInt(i->i.getFitness()).max().getAsInt();
        }
        return maxFitness;
    }
    public int getAverageFitness()
    {
        if(avgFitness==null)
        {
            avgFitness = population.stream().mapToInt(i->i.getFitness()).sum()/population.size();
        }
        return avgFitness;
    }
    public MyIndividual getMostFitIndividual()
    {
        return population.stream().sorted((a,b)->a.getFitness()-b.getFitness()).findFirst().get();
    }
}
