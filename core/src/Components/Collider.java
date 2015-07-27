/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Math.Point2D;
import java.awt.Point;
import java.awt.Rectangle;

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
     * Create a collider with it's hitbox origin starting at entities origin pos and
     * it's width being hBw and it's height hBH.
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
        if (a.hBox.intersects(b.hBox))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    
    /**
     * Checks if an entities hitbox is colliding with a world hitbox
     *
     * @param a entities collider (contains hitbox)
     * @param b world hitbox
     * @return
     */
    public boolean checkWorldColision(Collider a, Rectangle b)
    {
        if (a.hBox.intersects(b) || b.intersects(a.hBox))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public Rectangle getHitBox()
    {
        return hBox;
    }

}
