/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 *
 * @author Bakuryu
 */
public class PointManager
{

    private int tPoints;

    public PointManager()
    {
        tPoints = 0;
    }

    public void addPoints(int points)
    {
        tPoints += points;
    }

    public void subPoints(int points)
    {
        tPoints -= points;
    }
    
    public int getPoints()
    {
        return tPoints;
    }
}
