/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Math.Point2D;
import java.util.UUID;



/**
 *
 * @author Bakuryu
 */
public class Entity
{
    /* Point2D variable to store the Entity's position*/
    protected Point2D position;
    
    /* UUID variable used to hold the Entity's unique id*/
    protected UUID ID;
    
    /**
     * Create an Entity and assign it a unique ID
     */
    public Entity()
    {
        ID = UUID.randomUUID();
    }
    
    /* Returns Entity's unique ID*/
    public UUID getID()
    {
        return ID;
    }
    
    /*Returns Entity's position*/
    public Point2D getPosition()
    {
        return position;
    }
    
    /* Blank update method for inheriting class to implement*/
    public void update(float t)
    {
        
    }
}
