/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Components.Collider;
import Graphics.AnimationManager;
import Math.CoordinateTranslator;
import Math.Point2D;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 *
 * @author Bakuryu
 */
public class PlayerEntity extends Entity
{

    //Point2D position;
    /* Player's start position*/
    //private Point2D startPos;
    //private Point2D feetPos;
    /* Player's movement state (Left,Right,Up,Down,etc)*/
    //private String pMState;
    /* Player's last direction moved to determine what sprite to draw*/
    //private String pLDState;
    /* Controller used to move player*/
    //private Controller contr;
    /* Animation Manager to retrieve proper animation based on player movement*/
    private AnimationManager animM;
    /* Current animation to be draw by SpriteRenderer*/
    private Animation pAnim;
    /* Player's collision box*/
    private Collider col;
    /* Game map, to be use for map collision in the future*/
    //private GameMap gMap;
    private CoordinateTranslator corT;
    private int hp;

    /**
     * Create a new player entity, set it's location and initialize values
     *
     * @param x player entities starting x coordinate
     * @param y player entities starting y coordinate
     */
    public PlayerEntity(double x, double y)
    {

        //startPos = new Point2D(x, y);
        //feetPos = new Point2D(x+4.3125,y-13.0625);
        position = new Point2D(x, y);
        //pMState = "IDLE";
        //pLDState = "D";
        hp = 10;
        animM = new AnimationManager();
        pAnim = animM.setPlayerAnimation();
        //this.gMap = gMap;
        //contr = new Controller();


        /* Create collider box the same size as player sprite*/
        col = new Collider(position, 32, 32);

    }

    /**
     * Update player entity, get player inputs, update animation, and eventually
     * check for collision
     *
     * @param t
     */
    @Override
    public void update(float t)
    {
        System.out.println("HP: " + hp);
        /* Update player animation based on delta time to keep it in synch with game*/

        //feetPos.setX(position.getX()+4.3125);
        //feetPos.setY(position.getY()-13.0625);
        /**
         * Get player's input,set the appropriate animation and move character
         * in the appropriate direction based on controller's calculation.
         * (Eventually check for map collision to prevent movement)
         */
//        if (gc.getInput().isKeyDown(Input.KEY_RIGHT) && !(gc.getInput().isKeyDown(Input.KEY_RIGHT) && gc.getInput().isKeyDown(Input.KEY_UP)) && !(gc.getInput().isKeyDown(Input.KEY_RIGHT) && gc.getInput().isKeyDown(Input.KEY_DOWN)))
//        {
//            //if(position.getX() < 285.0)
//
//            //position.setX(position.getX() + t / 6.0);
//            if (pMState != "MRIGHT")
//            {
//                pMState = "MRIGHT";
//                pLDState = "R";
//                setAnimation();
//            }
//
//            if (!isCollidingWorld() && position.getX() < 293.0)
//            {
//                position.setX(contr.getMovement(position, pMState, t));
//            }
//            else
//            {
//                position.setX(position.getX() - 1);
//            }
//            //contr.moveEntity(this, pMState, t);
//
//        }
//
//        if (gc.getInput().isKeyDown(Input.KEY_LEFT) && !(gc.getInput().isKeyDown(Input.KEY_LEFT) && gc.getInput().isKeyDown(Input.KEY_UP)) && !(gc.getInput().isKeyDown(Input.KEY_LEFT) && gc.getInput().isKeyDown(Input.KEY_DOWN)))
//        {
//
//            //if(position.getX() > 0.0)
//            //position.setX(position.getX() - t / 6.0);
//            if (pMState != "MLEFT")
//            {
//                pMState = ("MLEFT");
//                pLDState = "L";
//                setAnimation();
//            }
//
//            if (!isCollidingWorld() && position.getX() > 0.0)
//            {
//                position.setX(contr.getMovement(position, pMState, t));
//            }
//            else
//            {
//                position.setX(position.getX() + 1);
//            }
//
//        }
//
//        if (gc.getInput().isKeyDown(Input.KEY_UP) && !(gc.getInput().isKeyDown(Input.KEY_LEFT) && gc.getInput().isKeyDown(Input.KEY_UP)) && !(gc.getInput().isKeyDown(Input.KEY_RIGHT) && gc.getInput().isKeyDown(Input.KEY_UP)))
//        {
//            //if(position.getY() < 300.0)
//            //position.setY(position.getY() + t / 6.0);
//            if (pMState != "MUP")
//            {
//                pMState = ("MUP");
//                pLDState = "U";
//                setAnimation();
//            }
//
//            if (!isCollidingWorld() && position.getY() < 300.0)
//            {
//                position.setY(contr.getMovement(position, pMState, t));
//            }
//            else
//            {
//                position.setY(position.getY() - 1);
//            }
//
//        }
//
//        if (gc.getInput().isKeyDown(Input.KEY_DOWN) && !(gc.getInput().isKeyDown(Input.KEY_LEFT) && gc.getInput().isKeyDown(Input.KEY_DOWN)) && !(gc.getInput().isKeyDown(Input.KEY_RIGHT) && gc.getInput().isKeyDown(Input.KEY_DOWN)))
//        {
//
//            if (pMState != "MDOWN")
//            {
//                pMState = ("MDOWN");
//                pLDState = "D";
//                setAnimation();
//            }
//            if (!isCollidingWorld() && position.getY() > 14)
//            {
//                position.setY(contr.getMovement(position, pMState, t));
//            }
//            else
//            {
//                position.setY(position.getY() + 1);
//            }
//
//        }
//
//        if (!gc.getInput().isKeyDown(Input.KEY_LEFT) && !gc.getInput().isKeyDown(Input.KEY_RIGHT) && !gc.getInput().isKeyDown(Input.KEY_UP) && !gc.getInput().isKeyDown(Input.KEY_DOWN))
//        {
//            if (pMState != "IDLE")
//            {
//                pMState = ("IDLE");
//                setAnimation();
//            }
//
//        }
//
//        if ((gc.getInput().isKeyDown(Input.KEY_UP) && gc.getInput().isKeyDown(Input.KEY_LEFT)))
//        {
//
//            if (pMState != "MULEFT")
//            {
//                pMState = ("MULEFT");
//                pLDState = "U";
//                setAnimation();
//            }
//
//            if (!isCollidingWorld() && position.getY() < 300.0 && position.getX() > 0.0)
//            {
//                position.setX(contr.getMovementD(position, pMState, t, "LX"));
//                position.setY(contr.getMovementD(position, pMState, t, "UY"));
//            }
//            else
//            {
//                position.setX(position.getX() + 1);
//                position.setY(position.getY() - 1);
//            }
//
//        }
//
//        if ((gc.getInput().isKeyDown(Input.KEY_DOWN) && gc.getInput().isKeyDown(Input.KEY_LEFT)) || (gc.getInput().isKeyDown(Input.KEY_LEFT) && gc.getInput().isKeyDown(Input.KEY_DOWN)))
//        {
//
//            if (pMState != "MDLEFT")
//            {
//                pMState = ("MDLEFT");
//                pLDState = "D";
//                setAnimation();
//            }
//
//            if (!isCollidingWorld() && position.getX() > 0.0 && position.getY() > 14.0)
//            {
//                position.setX(contr.getMovementD(position, pMState, t, "LX"));
//                position.setY(contr.getMovementD(position, pMState, t, "DY"));
//            }
//            else
//            {
//                position.setX(position.getX() + 1);
//                position.setY(position.getY() + 1);
//            }
//
//        }
//
//        if ((gc.getInput().isKeyDown(Input.KEY_UP) && gc.getInput().isKeyDown(Input.KEY_RIGHT)))
//        {
//
//            if (pMState != "MURIGHT")
//            {
//                pMState = ("MURIGHT");
//                pLDState = "U";
//                setAnimation();
//            }
//
//            if (!isCollidingWorld() && position.getY() < 300.0 && position.getX() < 291.0)
//            {
//                position.setX(contr.getMovementD(position, pMState, t, "RX"));
//                position.setY(contr.getMovementD(position, pMState, t, "UY"));
//            }
//            else
//            {
//                position.setX(position.getX() - 1);
//                position.setY(position.getY() - 1);
//            }
//
//        }
//
//        if (gc.getInput().isKeyDown(Input.KEY_DOWN) && gc.getInput().isKeyDown(Input.KEY_RIGHT))
//        {
//
//            if (pMState != "MDRIGHT")
//            {
//                pMState = ("MDRIGHT");
//                pLDState = "D";
//                setAnimation();
//            }
//
//            if (!isCollidingWorld() && position.getX() < 292 && position.getY() > 14.0)
//            {
//                position.setX(contr.getMovementD(position, pMState, t, "RX"));
//                position.setY(contr.getMovementD(position, pMState, t, "DY"));
//            }
//            else
//            {
//                position.setX(position.getX() - 1);
//                position.setY(position.getY() + 1);
//            }
//
//        }
    }

