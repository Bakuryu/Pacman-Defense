/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Math.Point2D;
import com.badlogic.gdx.math.Rectangle;
import java.awt.Point;

import java.util.ArrayList;

/**
 *
 * @author Bakuryu
 */
public class Collider
{
    /* Variables used to store the hitbox's width and height*/

    int hitBoxW, hitBoxH;
    /* Rectangle used to represent collider's hitbox*/
    Rectangle hBox;

    /**
     * Create a collider with it's hitbox origin starting at entities origin pos
     * and it's width being hBw and it's height hBH.
     *
     * @param pos entities origin pos
     * @param hBW hitbox collider's width
     * @param hBH hitbox collider's height
     */
    public Collider(Point2D pos, int hBW, int hBH)
    {
        hBox = new Rectangle();

        hitBoxW = hBW;
        hitBoxH = hBH;

        hBox = new Rectangle((int) pos.getX(), (int) pos.getY(), hBW, hBH);
    }

    /**
     * Create a collider with it's hitbox origin starting at entities origin x and y
     * and it's width being hBw and it's height hBH.
     *
     * @param xPos entities origin x position
     * @param yPos entities origin x position
     * @param hBW hitbox collider's width
     * @param hBH hitbox collider's height
     */
    public Collider(double xPos, double yPos, int hBW, int hBH)
    {
        hBox = new Rectangle();

        hitBoxW = hBW;
        hitBoxH = hBH;

        hBox = new Rectangle((int) xPos, (int) yPos, hBW, hBH);
    }

    /**
     * Keep hitbox with entities position
     *
     * @param pos entities position
     */
    public void updatePos(Point pos)
    {
        hBox.x = (int) pos.getX();
        hBox.y = (int) pos.getY();

    }

    /**
     * Check if two entities hitboxes are colliding through their colliders
     *
     * @param a first entities collider (contains hitbox)
     * @param b second entities collider (contains hitbox)
     * @return
     */
    public boolean checkEntityCollision(Collider a, Collider b)
    {
        return a.hBox.overlaps(b.hBox);
    }

    /**
     * Checks if an entities hitbox is colliding with a world hitbox
     *
     * @param a entities collider (contains hitbox)
     * @param b world hitbox
     * @return
     */
    public boolean checkWorldCollision(Collider a, Rectangle b)
    {
        return a.hBox.overlaps(b) || b.overlaps(a.hBox);
    }

    /**
     * Checks if an entities hitbox is colliding with a world hitbox
     *
     * @param a entities collider (contains hitbox)
     * @param b world hitbox
     * @return
     */
    public boolean checkWorldCollisions(Collider a, ArrayList<Rectangle> b)
    {
        boolean collided = false;
        for (Rectangle rect : b)
        {
            if (a.hBox.overlaps(rect) || rect.overlaps(a.hBox))
            {
                collided = true;
            }
        }
        return collided;
    }

    public Rectangle getHitBox()
    {
        return hBox;
    }

}
