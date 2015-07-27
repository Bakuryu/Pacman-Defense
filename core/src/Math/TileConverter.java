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
        int tx = (int) (p.getX() / 2.5);
        double hMY = p.getY();
        double hDTF = 100.0 / 35.0;
        double total = hMY / hDTF;

        int ty = (int) total;

        Point tilePoint = new Point(tx, ty);

        return tilePoint;
    }

    public Point2D convertFromTileCord(int x, int y)
    {
        double wX = 0;

        wX = x * 2.5;
        double hDTF = (double) 100 / 35;
        double wY = (y * hDTF);

        Point2D worldPoint = new Point2D(wX, wY);
        return worldPoint;
    }

}
