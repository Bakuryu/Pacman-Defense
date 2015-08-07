/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Math.Point2D;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

public class Controller
{

    /* Variable to store amount entitie position should increase */
    private Input input;
    private String keyPressed;
    private String dirKeyPressed;
    private boolean isMoving;
    private double speed;
    private ArrayList<Rectangle> mapCollisionBoxes;
    private Collider pCol;
    private boolean rBlocked;
    private boolean lBlocked;
    private boolean uBlocked;
    private boolean dBlocked;
    private String prevKey;

    //private Point2D movementP;
    public Controller(ArrayList<Rectangle> collisions, Collider pCol)
    {
        input = new Input();
        keyPressed = "N";
        dirKeyPressed = "N";
        isMoving = false;
        speed = 40.0;
        mapCollisionBoxes = collisions;
        this.pCol = pCol;
        prevKey = "N";

    }

    /**
     * Check if a movement input is pressed and move accordingly
     *
     * @param curPos Player's current position
     * @param t Delta time
     */
    public void move(Point2D curPos, int t)
    {
        System.out.println("rBlocked: " + rBlocked);
        System.out.println("lBlocked: " + lBlocked);
        System.out.println("uBlocked: " + uBlocked);
        System.out.println("dBlocked: " + dBlocked);
        keyPressed = input.checkKeys();
        switch (keyPressed)
        {
            case "RIGHT":
//                if (!rBlocked)
//                {
                    if (!checkForCollision("R"))
                    {
//                        rBlocked = true;
//                    }
//                    else
//                    {
                        dirKeyPressed = "R";
//                        lBlocked = false;
//                        dBlocked = false;
//                        uBlocked = false;
                    }
//                }
                break;
            case "LEFT":
//                if (!lBlocked)
//                {
                    if (!checkForCollision("L"))
                    {
//                        lBlocked = true;
//                    }
//                    else
//                    {
                        dirKeyPressed = "L";
//                        rBlocked = false;
//                        dBlocked = false;
//                        uBlocked = false;
                    }
//                }
                break;
            case "UP":
//                if (!uBlocked)
//                {
                    if (!checkForCollision("U"))
                    {
//                        uBlocked = true;
//                    }
//                    else
//                    {
                        dirKeyPressed = "U";
//                        rBlocked = false;
//                        dBlocked = false;
//                        lBlocked = false;
                    }
//                }
                break;
            case "DOWN":
//                if (!dBlocked)
//                {
                    if (!checkForCollision("D"))
                    {
//                        dBlocked = true;
//                    }
//                    else
//                    {
                        dirKeyPressed = "D";
//                        rBlocked = false;
//                        lBlocked = false;
//                        uBlocked = false;
                    }
//                }
                break;
            case "NONE":
                break;
        }

        switch (dirKeyPressed)
        {
            case "R":
                if (!rBlocked)
                {
                    if (checkForCollision("N"))
                    {
                        curPos.setX(Math.round(curPos.getX() - speed * t / 1000));
                       rBlocked = true;
                    }
                    else
                    {
                        curPos.setX(curPos.getX() + speed * t / 1000);
                        lBlocked = false;
                        dBlocked = false;
                        uBlocked = false;
                    }
                }

                break;
            case "L":
                if (!lBlocked)
                {
                    if (checkForCollision("N"))
                    {
                        curPos.setX(Math.round(curPos.getX() + speed * t / 1000));
                        lBlocked = true;
                    }
                    else
                    {
                        curPos.setX(curPos.getX() - speed * t / 1000);
                        rBlocked = false;
                        uBlocked = false;
                        dBlocked = false;
                    }
                }
                break;

            case "U":
                if (!uBlocked)
                {
                    if (checkForCollision("N"))
                    {
                        curPos.setY(Math.round(curPos.getY() - speed * t / 1000));
                        uBlocked = true;
                    }
                    else
                    {
                        curPos.setY(curPos.getY() + speed * t / 1000);
                        dBlocked = false;
                        lBlocked = false;
                        rBlocked = false;
                    }
                }
                break;
            case "D":
                if (!dBlocked)
                {
                    if (checkForCollision("N"))
                    {
                        curPos.setY(Math.round(curPos.getY() + speed * t / 1000));
                        dBlocked = true;
                    }
                    else
                    {
                        curPos.setY(curPos.getY() - speed * t / 1000);
                        uBlocked = false;
                        lBlocked = false;
                        rBlocked = false;
                    }
                }
                break;
        }
    }

    public String lastKeyPressed()
    {
        return dirKeyPressed;
    }

    private boolean checkForCollision(String dir)
    {
        Rectangle predictColBox = new Rectangle();

        switch (dir)
        {
            case "R":
                predictColBox = new Rectangle(pCol.hBox.x + 16, pCol.hBox.y, pCol.hitBoxW, pCol.hitBoxH);
                break;
            case "L":
                predictColBox = new Rectangle(pCol.hBox.x - 16, pCol.hBox.y, pCol.hitBoxW, pCol.hitBoxH);
                break;
            case "U":
                predictColBox = new Rectangle(pCol.hBox.x, pCol.hBox.y + 16, pCol.hitBoxW, pCol.hitBoxH);
                break;
            case "D":
                predictColBox = new Rectangle(pCol.hBox.x, pCol.hBox.y - 16, pCol.hitBoxW, pCol.hitBoxH);
                break;
            case "N":
                predictColBox = new Rectangle(pCol.hBox.x,pCol.hBox.y,pCol.hitBoxW,pCol.hitBoxH);
        }
        boolean collided = false;
        for (Rectangle rect : mapCollisionBoxes)
        {
            //if (pCol.hBox.overlaps(rect))
            if (predictColBox.overlaps(rect))
            {
                collided = true;
            }
        }
        return collided;
    }

}
