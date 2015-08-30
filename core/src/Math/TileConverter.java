/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import java.awt.Point;

/**
 *
 * @author Bakuryu
 */
public class TileConverter
{

    public Point convertToTileCord(Point2D p)
    {
        double tx = p.getX();

        int ty = 36 - (int)p.getY();

        Point tilePoint = new Point((int) tx, ty);

        return tilePoint;
    }

    public Point2D convertFromTileCord(int x, int y)
    {
        double wX = x + 0.5;

        double wY = (36 - y) + 0.5;

        Point2D worldPoint = new Point2D(wX, wY);
        return worldPoint;
    }

}
