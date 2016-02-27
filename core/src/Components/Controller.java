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
        System.out.println("rCollision: " + rCollision);
        System.out.println("lCollision: " + lCollision);
        System.out.println("uCollision: " + uCollision);
        System.out.println("dCollision: " + dCollision);
        System.out.println("rBlocked: " + preCol.isRBlocked());
        System.out.println("lBlocked: " + preCol.isLBlocked());
        System.out.println("uBlocked: " + preCol.isUBlocked());
        System.out.println("dBlocked: " + preCol.isDBlocked());
        System.out.println("lastKeyPressed :" + lastKeyPressed);
        keyPressed = input.checkKeys();
        preCol.update();

        System.out.println("keyPressed: " + keyPressed);
        switch (keyPressed)
        {

            case "RIGHT":
                
                lastKeyPressed = "R";
                checkForCollision(keyPressed, t);
                
                if (!rCollision)
                {
                    moveRight(curPos, t);
                }

                if (rCollision && !preCol.isRBlocked())//rBlocked)
                {
                    curPos.setY(Math.round(curPos.getY()));
                    rCollision = false;
                }

                break;
            case "LEFT":

                lastKeyPressed = "L";

                checkForCollision(keyPressed, t);
                if (!lCollision)
                {
                    moveLeft(curPos, t);
                }

                if (lCollision && !preCol.isLBlocked())//lBlocked)
                {
                    curPos.setY(Math.round(curPos.getY()));
                    lCollision = false;
                }
                break;
            case "UP":
                
                lastKeyPressed = "U";

                checkForCollision(keyPressed, t);
                if (!uCollision)
                {
                    moveUp(curPos, t);
                }

                if (uCollision && !preCol.isUBlocked())//uBlocked)
                {
                    curPos.setX(Math.round(curPos.getX()));
                    uCollision = false;
                }
                break;
            case "UPR":
//                if (lastKeyPressed == "R" || lastKeyPressed == "L")
//                {
                if (preCol.isUBlocked() && !preCol.isRBlocked())//uBlocked)
                {

                    lastKeyPressed = "R";
                    checkForCollision(keyPressed, t);
                    if (!rCollision)
                    {

//                        if (checkForCollision(keyPressed, t))
//                        {
//                            if (!lCollision)
//                            {
//                                curPos.setX(Math.round(curPos.getX() - speed * t / 1000));
//                                rCollision = true;
//                            }
//                        }
//                        else
//                        {
                            moveRight(curPos, t);
//                        }
                    }
//                    else if (rCollision && !preCol.isUBlocked())
//                    {
//                        curPos.setX(Math.round(curPos.getX()));
//                        lastKeyPressed = "U";
//                        if (!uCollision)
//                        {
//                            if (checkForCollision(keyPressed, t))
//                            {
//                                if (!dCollision)
//                                {
//                                    curPos.setY(Math.round(curPos.getY() - speed * t / 1000));
//                                    uCollision = true;
//                                }
//                            }
//                            else
//                            {
//                                moveUp(curPos, t);
//                            }
//                        }
//                    }
                }
                else if (!preCol.isUBlocked() && !preCol.isRBlocked())
                {
                    curPos.setX(Math.round(curPos.getX()));
                    
                    lastKeyPressed = "U";
                    checkForCollision(keyPressed, t);
                    if (!uCollision)
                    {
//                        if (checkForCollision(keyPressed, t))
//                        {
//                            if (!dCollision)
//                            {
//                                curPos.setY(Math.round(curPos.getY() - speed * t / 1000));
//                                uCollision = true;
//                            }
//                        }
//                        else
//                        {
                            moveUp(curPos, t);
//                        }
                    }

                }
                else if (preCol.isUBlocked() && preCol.isRBlocked())
                {
                    checkForCollision(keyPressed, t);
                    if (lastKeyPressed == "R")
                    {
                        if(!rCollision)
                        {
                            moveRight(curPos,t);
                        }
                    }
                    else if(lastKeyPressed == "U")
                    {
                        if (!uCollision)
                        {
                            moveUp(curPos,t);
                        }
                    }
                }
                else if (preCol.isRBlocked() && !preCol.isUBlocked())
                {
                    curPos.setX(Math.round(curPos.getX()));
                    lastKeyPressed = "U";
                    checkForCollision(keyPressed, t);
                    if (!uCollision)
                    {
//                        if (checkForCollision(keyPressed, t))
//                        {
//                            if (!dCollision)
//                            {
//                                curPos.setY(Math.round(curPos.getY() - speed * t / 1000));
//                                uCollision = true;
//                            }
//                        }
//                        else
//                        {
                            moveUp(curPos, t);
//                        }
                    }
                }
//                }
//                if (lastKeyPressed == "U")
//                {
//                if (preCol.isRBlocked())
//                {
//                    if (!uCollision)
//                    {
//                        if (checkForCollision(keyPressed, t))
//                        {
//                            if (!dCollision)
//                            {
//                                curPos.setY(Math.round(curPos.getY() - speed * t / 1000));
//                                uCollision = true;
//                            }
//                        }
//                        else
//                        {
//                            moveUp(curPos, t);
//                        }
//                    }
//                    else if (uCollision && !(preCol.isRBlocked()))
//                    {
//                        curPos.setY(Math.round(curPos.getY()));
//                        lastKeyPressed = "R";
//                        if (!rCollision)
//                        {
//
//                            if (checkForCollision(keyPressed, t))
//                            {
//                                if (!lCollision)
//                                {
//                                    curPos.setX(Math.round(curPos.getX() - speed * t / 1000));
//                                    rCollision = true;
//                                }
//                            }
//                            else
//                            {
//                                moveRight(curPos, t);
//                            }
//                        }
//
//                    }
//                }
//                else if (!preCol.isRBlocked() && !preCol.isUBlocked())
//                {
//                    curPos.setX(Math.round(curPos.getX()));
//                    lastKeyPressed = "U";
//                    if (!uCollision)
//                    {
//                        if (checkForCollision(keyPressed, t))
//                        {
//                            if (!dCollision)
//                            {
//                                curPos.setY(Math.round(curPos.getY() - speed * t / 1000));
//                                uCollision = true;
//                            }
//                        }
//                        else
//                        {
//                            moveUp(curPos, t);
//                        }
//                    }
//                }
//                else
//                {
//                    curPos.setY(Math.round(curPos.getY()));
//                    lastKeyPressed = "R";
//                    if (!rCollision)
//                    {
//
//                        if (checkForCollision(keyPressed, t))
//                        {
//                            if (!lCollision)
//                            {
//                                curPos.setX(Math.round(curPos.getX() - speed * t / 1000));
//                                rCollision = true;
//                            }
//                        }
//                        else
//                        {
//                            moveRight(curPos, t);
//                        }
//                    }
////                       
//                }
//                }
                break;

            case "DOWN":
                
                lastKeyPressed = "D";

                checkForCollision(keyPressed, t);
                if (!dCollision)
                {

                    moveDown(curPos, t);
                }
//                }
                if (!preCol.isDBlocked())//dBlocked)
                {
                    curPos.setX(Math.round(curPos.getX()));
                    dCollision = false;
                }
                break;
        }

    }

    private void moveRight(Point2D curPos, int t)
    {
        curPos.setX(curPos.getX() + speed * t / 1000);
        lCollision = false;
        dCollision = false;
        uCollision = false;
    }

    private void moveLeft(Point2D curPos, int t)
    {
        curPos.setX(curPos.getX() - speed * t / 1000);
        rCollision = false;
        uCollision = false;
        dCollision = false;
    }

    private void moveUp(Point2D curPos, int t)
    {
        curPos.setY(curPos.getY() + speed * t / 1000);
        dCollision = false;
        lCollision = false;
        rCollision = false;
    }

    private void moveDown(Point2D curPos, int t)
    {

        curPos.setY(curPos.getY() - speed * t / 1000);
        uCollision = false;
        lCollision = false;
        rCollision = false;
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
                System.out.println("Vector Dist: " + dist.x);
                if (dir == "LEFT"/*noColDistX > Math.abs(pCenter.sub(mCenter).x)*/);
                {
                    if (dist.x > 0)
                    {
                        lCollision = true;

                    }

                }
                if (dir == "RIGHT")
                {
                    if (dist.x < 0)
                    {
                        rCollision = true;

                    }
                }
                if (dir == "UP"/*noColDistY > Math.abs(pCenter.sub(mCenter).y)*/)
                {
                    if (dist.y < 0)
                    {
                        uCollision = true;
                    }
                }
                if (dir == "DOWN")
                {
                    if (dist.y > 0)
                    {
                        dCollision = true;
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
