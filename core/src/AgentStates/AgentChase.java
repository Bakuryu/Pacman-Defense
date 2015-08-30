/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgentStates;

import Entity.AgentEntity;
import Entity.Entity;
import Entity.EntityState;
import Entity.PlayerEntity;
import Math.Point2D;
import Math.Vector2D;
import java.util.LinkedList;

/**
 *
 * @author Bakuryu
 */
public class AgentChase extends EntityState<AgentEntity>
{

    private double minDist;
    private boolean goLeft;
    private boolean goRight;
    private boolean goUp;
    private boolean goDown;
    private LinkedList<Point2D> path;
    private Vector2D toTarget;
    private EntityState<Entity> nState;
    private Point2D target;
    private Point2D moveTo;

    public AgentChase(AgentEntity a, Point2D target)
    {
        toTarget = new Vector2D(0, 0);
        moveTo = new Point2D(0, 0);
        this.target = target;
        a.getPathF().generatePath(a.getCenterPos(), target);
        path = a.getPathF().getPath();
    }

    @Override
    public void Enter(AgentEntity a, int t)
    {
        System.out.println("BLINKYS ON THE MOVE!");
        a.getPathF().generatePath(a.getCenterPos(), target);
        path = a.getPathF().getPath();
    }

    @Override
    public void Execute(AgentEntity a, int t)
    {

        if (!path.isEmpty() && !(isAgentNear(a, path.getFirst())))
        {

            double nX, nY;
            toTarget = a.getTargetDistDir(path.getFirst());//new Vector2D(foodLoc.getX()- e.getPosition().getX(), foodLoc.getY() - e.getPosition().getY());
            toTarget.normalize();
            nX = a.getCenterPos().getX() + toTarget.getX() * a.getSpeed() * t / 1000;
            nY = a.getCenterPos().getY() + toTarget.getY() * a.getSpeed() * t / 1000;
//            System.out.println("NX: " + nX + " NY: "+ nY);
            moveTo = new Point2D(nX, nY);

            a.getCenterPos().set(moveTo);
//            a.getPosition().setX(moveTo.getX() + 0.5);
//            a.getPosition().setX(moveTo.getY() + 0.5);
        }

        if (!path.isEmpty() && (isAgentNear(a, path.getFirst())))
        {
            path.remove();
        }

//        if (path.isEmpty())
//        {
//            a.getPathF().generatePath(a.getCenterPos(), target);
//            path = a.getPathF().getPath();
//        }
    }

    @Override
    public void Exit(AgentEntity a, int t)
    {
    }

    public boolean isAgentNear(AgentEntity a, Point2D dest)
    {
        boolean closeX = false;
        boolean closeY = false;
        if (Math.abs(a.getCenterPos().getX() - dest.getX()) < 0.1)
        {
            closeX = true;
        }

        if (Math.abs(a.getCenterPos().getY() - dest.getY()) < 0.1)
        {
            closeY = true;
        }

        return (closeX && closeY);
    }

    public void setTarget(Point2D target)
    {
        this.target = target;
    }

}
