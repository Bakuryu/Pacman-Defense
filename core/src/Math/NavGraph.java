/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import Graphics.GameMap;
import java.util.ArrayList;

/**
 *
 * @author Bakuryu
 */
public class NavGraph
{

    private GameMap gMap;

    public NavGraph(GameMap gMap)
    {
        this.gMap = gMap;
    }

    public Iterable<Point2D> getNeightbors(Point2D p)
    {
        ArrayList<Point2D> ret = new ArrayList<>();
        boolean[][] validPath = gMap.getAIPaths();

        if (validPath[((int) p.getX()) - 1][(int) p.getY()] && validPath[((int) p.getX()) - 1][((int) p.getY()) + 1])
        {
            ret.add(new Point2D(p.getX() - 1, p.getY() + 1));
        }

        if (validPath[(int) p.getX()][((int) p.getY()) + 2] && validPath[((int) p.getX()) + 1][((int) p.getY()) + 2])
        {
            ret.add(new Point2D(p.getX() + 1, p.getY() + 2));
        }

        if (validPath[(int) p.getX()][((int) p.getY()) - 1] && validPath[((int) p.getX()) + 1][((int) p.getY()) - 1])
        {
            ret.add(new Point2D(p.getX() + 1, p.getY() - 1));
        }

        if (validPath[((int) p.getX()) + 2][(int) p.getY()] && validPath[((int) p.getX()) + 2][((int) p.getY()) + 1])
        {
            ret.add(new Point2D(p.getX() + 2, p.getY() + 1));
        }
        
        return ret;

    }
}
