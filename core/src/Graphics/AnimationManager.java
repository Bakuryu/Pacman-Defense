/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Bakuryu
 */
public class AnimationManager
{

    private Texture blinky;
    private Texture inky;
    private Texture pinky;
    private Texture clyde;
    private Texture pacman;
    private Texture scared;
    private TextureRegion[] animFrames;
    private TextureRegion[][] tmp;
    private int AFRAME_COL;
    private int AFRAME_ROW;
    private int PFRAME_COL;
    private int PFRAME_ROW;
    private String curPAnim;


    /* Animation to be return to entity*/
    private Animation anim;

    /* Temporary animation variable for storing the next animation*/
    /**
     * Create an AnimationManager, load player and agent sprite into sprite
     * sheets, and initialize variables.
     *
     */
    public AnimationManager()
    {
        AFRAME_COL = 2;
        AFRAME_ROW = 4;
        PFRAME_COL = 4;
        PFRAME_ROW = 1;
        blinky = new Texture("graphics/Blinky.png");
        inky = new Texture("graphics/Inky.png");
        pinky = new Texture("graphics/Pinky.png");
        clyde = new Texture("graphics/Clyde.png");
        pacman = new Texture("graphics/PacManAnim.png");
        scared = new Texture("graphics/BlueGhostAnim.png");
        curPAnim = "IDLE";

    }

    /* Retrieve animation based on entity type*/
    public Animation setAgentAnimation(String type, String dir)
    {
        switch (type)
        {
            case "Blinky":
                tmp = TextureRegion.split(blinky, blinky.getWidth() / AFRAME_COL, blinky.getHeight() / AFRAME_ROW);
                break;
            case "Inky":
                tmp = TextureRegion.split(inky, inky.getWidth() / AFRAME_COL, inky.getHeight() / AFRAME_ROW);
                break;
            case "Pinky":
                tmp = TextureRegion.split(pinky, pinky.getWidth() / AFRAME_COL, pinky.getHeight() / AFRAME_ROW);
                break;
            case "Clyde":
                tmp = TextureRegion.split(clyde, clyde.getWidth() / AFRAME_COL, clyde.getHeight() / AFRAME_ROW);
                break;
        }
        animFrames = new TextureRegion[2];
        if (dir == "L")
        {

            animFrames[0] = tmp[2][0];
            animFrames[1] = tmp[2][1];

        }

        if (dir == "R")
        {
            animFrames[0] = tmp[3][0];
            animFrames[1] = tmp[3][1];
        }

        if (dir == "U")
        {
            animFrames[0] = tmp[0][0];
            animFrames[1] = tmp[0][1];
        }

        if (dir == "D")
        {
            animFrames[0] = tmp[1][0];
            animFrames[1] = tmp[1][1];
        }

        if (type == "Scared")
        {
            tmp = TextureRegion.split(scared, scared.getWidth() / 2, scared.getHeight());
            animFrames = new TextureRegion[AFRAME_ROW * AFRAME_COL];
            int index = 0;
            for (int i = 0; i < AFRAME_ROW; i++)
            {
                for (int j = 0; j < AFRAME_COL; j++)
                {
                    animFrames[index++] = tmp[i][j];
                }

            }
        }

        anim = new Animation(0.15f, animFrames);
        return anim;
    }

    public Animation setPlayerAnimation(String newAnim)
    {
        tmp = TextureRegion.split(pacman, pacman.getWidth() / 4, pacman.getHeight());
        animFrames = new TextureRegion[PFRAME_ROW * PFRAME_COL];
        int index = 0;
        for (int i = 0; i < PFRAME_ROW; i++)
        {
            for (int j = 0; j < PFRAME_COL; j++)
            {
                animFrames[index++] = tmp[i][j];
            }

        }
        anim = new Animation(0.05f, animFrames);
        return anim;
    }

}
