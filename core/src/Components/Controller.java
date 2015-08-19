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
    private boolean rCollision;
    private boolean lCollision;
    private boolean uCollision;
    private boolean dCollision;
    private boolean rBlocked;
    private boolean lBlocked;
    private boolean uBlocked;
    private boolean dBlocked;
    private String prevKey;
    private Rectangle predictColBox;
    //private Rectangle rBox;
    //private Rectangle lBox;
    //private Rectangle uBox;
    //private Rectangle dBox;

    //private Point2D movementP;
    public Controller(ArrayList<Rectangle> collisions, Collider pCol)
    {
        input = new Input();
        keyPressed = "L";
        dirKeyPressed = "L";
        isMoving = false;
        speed = 35.0;
        mapCollisionBoxes = collisions;
        this.pCol = pCol;
        prevKey = "N";
        predictColBox = new Rectangle();
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
        System.out.println("rBlocked: " + rBlocked);
        System.out.println("lBlocked: " + lBlocked);
        System.out.println("uBlocked: " + uBlocked);
        System.out.println("dBlocked: " + dBlocked);
        keyPressed = input.checkKeys();
        checkProximity();
//        if (checkForCollision("R"))
//        {
//            rBlocked = true;
//        }
        switch (keyPressed)
        {

            case "RIGHT":
                //if (!checkForCollision("R"))

                if (!rBlocked)
                {
                    dirKeyPressed = "R";
                    rCollision = false;
                    curPos.setY(Math.round(curPos.getY()));
                }
                break;
            case "LEFT":
                //if (!checkForCollision("L"))
                if (!lBlocked)
                {
                    dirKeyPressed = "L";
                    lCollision = false;
                    curPos.setY(Math.round(curPos.getY()));
                }
                break;
            case "UP":
                //if (!checkForCollision("U"))
                if (!uBlocked)
                {
                    dirKeyPressed = "U";
                    uCollision = false;
                    curPos.setX(Math.round(curPos.getX()));
                }
                break;
            case "DOWN":

                //if (!checkForCollision("D"))
                if (!dBlocked)
                {
                    dirKeyPressed = "D";
                    dCollision = false;
                    curPos.setX(Math.round(curPos.getX()));
                }

                break;
//            case "NONE":
//                break;
        }

        switch (dirKeyPressed)
        //switch (keyPressed)
        {
            case "R":
                //case "RIGHT":
                if (!rCollision)
                {
                    if (checkForCollision("N"))
                    {
                        if (!lCollision)
                        {
                            curPos.setX(Math.round(curPos.getX() - speed * t / 1000));
                            rCollision = true;
                        }
                    }
                    else
                    {
                        curPos.setX(curPos.getX() + speed * t / 1000);
                        //lCollision = false;
                        dCollision = false;
                        uCollision = false;
                    }
                }

                break;
            case "L":
                //case "LEFT":
                if (!lCollision)
                {
                    if (checkForCollision("N"))
                    {
                        if (!rCollision)
                        {
                            curPos.setX(Math.round(curPos.getX() + speed * t / 1000));
                            lCollision = true;
                        }
                    }
                    else
                    {
                        curPos.setX(curPos.getX() - speed * t / 1000);
                        //rCollision = false;
                        uCollision = false;
                        dCollision = false;
                    }
                }
                break;

            case "U":
                //case "UP":
                if (!uCollision)
                {
                    if (checkForCollision("N"))
                    {
                        if (!dCollision)
                        {
                            curPos.setY(Math.round(curPos.getY() - speed * t / 1000));
                            uCollision = true;
                        }
                    }
                    else
                    {
                        curPos.setY(curPos.getY() + speed * t / 1000);
                        //dCollision = false;
                        lCollision = false;
                        rCollision = false;
                    }
                }
                break;
            case "D":
                //case "DOWN":
                if (!dCollision)
                {
                    if (checkForCollision("N"))
                    {
                        if (!uCollision)
                        {
                            curPos.setY(Math.round(curPos.getY() + speed * t / 1000));
                            dCollision = true;
                        }
                    }
                    else
                    {
                        curPos.setY(curPos.getY() - speed * t / 1000);
                        //uCollision = false;
                        lCollision = false;
                        rCollision = false;
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

        switch (dir)
        {
//            case "R":
//                predictColBox = new Rectangle(pCol.hBox.x + 16, pCol.hBox.y, pCol.hitBoxW, pCol.hitBoxH);
//                break;
//            case "L":
//                predictColBox = new Rectangle(pCol.hBox.x - 16, pCol.hBox.y, pCol.hitBoxW, pCol.hitBoxH);
//                break;
//            case "U":
//                predictColBox = new Rectangle(pCol.hBox.x, pCol.hBox.y + 16, pCol.hitBoxW, pCol.hitBoxH);
//                break;
//            case "D":
//                predictColBox = new Rectangle(pCol.hBox.x, pCol.hBox.y - 16, pCol.hitBoxW, pCol.hitBoxH);
//                break;
            case "N":
                predictColBox = new Rectangle(pCol.hBox.x, pCol.hBox.y, pCol.hitBoxW, pCol.hitBoxH);
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

    private void checkProximity()
    {
        Rectangle rBox = new Rectangle(pCol.hBox.x + 16, pCol.hBox.y + 3, pCol.hitBoxW, pCol.hitBoxH - 6);
        Rectangle lBox = new Rectangle(pCol.hBox.x - 16, pCol.hBox.y + 3, pCol.hitBoxW, pCol.hitBoxH - 6);
        Rectangle uBox = new Rectangle(pCol.hBox.x + 3, pCol.hBox.y + 16, pCol.hitBoxW - 6, pCol.hitBoxH);
        Rectangle dBox = new Rectangle(pCol.hBox.x + 3, pCol.hBox.y - 16, pCol.hitBoxW - 6, pCol.hitBoxH);
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

    public Rectangle getPreColBox()
    {
        return predictColBox;
    }

}
