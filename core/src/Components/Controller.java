/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Math.Point2D;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class Controller
{

    /* Variable to store amount entitie position should increase */
    private Input input;
    private String keyPressed;
    private String lastKeyPressed;
    private String lastMoved;
    private boolean isMoving;
    private double speed;
    private ArrayList<Rectangle> mapCollisionBoxes;
    private Collider pCol;
    private boolean rCollision;
    private boolean lCollision;
    private boolean uCollision;
    private boolean dCollision;
    private String prevKey;
    private Rectangle predictColBox;
    private PreCollider preCol;
    //private Rectangle rBox;
    //private Rectangle lBox;
    //private Rectangle uBox;
    //private Rectangle dBox;

    //private Point2D movementP;
    public Controller(ArrayList<Rectangle> collisions, Collider pCol, PreCollider preCol)
    {
        input = new Input();
        keyPressed = "N";
        lastKeyPressed = "N";
        isMoving = false;
        speed = 35.0;
        mapCollisionBoxes = collisions;
        this.pCol = pCol;
        prevKey = "N";
        predictColBox = new Rectangle();
        this.preCol = preCol;
        //Rectangle rBox = new Rectangle();
        //Rectangle lBox = new Rectangle();
        //Rectangle uBox = new Rectangle();
        //ectangle dBox = new Rectangle();

    }

    /**
     * Check if a movement input is pressed and move accordingly
     *
     * @param curPos Player's current position
     * @param t Delta time
     */
    public void move(Point2D curPos, int t)
    {
        //System.out.println("rCollision: " + rCollision);
        System.out.println("lCollision: " + lCollision);
        System.out.println("uCollision: " + uCollision);
//        System.out.println("dCollision: " + dCollision);
        //System.out.println("rBlocked: " + preCol.isRBlocked());
        System.out.println("lBlocked: " + preCol.isLBlocked());
        System.out.println("uBlocked: " + preCol.isUBlocked());
        System.out.println("LastMoved: " + lastMoved);
//        System.out.println("dBlocked: " + preCol.isDBlocked());
//        System.out.println("lastKeyPressed :" + lastKeyPressed);
        keyPressed = input.checkKeys();
        preCol.update();

        System.out.println("keyPressed: " + keyPressed);
        checkForCollision(keyPressed, t);
        switch (keyPressed)
        {

            case "RIGHT":
                lastKeyPressed = "R";
                moveRight(curPos, t);
                break;
                
            case "LEFT":
                lastKeyPressed = "L";
                moveLeft(curPos, t);
                break;
                
            case "UP":
                lastKeyPressed = "U";
                moveUp(curPos, t);
                break;

            case "UPR":
                if (!preCol.isUBlocked())
                {
                    if (!preCol.isRBlocked())
                    {
                        if (lastMoved == "U")
                        {
                            lastKeyPressed = "R";
                            moveRight(curPos, t);
                        }
                        else if (lastMoved == "R")
                        {
                            lastKeyPressed = "U";
                            moveUp(curPos, t);
                        }
                    }
                    else
                    {
                        lastKeyPressed = "U";
                        moveUp(curPos, t);
                    }
                }
                else
                {
                    if (!preCol.isRBlocked())
                    {
                        lastKeyPressed = "R";
                        moveRight(curPos, t);
                    }
                    else
                    {
                        if (lastMoved == "U" && !uCollision)
                        {
                            lastKeyPressed = "U";
                            moveUp(curPos, t);
                        }

                        if (lastMoved == "R" && !rCollision)
                        {
                            lastKeyPressed = "R";
                            moveRight(curPos, t);
                        }
                    }
                }
                break;
                
            case "UPL":
                if (!preCol.isUBlocked())
                {
                    if (!preCol.isLBlocked())
                    {
                        if (lastMoved == "U")
                        {
                            lastKeyPressed = "L";
                            moveLeft(curPos, t);
                        }
                        else if (lastMoved == "L")
                        {
                            lastKeyPressed = "U";
                            moveUp(curPos, t);
                        }
                    }
                    else
                    {
                        lastKeyPressed = "U";
                        lCollision = true;
                        moveUp(curPos, t);
                    }
                }
                else
                {
                    if (!preCol.isLBlocked())
                    {
                        lastKeyPressed = "L";
                        moveLeft(curPos, t);
                    }
                    else
                    {
                        if (lastMoved == "U" || lastMoved == "D" && !uCollision)
                        {
                            lastKeyPressed = "U";
                            moveUp(curPos, t);
                        }

                        if (lastMoved == "L" || lastMoved == "R" && !lCollision)
                        {
                            lastKeyPressed = "L";
                            moveLeft(curPos, t);
                        }
                    }
                }
                break;

            case "DOWN":
                lastKeyPressed = "D";
                moveDown(curPos, t);
                break;
                
            case "DOWNR":
                if (!preCol.isDBlocked())
                {
                    if (!preCol.isRBlocked())
                    {
                        if (lastMoved == "D")
                        {
                            lastKeyPressed = "R";
                            moveRight(curPos, t);
                        }
                        else if (lastMoved == "R")
                        {
                            lastKeyPressed = "D";
                            moveDown(curPos, t);
                        }
                    }
                    else
                    {
                        lastKeyPressed = "D";
                        moveDown(curPos, t);
                    }
                }
                else
                {
                    if (!preCol.isRBlocked())
                    {
                        lastKeyPressed = "R";
                        moveRight(curPos, t);
                    }
                    else
                    {
                        if (lastMoved == "D" && !dCollision)
                        {
                            lastKeyPressed = "D";
                            moveDown(curPos, t);
                        }

                        if (lastMoved == "R" && !rCollision)
                        {
                            lastKeyPressed = "R";
                            moveRight(curPos, t);
                        }
                    }
                }
                break;
            case "DOWNL":
                if (!preCol.isDBlocked())
                {
                    if (!preCol.isLBlocked())
                    {
                        if (lastMoved == "D")
                        {
                            lastKeyPressed = "L";
                            moveLeft(curPos, t);
                        }
                        else if (lastMoved == "L")
                        {
                            lastKeyPressed = "D";
                            moveDown(curPos, t);
                        }
                    }
                    else
                    {
                        lastKeyPressed = "D";
                        lCollision = true;
                        moveDown(curPos, t);
                    }
                }
                else
                {
                    if (!preCol.isLBlocked())
                    {
                        lastKeyPressed = "L";
                        moveLeft(curPos, t);
                    }
                    else
                    {
                        if (lastMoved == "D" || lastMoved == "U" && !dCollision)
                        {
                            lastKeyPressed = "D";
                            moveDown(curPos, t);
                        }

                        if (lastMoved == "L" || lastMoved == "R" && !lCollision)
                        {
                            lastKeyPressed = "L";
                            moveLeft(curPos, t);
                        }
                    }
                }
                break;
        }

    }

    private void moveRight(Point2D curPos, int t)
    {

        if (!rCollision)
        {
            curPos.setX(curPos.getX() + speed * t / 1000);
            lastMoved = "R";
            lCollision = false;
        }

        if (rCollision && !preCol.isRBlocked())//rBlocked)
        {
            curPos.setY(Math.round(curPos.getY()));
            rCollision = false;
        }

    }

    private void moveLeft(Point2D curPos, int t)
    {
        if (!lCollision)
        {
            curPos.setX(curPos.getX() - speed * t / 1000);
            lastMoved = "L";
            rCollision = false;
        }

        if (lCollision && !preCol.isLBlocked())//lBlocked)
        {
            curPos.setY(Math.round(curPos.getY()));
            lCollision = false;
        }

    }

    private void moveUp(Point2D curPos, int t)
    {

        if (!uCollision)
        {
            curPos.setY(curPos.getY() + speed * t / 1000);
            lastMoved = "U";
            dCollision = false;
        }

        if (uCollision && !preCol.isUBlocked())//uBlocked)
        {
            curPos.setX(Math.round(curPos.getX()));
            uCollision = false;
        }

    }

    private void moveDown(Point2D curPos, int t)
    {
        if (!dCollision)
        {
            curPos.setY(curPos.getY() - speed * t / 1000);
            lastMoved = "D";
            uCollision = false;
        }
//                }
        if (!preCol.isDBlocked())//dBlocked)
        {
            curPos.setX(Math.round(curPos.getX()));
            dCollision = false;
        }

    }

    public String lastKeyPressed()
    {
        return lastKeyPressed;
    }

    private boolean checkForCollision(String dir, int t)
    {
        Vector2 pCenter = new Vector2();
        predictColBox = new Rectangle(pCol.hBox.x, pCol.hBox.y, pCol.hitBoxW, pCol.hitBoxH);
        //System.out.println("Cent of Pre: " + (predictColBox.x) + ", " + (predictColBox.y));
        Point2D tempPos = new Point2D(predictColBox.x, predictColBox.y);
        switch (dir)
        {
            case "RIGHT":
                moveRight(tempPos, t);
                predictColBox.set((float) tempPos.getX(), (float) tempPos.getY(), predictColBox.width, predictColBox.height);
                break;
            case "LEFT":
                moveLeft(tempPos, t);
                predictColBox.set((float) tempPos.getX(), (float) tempPos.getY(), predictColBox.width, predictColBox.height);
                break;
            case "UP":
                moveUp(tempPos, t);
                predictColBox.set((float) tempPos.getX(), (float) tempPos.getY(), predictColBox.width, predictColBox.height);
                break;
            case "DOWN":
                moveDown(tempPos, t);
                predictColBox.set((float) tempPos.getX(), (float) tempPos.getY(), predictColBox.width, predictColBox.height);
                break;
        }

        //pCenter = new Point2D(predictColBox.)
        predictColBox.getCenter(pCenter);
        boolean collided = false;
        for (Rectangle rect : mapCollisionBoxes)
        {
            //if (pCol.hBox.overlaps(rect))
            if (predictColBox.overlaps(rect))
            {
                float noColDistX = (predictColBox.width / 2) + (rect.width / 2);
                //System.out.println("noColDistX: " + noColDistX);
                float noColDistY = (predictColBox.height / 2) + (rect.height / 2);
                //System.out.println("noColDistY: " + noColDistY);
                Vector2 mCenter = new Vector2();
                rect.getCenter(mCenter);
                Vector2 dist = new Vector2(pCenter.sub(mCenter));

                if (dir == "UPR")
                {
                    if (dist.y < 0)
                    {
                        uCollision = true;
                        System.out.println("Vector Dist Y: " + dist.y);
                    }

                    if (dist.x < 0)
                    {
                        rCollision = true;
                        System.out.println("Vector Dist X: " + dist.x);

                    }
                }

                if (dir == "UPL")
                {
                    if (dist.y < 0)
                    {
                        uCollision = true;
                        System.out.println("Vector Dist Y: " + dist.y);
                    }

                    if (dist.x > 0)
                    {
                        lCollision = true;
                        System.out.println("Vector Dist X: " + dist.x);

                    }
                }

                if (dir == "DOWNR")
                {
                    if (dist.y > 0)
                    {
                        dCollision = true;
                        System.out.println("Vector Dist Y: " + dist.y);
                    }

                    if (dist.x < 0)
                    {
                        rCollision = true;
                        System.out.println("Vector Dist X: " + dist.x);

                    }
                }

                if (dir == "DOWNL")
                {
                    if (dist.y > 0)
                    {
                        dCollision = true;
                        System.out.println("Vector Dist Y: " + dist.y);
                    }

                    if (dist.x > 0)
                    {
                        lCollision = true;
                        System.out.println("Vector Dist X: " + dist.x);

                    }
                }

                if (dir == "LEFT");
                {
                    if (dist.x > 0)
                    {
                        lCollision = true;
                        System.out.println("Vector Dist X: " + dist.x);

                    }

                }
                if (dir == "RIGHT")
                {
                    if (dist.x < 0)
                    {
                        rCollision = true;
                        System.out.println("Vector Dist X: " + dist.x);

                    }
                }
                if (dir == "UP")
                {
                    if (dist.y < 0)
                    {
                        uCollision = true;
                        System.out.println("Vector Dist Y: " + dist.y);
                    }
                }
                if (dir == "DOWN")
                {
                    if (dist.y > 0)
                    {
                        dCollision = true;
                        System.out.println("Vector Dist Y: " + dist.y);
                    }
                }

                collided = true;
            }
        }
        return collided;
    }

    public Rectangle getPreColBox()
    {
        return predictColBox;
    }

}
