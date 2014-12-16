package smartroads.brain.neural;

/**
 *
 * @author Oleksiy
 */
public class MyDataConverter
{
    public static double mapIntegerToSpecialDouble(Integer in) //returns double between 0 and 1
    {
        if(in>=0)
        {
            return (double)(in/2+Integer.MAX_VALUE/2)/(double)Integer.MAX_VALUE;
        }
        else
        {
            return (double)(in/2+Integer.MAX_VALUE/2)/(double)Integer.MIN_VALUE;
        }
    }
    public static int mapSpecialDoubleToInteger(double in) throws Exception
    {
        if(in>1.0||in<0)
        {
            throw new Exception("Not a special double. must be between 0 and 1");
        }
        in=in*2.0-1.0;
        if(in>=0&&in<0.5)
        {
            return (int)(Integer.MAX_VALUE*in);
        }        
        else
        {
            return (int)(Integer.MIN_VALUE*in);
        }
    }
    
}
