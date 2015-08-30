/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Components.Collider;
import Components.Controller;
import Components.PreCollider;
import Graphics.AnimationManager;
import Graphics.GameMap;
import Math.CoordinateTranslator;
import Math.Point2D;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

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
    private GameMap gMap;
    /* Player's hurt box*/
    private Collider hurtBox;
    private CoordinateTranslator corT;
    private PreCollider preCol;
    private Point2D centerPos;

    /**
     * Create a new player entity, set it's location and initialize values
     *
     * @param x player entities starting x coordinate
     * @param y player entities starting y coordinate
     */
    public PlayerEntity(double x, double y, GameMap gMap)
    {

        //startPos = new Point2D(x, y);
        //feetPos = new Point2D(x+4.3125,y-13.0625);
        position = new Point2D(x, y);
        centerPos = new Point2D(x + 0.5, y + 0.5);
        directionMoving = "N";
        directionFacing = "D";
        animM = new AnimationManager();
        pAnim = animM.setPlayerAnimation(directionMoving);
        this.gMap = gMap;
        /* Create collider box the same size as player sprite*/
        col = new Collider(position.getX(), position.getY(), 16, 16);
        hurtBox = new Collider(position.getX(), position.getY(), 16, 16);
        preCol = new PreCollider(gMap.getMapCollisions(), col);
        contr = new Controller(gMap.getMapCollisions(), col, preCol);

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

//        if (!col.checkWorldCollisions(col, gMap.getMapCollisions()))
//        {
        contr.move(position, (int) t);
        centerPos.setX(position.getX() + 0.5);
        centerPos.setY(position.getY() + 0.5);

        //hurtBox.updatePos(position);
//        }
        directionMoving = contr.lastKeyPressed();
//        System.out.println("Pos :" + position);
//        System.out.println("CentPos : " + centerPos);
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

    public Rectangle getPCB()
    {
        return contr.getPreColBox();
    }

    public Collider getHurtBox()
    {
        return hurtBox;
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

    public String getDirMove()
    {
        return directionMoving;
    }

    public Point2D getCentPos()
    {
        return centerPos;
    }
}
