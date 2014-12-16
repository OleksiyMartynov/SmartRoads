package smartroads.brain.neural;

import java.util.List;

/**
 *
 * @author Oleksiy
 * @param <U>
 */
public interface IMyNeuronFunction <U extends Number>
{    
    public U process(List<U>inputs,List<U>weights,U threshold) throws Exception;
}
