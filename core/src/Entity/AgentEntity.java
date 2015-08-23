/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import AgentStates.AgentChase;
import Components.Collider;
import Components.PreCollider;
import Graphics.AnimationManager;
import Graphics.GameMap;
import Math.NavGraph;
import Math.Point2D;
import Math.PointManager;
import Math.Vector2D;
import com.badlogic.gdx.graphics.g2d.Animation;
import java.util.LinkedList;

/**
 *
 * @author Bakuryu
 */
public class AgentEntity extends Entity
{

    private String enemyType;
    private AnimationManager animM;
    private Animation anim;
    private int speed;
    private int hp;
    private int dmg;
    private LinkedList<Point2D> path;
    private LinkedList<Point2D> backtrack;
    private boolean isBacktracking;
    private Point2D centerPos;
    private Collider hitBox;
    private boolean isAlive;
    private PlayerEntity p;
    private PointManager pointM;
    private boolean startScaredTimer;
    private long scaredStart;
    private int scaredCoolDown;
    private boolean hasDied;
    private boolean wasKilled;
    private String dir;
    private PreCollider preCol;
    private EntityState aCurState;
    private Point2D target;
    private GameMap gMap;
    private NavGraph navGraph;

    /**
     * Create an AgentEntity at location (x,y).
     *
     * @param x Agent's starting x coordinate
     * @param y Agent's starting y coordinate
     * @param type
     * @param p
     * @param pM
     */
    public AgentEntity(double x, double y, String type, PlayerEntity p, PointManager pM, GameMap gMap)
    {
        this.gMap = gMap;
        navGraph = new NavGraph(gMap);
        
        pointM = pM;
        this.p = p;
        isAlive = true;

        position = new Point2D(x, y);
        centerPos = new Point2D(x + 2, y + 4);
        enemyType = type;
        animM = new AnimationManager();
        hitBox = new Collider(position, 30, 29);
        path = new LinkedList<>();
        startScaredTimer = false;
        scaredCoolDown = 5;
        wasKilled = false;
        hasDied = false;
        dir = "L";
        preCol = new PreCollider(hitBox);
        target = new Point2D();
        createAgent();

    }

    /**
     * Update Agent's current animation based on delta time, execute the current
     * state, determine animation based on targets location from Agent, and
     * check for collision.
     *
     * @param t
     */
    @Override
    public void update(float t)
    {
        
        if (aCurState != null)
        {
            aCurState.Execute(this, (int)t); //May change Entity state to float deltatime 
        }

        if (enemyType == "Blinky" && aCurState instanceof AgentChase)
        {
            target = p.position;
        }


    }

    /**
     * Change the current state to nState, do current state's Exit method first,
     * then change state to nState, then do nState's Enter method.
     *
     * @param nState New state being changed to
     * @param t delta time
     */
    public void ChangeState(EntityState<AgentEntity> nState, int t)
    {
        aCurState.Exit(this, t);
        aCurState = nState;
        aCurState.Enter(this, t);
    }

    public void setAnimation(Animation a)
    {
        anim = a;
    }

    public Animation getAnimation()
    {
        return anim;
    }

    public void setSpeed(int s)
    {
        speed = s;
    }

    public void setHP(int h)
    {
        hp = h;
    }

    public void setDmg(int d)
    {
        dmg = d;
    }

    public void takeDmg(int dmg)
    {
        hp -= dmg;
        if (hp == 0)
        {
            if (!hasDied)
            {
                if (enemyType == "Blinky")
                {
                    pointM.addPoints(1);
                }

                if (enemyType == "Inky")
                {
                    pointM.addPoints(1);
                }

                if (enemyType == "Pinky")
                {
                    pointM.addPoints(1);
                }

                if (enemyType == "Clyde")
                {
                    pointM.addPoints(1);
                }
            }
            isAlive = false;
            hasDied = false;
        }
    }

    public boolean isAgentNear(AgentEntity a, Point2D dest)
    {
        boolean closeX = false;
        boolean closeY = false;
        if (Math.abs(a.getPosition().getX() - dest.getX()) < 0.5)
        {
            closeX = true;
        }

        if (Math.abs(a.getPosition().getY() - dest.getY()) < 0.5)
        {
            closeY = true;
        }

        return (closeX && closeY);
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void Die()
    {
        hasDied = true;

    }

    public boolean wasKilled()
    {
        return wasKilled;
    }

    public boolean isCollidingPlayer()
    {
        boolean pCollision = false;
        if (hitBox.checkEntityCollision(this.getCollider(), p.getCollider()))
        {
            pCollision = true;
        }
        return pCollision;
    }

    public void setPath(LinkedList<Point2D> path)
    {
        this.path = path;
    }

    public Point2D getCenterPos()
    {
        return centerPos;
    }

    public Collider getCollider()
    {
        return hitBox;
    }

    public void isScared(boolean b)
    {
        isBacktracking = b;
        startScaredTimer = true;
        scaredStart = System.currentTimeMillis();
    }
    
    public PreCollider getPreCol()
    {
        return preCol;
    }
    
    public Point2D getTarget()
    {
        return target;
    }

    private void createAgent()
    {
        switch (enemyType)
        {
            case "Blinky":
                setAnimation(animM.setAgentAnimation(enemyType, dir));
                setSpeed(20);
                setHP(4);
                setDmg(2);
                break;
            case "Inky":
                setAnimation(animM.setAgentAnimation(enemyType, dir));
                setSpeed(15);
                setHP(7);
                setDmg(4);
                break;
            case "Pinky":
                setAnimation(animM.setAgentAnimation(enemyType, dir));
                setSpeed(35);
                setHP(3);
                setDmg(1);
                break;
            case "Clyde":
                setAnimation(animM.setAgentAnimation(enemyType, dir));
                setSpeed(25);
                setHP(5);
                setDmg(3);
                break;

        }
    }
}
