/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Bakuryu
 */
public class PathFinder
{

    private NavGraph navGraph;
    private LinkedList<Point2D> path;
    private Queue<Point2D> pq;
    private ArrayList<Point2D> checked;
    private HashMap<Point2D, Double> hDist;
    private HashMap<Point2D, Double> pDist;
    private HashMap<Point2D, Point2D> parent;

    private Point2D prevPoint;
    private Point2D targetP;
    private TileConverter tConv;
    private LinkedList<Point> tilePath;

    public PathFinder(NavGraph nGraph)
    {
        navGraph = nGraph;
        path = new LinkedList<>();
        targetP = new Point2D();
        hDist = new HashMap<>();
        pDist = new HashMap<>();
        parent = new HashMap<>();
        checked = new ArrayList<>();
        pq = new LinkedList<>();
        tConv = new TileConverter();
        tilePath = new LinkedList<>();
    }

    public void generatePath(Point2D from, Point2D to)
    {
        tilePath.clear();
        checked.clear();
        pq.clear();
        parent.clear();
        parent.put(from, prevPoint);
        pDist.put(from, 0.0);
        pq.add(from);

        while (!pq.isEmpty())
        {
            Point2D currentPoint = (Point2D) pq.poll();

            if (to.getX() == currentPoint.getX() && to.getY() == currentPoint.getY() || isNear(to, currentPoint))
            {
                targetP = currentPoint;
                hDist.clear();
                pDist.clear();

                break;
            }

            for (Point2D p : navGraph.getNeightbors(currentPoint))
            {
                if (!checked.contains(p))
                {
                    pDist.put(p, pDist.get(currentPoint) + 1);
                    hDist.put(p, pDist.get(p) + getH(p, to));
                    if (parent.get(p) != currentPoint)
                    {
                        if (!p.equals(from))
                        {
                            parent.put(p, currentPoint);
                        }
                    }
                }
            }

            Point2D minP = new Point2D();
            Double min = Double.POSITIVE_INFINITY;

            for (HashMap.Entry<Point2D, Double> h : hDist.entrySet())
            {
                if (min.compareTo(h.getValue()) > 0)
                {
                    minP = h.getKey();
                    min = h.getValue();
                }
            }
            if (!checked.contains(minP))
            {
                pq.add(minP);
                hDist.remove(minP);
                checked.add(minP);
            }
        }
    }

    public LinkedList<Point2D> getPath()
    {
        while (targetP != null)
        {
            path.add(targetP);
            tilePath.add(tConv.convertToTileCord(targetP));
            targetP = parent.get(targetP);

        }
        Collections.reverse(path);
        Collections.reverse(tilePath);
        return path;
    }

    public boolean isNear(Point2D to, Point2D from)
    {
        boolean closeX = false;
        boolean closeY = false;
        if (Math.abs(to.getX() - from.getX()) <= 0.5)
        {
            closeX = true;
        }

        if (Math.abs(to.getY() - from.getY()) <= 0.5)
        {
            closeY = true;
        }

        return (closeX && closeY);
    }

    public double getH(Point2D from, Point2D to)
    {
        double x = Math.abs(from.getX() - to.getX());
        double y = Math.abs(from.getY() - to.getY());
        return (x + y);
    }

}