    public void takeDmg(int dmg)
    {
        hp -= dmg;
    }

    public void setCordT(CoordinateTranslator corT)
    {
        this.corT = corT;
    }
    /* Check if player is colliding with the world*/

//    public boolean isCollidingWorld()
//    {
//        boolean collided = false;
//        int playerTX = (int)convertToTileCord(feetPos).getX();
//        int playerTY = (int)convertToTileCord(feetPos).getY();
//        boolean[][] blocked = gMap.getBlocked();
//        if(blocked[playerTX][playerTY])
//        {
//            collided = true;
//        }
//        
//
//        return collided;
//        //return false;
//    }

    /* Set player's animation according to player current movement or direction state*/
    public void setAnimation()
    {

        pAnim = animM.setPlayerAnimation();

    }

    /* Retrieve player's current animation (Used by SpriteRenderer)*/
    public Animation getAnimation()
    {
        return pAnim;
    }

    /* Retrieve player's current movement state (Used by Controller)*/
//    public String getMState()
//    {
//        return pMState;
//    }
//
//    /* Retrieve player's last movement state (Used by Controller)*/
//    public String getLDState()
//    {
//        return pLDState;
//    }

    /* Retrieve player's collision box (Used by AgentEntity)*/
    public Collider getCollider()
    {
        return col;
    }

    /* Reset players position to it's starting position (Used by AgentEntity)*/
//    public void resetPos()
//    {
//        position.set(startPos);
//    }
//    
//    public Point2D getFeetPos()
//    {
//        return feetPos;
//    }
//    public void setStartPos(Point2D sP)
//    {
//        startPos = sP;
//    }
//    
//    public void setNewMap(GameMap map)
//    {
//        gMap = map;
//    }
    public int getHP()
    {
        return hp;
    }
}
