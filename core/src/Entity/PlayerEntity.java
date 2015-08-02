/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Components.Collider;
import Components.Controller;
import Graphics.AnimationManager;
import Math.CoordinateTranslator;
import Math.Point2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
    private String directionMoving;
    /* Player's last direction moved to determine what sprite to draw*/
    private String directionFacing;
    /* Controller used to move player*/
    private Controller contr;
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
        directionMoving = "N";
        directionFacing = "D";
        hp = 10;
        animM = new AnimationManager();
        pAnim = animM.setPlayerAnimation(directionMoving);
        //this.gMap = gMap;
        contr = new Controller();


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
        contr.move(position, (int) t);
        directionMoving = contr.lastKeyPressed();

//            if (!isCollidingWorld() && position.getX() < 293.0)
//            {
//                position.setX(contr.getMovement(position, directionMoving, t));
//            }
//            else
//            {
//                position.setX(position.getX() - 1);
//            }

//            if (!isCollidingWorld() && position.getX() > 0.0)
//            {
//                position.setX(contr.getMovement(position, directionMoving, t));
//            }
//            else
//            {
//                position.setX(position.getX() + 1);
//            }

//            if (!isCollidingWorld() && position.getY() < 300.0)
//            {
//                position.setY(contr.getMovement(position, directionMoving, t));
//            }
//            else
//            {
//                position.setY(position.getY() - 1);
//            }

//            if (!isCollidingWorld() && position.getY() > 14)
//            {
//                position.setY(contr.getMovement(position, directionMoving, t));
//            }
//            else
//            {
//                position.setY(position.getY() + 1);
//            }



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
    public void setAnimation(String newAnim)
    {

        animM.setPlayerAnimation(newAnim);

    }

    /* Retrieve player's current animation (Used by SpriteRenderer)*/
    public Animation getAnimation()
    {
        return pAnim;
    }

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
    
    public String getDirMove()
    {
        return directionMoving;
    }
}
