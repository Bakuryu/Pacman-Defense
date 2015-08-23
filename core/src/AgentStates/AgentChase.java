/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgentStates;

import Entity.AgentEntity;
import Entity.EntityState;

/**
 *
 * @author Bakuryu
 */
public class AgentChase extends EntityState<AgentEntity>
{

    private double minDist;
    private boolean goLeft;
    private boolean goRight;
    private boolean goUp;
    private boolean goDown;

    @Override
    public void Enter(AgentEntity e, int t)
    {
        System.out.println("BLINKYS ON THE MOVE!");
    }

    @Override
    public void Execute(AgentEntity e, int t)
    {


    }

    @Override
    public void Exit(AgentEntity e, int t)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
