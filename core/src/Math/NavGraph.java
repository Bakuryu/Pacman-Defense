/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import Graphics.GameMap;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Bakuryu
 */
public class NavGraph
{

    private GameMap gMap;
    private TileConverter tConv;
    private Point2D upNeighborPoint;
    private Point2D leftNeighborPoint;
    private Point2D rightNeighborPoint;
    private Point2D downNeighborPoint;
    private Point pTilePos;

    public NavGraph(GameMap gMap)
    {
        this.gMap = gMap;
        tConv = new TileConverter();
    }

    public Iterable<Point2D> getNeightbors(Point2D p)
    {
        ArrayList<Point2D> ret = new ArrayList<>();
        boolean[][] validPath = gMap.getAIPaths();

        pTilePos = new Point(tConv.convertToTileCord(p));

        //Up
        if (pTilePos.y - 1 > -1)
        {
            if (validPath[pTilePos.x][pTilePos.y - 1])
            {
                upNeighborPoint = new Point2D(tConv.convertFromTileCord(pTilePos.x, pTilePos.y - 1));
                ret.add(upNeighborPoint);
            }
        }

        //Left
        if (pTilePos.x - 1 > -1)
        {
            if (validPath[pTilePos.x - 1][pTilePos.y])
            {
                leftNeighborPoint = new Point2D(tConv.convertFromTileCord(pTilePos.x - 1, pTilePos.y));
                ret.add(leftNeighborPoint);
            }
        }

        //Down
        if (pTilePos.y + 1 < 37)
        {
            if (validPath[pTilePos.x][pTilePos.y + 1])
            {
                downNeighborPoint = new Point2D(tConv.convertFromTileCord(pTilePos.x, pTilePos.y + 1));
                ret.add(downNeighborPoint);
            }
        }

        //Right
        if (pTilePos.x + 1 < 55)
        {
            if (validPath[pTilePos.x + 1][pTilePos.y])
            {
                rightNeighborPoint = new Point2D(tConv.convertFromTileCord(pTilePos.x + 1, pTilePos.y));
                ret.add(rightNeighborPoint);
            }
        }
        return ret;

    }
}
