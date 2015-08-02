/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Math.Point2D;

public class Controller
{

    /* Variable to store amount entitie position should increase */
    private Input input;
    private String keyPressed;
    private String lastKeyPressed;
    private boolean isMoving;
    private double speed;

    //private Point2D movementP;
    public Controller()
    {
        input = new Input();
        keyPressed = "L";
        lastKeyPressed = "L";
        isMoving = false;
        speed = 40.0;
    }

    /**
     * Check if a movement input is pressed and move accordingly
     *
     * @param curPos Player's current position
     * @param t Delta time
     */
    public void move(Point2D curPos, int t)
    {
        keyPressed = input.checkKeys();
        switch (keyPressed)
        {
            case "RIGHT":
                lastKeyPressed = "R";
                break;
            case "LEFT":
                lastKeyPressed = "L";
                break;
            case "UP":
                lastKeyPressed = "U";
                break;
            case "DOWN":
                lastKeyPressed = "D";
                break;
            case "NONE":
                break;
        }

        switch (lastKeyPressed)
        {
            case "R":
                curPos.setX(curPos.getX() + speed * t / 1000);
                break;
            case "L":
                curPos.setX(curPos.getX() - speed * t / 1000);
                break;
            case "U":
                curPos.setY(curPos.getY() + speed * t / 1000);
                break;
            case "D":
                curPos.setY(curPos.getY() - speed * t / 1000);
                break;
        }
        System.out.println(curPos.getX() - speed * t / 1000);
    }

    public String lastKeyPressed()
    {
        return lastKeyPressed;
    }

}
