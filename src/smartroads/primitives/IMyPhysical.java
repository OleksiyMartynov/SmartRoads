/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartroads.primitives;

/**
 *
 * @author Oleksiy
 */
public interface IMyPhysical
{
    public void translate(MyPoint vec);
    public void rotateByDeg(MyPoint pivotPoint, double angleDegrees);
}
