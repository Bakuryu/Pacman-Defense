/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Math.Point2D;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Bakuryu
 */
public class PreCollider
{

    private Rectangle lBox;
    private Rectangle rBox;
    private Rectangle uBox;
    private Rectangle dBox;
    private boolean lBlocked;
    private boolean rBlocked;
    private boolean uBlocked;
    private boolean dBlocked;
    private ArrayList<Rectangle> mapCollisionBoxes;
    private Collider eCol;

    public PreCollider(ArrayList<Rectangle> mapCols, Collider col)
    {
        mapCollisionBoxes = mapCols;
        eCol = col;
        rBox = new Rectangle(col.hBox.x + 16, col.hBox.y + 3, col.hitBoxW, col.hitBoxH - 6);
        lBox = new Rectangle(col.hBox.x - 16, col.hBox.y + 3, col.hitBoxW, col.hitBoxH - 6);
        uBox = new Rectangle(col.hBox.x + 3, col.hBox.y + 16, col.hitBoxW - 6, col.hitBoxH);
        dBox = new Rectangle(col.hBox.x + 3, col.hBox.y - 16, col.hitBoxW - 6, col.hitBoxH);
    }

    public PreCollider(Collider col)
    {
        eCol = col;
        rBox = new Rectangle(col.hBox.x + 16, col.hBox.y + 3, col.hitBoxW, col.hitBoxH - 6);
        lBox = new Rectangle(col.hBox.x - 16, col.hBox.y + 3, col.hitBoxW, col.hitBoxH - 6);
        uBox = new Rectangle(col.hBox.x + 3, col.hBox.y + 16, col.hitBoxW - 6, col.hitBoxH);
        dBox = new Rectangle(col.hBox.x + 3, col.hBox.y - 16, col.hitBoxW - 6, col.hitBoxH);
    }

    public void update()
    {
        System.out.println("rBlocked: " + rBlocked);
        System.out.println("lBlocked: " + lBlocked);
        System.out.println("uBlocked: " + uBlocked);
        System.out.println("dBlocked: " + dBlocked);
        rBox.set(eCol.hBox.x + 16, eCol.hBox.y + 3, eCol.hitBoxW, eCol.hitBoxH - 6);
        lBox.set(eCol.hBox.x - 16, eCol.hBox.y + 3, eCol.hitBoxW, eCol.hitBoxH - 6);
        uBox.set(eCol.hBox.x + 3, eCol.hBox.y + 16, eCol.hitBoxW - 6, eCol.hitBoxH);
        dBox.set(eCol.hBox.x + 3, eCol.hBox.y - 16, eCol.hitBoxW - 6, eCol.hitBoxH);
        checkProximity();
    }

    private void checkProximity()
    {
        rBlocked = false;
        lBlocked = false;
        uBlocked = false;
        dBlocked = false;
        for (Rectangle rect : mapCollisionBoxes)
        {
            if (rBox.overlaps(rect))
            {
                rBlocked = true;
            }

            if (lBox.overlaps(rect))
            {
                lBlocked = true;
            }

            if (uBox.overlaps(rect))
            {
                uBlocked = true;
            }

            if (dBox.overlaps(rect))
            {
                dBlocked = true;
            }

        }
    }

    public boolean isLBlocked()
    {
        return lBlocked;
    }

    public boolean isRBlocked()
    {
        return rBlocked;
    }

    public boolean isUBlocked()
    {
        return uBlocked;
    }

    public boolean isDBlocked()
    {
        return dBlocked;
    }

    public Point2D dBoxPos()
    {
        Point2D dBoxP = new Point2D(dBox.x, dBox.y);

        return dBoxP;
    }

    public Point2D lBoxPos()
    {
        Point2D lBoxP = new Point2D(lBox.x, lBox.y);

        return lBoxP;
    }

    public Point2D rBoxPos()
    {
        Point2D rBoxP = new Point2D(rBox.x, rBox.y);

        return rBoxP;
    }

    public Point2D uBoxPos()
    {
        Point2D uBoxP = new Point2D(uBox.x, uBox.y);

        return uBoxP;
    }

}
