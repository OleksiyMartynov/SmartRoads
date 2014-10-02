/*

 */

package smartroads;

import smartroads.primitives.MyLine;
import smartroads.primitives.MyPoint;

/**
 *
 * @author Oleksiy
 */
public class MyStartClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {        
        MyLine lOne = new MyLine(new MyPoint(0.0f,0.0f), new MyPoint(0.0f,5.0f));
        MyLine lTwo = new MyLine(new MyPoint(0.1f, 1.0f), new MyPoint(2.0f,6.0f));
        boolean status= lOne.isIntersecting(lTwo);
        System.out.println("intersect:"+status);
    }
    
}
