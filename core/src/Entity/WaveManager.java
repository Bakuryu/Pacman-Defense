/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.LinkedList;

/**
 *
 * @author Bakuryu
 */
public class WaveManager
{

    private AgentEntityFactory aFact;
    private LinkedList<AgentEntity> curWave;
    private int maxEnemy;
    private int totalEnemy;
    private int waveNum;
    private EntityManager entM;
    private long spawnSTimer;
    private boolean timerStart;
    private int spwnCoolD;
    private int waveCompTotal;
    private int totalWaves;
    private boolean start;

    public WaveManager(AgentEntityFactory aFact, EntityManager entM)
    {
        curWave = new LinkedList<AgentEntity>();
        spwnCoolD = 3;
        this.entM = entM;
        this.aFact = aFact;
        maxEnemy = 2;
        waveNum = 1;
        waveCompTotal = 0;
        totalWaves = 0;
    }

    public void update(float t)
    {
        getTotalEnemies();
        if (waveNum + 1 != 13)
        {
            if (curWave.isEmpty() && totalEnemy == 0)
            {
                if (start)
                {
                    waveCompTotal += 1;
                    totalWaves += 1;
                }
                generateWave(t);

                start = true;
            }

            if (!curWave.isEmpty())
            {
                spawnWave();
            }
        }
    }

    private void generateWave(float t)
    {
        if (waveCompTotal > 2)
        {
            waveCompTotal = 0;
            waveNum += 1;
        }

        if (waveNum == 5 && waveNum == 9)
        {
            maxEnemy += 1;
        }
        curWave = aFact.getAgents(waveNum, maxEnemy);
    }

    private void spawnWave()
    {
        if (!timerStart)
        {
            spawnSTimer = System.currentTimeMillis();
            entM.addEnt(curWave.pop());

            timerStart = true;
        }
        if (timerStart)
        {
            long counter;
            counter = System.currentTimeMillis() - spawnSTimer;
            if (counter >= spwnCoolD * 1000)
            {
                timerStart = false;
            }
        }
    }

    private void getTotalEnemies()
    {
        totalEnemy = 0;
        for (Entity e : entM.getEnts())
        {
            if (e instanceof AgentEntity)
            {
                totalEnemy += 1;
            }
        }
    }

    public int getTNumWaves()
    {
        return totalWaves;
    }

    public int getCurWave()
    {
        return waveNum;
    }
}
