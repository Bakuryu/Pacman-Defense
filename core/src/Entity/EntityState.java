/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *Abstract state class for entities to extend and implement
 * @author Bakuryu
 * @param <T> Entity type
 */
public abstract class EntityState<T>
{
    /* Abstract method for an entity entering a state*/
    public abstract void Enter(T e, int t);

    /* Abstract method for an entity executing a state*/
    public abstract void Execute(T e, int t);
    
    /* Abstract method for an entity exiting a state*/
    public abstract void Exit(T e, int t);

}
