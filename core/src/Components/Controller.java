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
    //private Point2D movementP;
    public Controller()
    {
       input = new Input();
       keyPressed = "N";
    }

    /**
     * Check if a movement input is pressed and move accordingly
     * @param curPos Player's current position
     * @param t Delta time
     */
    public void move(Point2D curPos, int t)
    {
        keyPressed = input.checkKeys();
        switch (keyPressed)
        {
            case "RIGHT":
                curPos.setX(curPos.getX() + t / 25.0);
                lastKeyPressed = "R";
                break;
            case "LEFT":
                curPos.setX(curPos.getX() - t / 25.0);
                lastKeyPressed = "L";
                break;
            case "UP":
                curPos.setY(curPos.getY() + t / 25.0);
                lastKeyPressed = "U";
                break;
            case "DOWN":
                curPos.setY(curPos.getY() - t / 25.0);
                lastKeyPressed = "D";
                break;
            case "NONE":
                break;
        }

    }
    
    public String lastKeyPressed()
    {
        return lastKeyPressed;
    }
}
