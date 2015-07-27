/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Bakuryu
 */
public class EntityManager
{
    /* Array list of entities used for storing and managing entities in the game*/

    private ArrayList<Entity> e;
    private boolean addED;
    private boolean removeED;
    private Stack<Entity> delayAEnts;
    private Stack<Entity> delayREnts;

    /**
     * Create an EntityManager and initialize entity ArrayList
     */
    public EntityManager()
    {
        e = new ArrayList();
        addED = false;
        removeED = false;
        delayAEnts = new Stack<Entity>();
        delayREnts = new Stack<Entity>();
    }

    /**
     * Adds an entity, ent, to the array list
     *
     * @param ent Entity to be added to array list
     */
    public void addEnt(Entity ent)
    {
        e.add(ent);
    }

    public void addEntDelay(Entity ent)
    {
        addED = true;
        delayAEnts.push(ent);
    }

    /**
     * Removes entity, ent, from array list
     *
     * @param ent Entity to be removed from array list
     */
    public void destroyEnt(Entity ent)
    {
        e.remove(ent);
    }

    public void destroyEntDelay(Entity ent)
    {
        removeED = true;
        delayREnts.push(ent);
    }

    /* Update method used to update all entities in the array list*/
    public void updateEnts(float t)
    {
        for (Entity ents : e)
        {
            ents.update(t);
            if (ents instanceof AgentEntity)
            {
                AgentEntity a = (AgentEntity) ents;
                if (!a.isAlive())
                {
                    destroyEntDelay(ents);
                }
            }
        }

        if (addED)
        {
            while(!delayAEnts.empty())
            {
                addEnt(delayAEnts.pop());
            }
            addED = false;
        }

        if (removeED)
        {
            while(!delayREnts.empty())
            {
                destroyEnt(delayREnts.pop());
            }

            removeED = false;
        }

    }

//    /* Render method used to render all entities in the array list*/
//    public void renderEnts(SpriteRenderer ren) throws SlickException
//    {
//        for (Entity ents : e)
//        {
//            //ren.render(ents);
//        }
//    }
    public ArrayList<Entity> getEnts()
    {
        return e;
    }
}
