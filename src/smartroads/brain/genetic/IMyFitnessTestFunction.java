package smartroads.brain.genetic;

import java.util.List;

/**
 *
 * @author Oleksiy
 * @param <U>
 */
public interface IMyFitnessTestFunction<U extends Number>
{
    public int testFitness(List<U> list);
}
