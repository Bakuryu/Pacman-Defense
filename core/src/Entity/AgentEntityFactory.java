/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Graphics.AnimationManager;
import Math.Point2D;
import Math.PointManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import java.util.LinkedList;
import java.util.Random;
/*
 *
 * @author Bakuryu
 */
public class AgentEntityFactory
{

    private AgentEntity a;
    private int hp;
    private int speed;
    private int dmg;
    private Animation anim;
    private AnimationManager animM;
    private LinkedList<AgentEntity> waveList;
    private PlayerEntity p;
    private PointManager pointM;
    private Random ran;

    public AgentEntityFactory(PlayerEntity p, PointManager pM)
    {
        ran = new Random();
        this.p = p;
        pointM = pM;
        animM = new AnimationManager();
        hp = 0;
        speed = 0;
        dmg = 0;
        waveList = new LinkedList<AgentEntity>();

    }

    public void update(float t)
    {

    }

    private LinkedList<Point2D> generatePath()
    {
        LinkedList<Point2D> path = new LinkedList<Point2D>();
        path.add(new Point2D(70, 3));
        path.add(new Point2D(93, 3));
        path.add(new Point2D(93, 31.43));
        path.add(new Point2D(12.5, 31.43));
        path.add(new Point2D(12.5, 46));
        path.add(new Point2D(93, 46));
        path.add(new Point2D(93, 77.30));
        path.add(new Point2D(25, 77.30));
        path.add(new Point2D(22.5, 70));
        path.add(new Point2D(10, 70));

        return path;

    }

    public LinkedList<AgentEntity> getAgents(int wave, int maxE)
    {

        if (wave == 1)
        {
            for (int i = 0; i < maxE; i++)
            {

                waveList.add(createAgent(wave));
            }
        }

        if (wave == 2)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(ran.nextInt(wave) + 1));
            }
        }

        if (wave == 3)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(ran.nextInt(wave) + 1));
            }
        }

        if (wave == 4)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(ran.nextInt(wave) + 1));
            }
        }

        if (wave == 5)
        {
            for (int i = 0; i < maxE; i++)
            {

                waveList.add(createAgent(1));
            }
        }

        if (wave == 6)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(ran.nextInt(2) + 1));
            }
        }

        if (wave == 7)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(ran.nextInt(3) + 1));
            }
        }

        if (wave == 8)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(ran.nextInt(4) + 1));
            }
        }

        if (wave == 9)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(1));
            }
        }

        if (wave == 10)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(ran.nextInt(2) + 1));
            }
        }

        if (wave == 11)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(ran.nextInt(3) + 1));
            }
        }

        if (wave == 12)
        {
            for (int i = 0; i < maxE; i++)
            {
                waveList.add(createAgent(ran.nextInt(4) + 1));
            }
        }

        return waveList;
    }

    private AgentEntity createAgent(int num)
    {
        AgentEntity agent = null;
        if (num == 1)
        {
            agent = new AgentEntity(70, -5.71, "Blinky", p, pointM);
            agent.setAnimation(animM.setAgentAnimation("Blinky"));
            agent.setSpeed(20);
            agent.setHP(4);
            agent.setDmg(2);
            agent.setPath(generatePath());
        }

        if (num == 2)
        {
            agent = new AgentEntity(70, -5.71, "Inky", p, pointM);
            agent.setAnimation(animM.setAgentAnimation("Inky"));
            agent.setSpeed(15);
            agent.setHP(7);
            agent.setDmg(4);
            agent.setPath(generatePath());
        }

        if (num == 3)
        {
            agent = new AgentEntity(70, -5.71, "Pinky", p, pointM);
            agent.setAnimation(animM.setAgentAnimation("Pinky"));
            agent.setSpeed(35);
            agent.setHP(3);
            agent.setDmg(1);
            agent.setPath(generatePath());
        }

        if (num == 4)
        {
            agent = new AgentEntity(70, -5.71, "Clyde", p, pointM);
            agent.setAnimation(animM.setAgentAnimation("Clyde"));
            agent.setSpeed(25);
            agent.setHP(5);
            agent.setDmg(3);
            agent.setPath(generatePath());
        }
        return agent;
    }

}
