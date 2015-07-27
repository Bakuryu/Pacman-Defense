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
    private int AFRAME_COL;
    private int AFRAME_ROW;
    private int PFRAME_COL;
    private int PFRAME_ROW;


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
        AFRAME_ROW = 1;
        PFRAME_COL = 4;
        PFRAME_ROW = 1;
        blinky = new Texture("graphics/BlinkyAnim2.png");
        inky = new Texture("graphics/InkyAnim2.png");
        pinky = new Texture("graphics/PinkyAnim2.png");
        clyde = new Texture("graphics/ClydeAnim2.png");
        pacman = new Texture("graphics/PacmanAnim.png");
        scared = new Texture("graphics/BlueGhostAnim.png");

    }

    /* Retrieve animation based on entity type*/
    public Animation setAgentAnimation(String type)
    {

        if (type == "Blinky")
        {
            TextureRegion[][] tmp = TextureRegion.split(blinky, blinky.getWidth() / 2, blinky.getHeight());
            animFrames = new TextureRegion[AFRAME_ROW * AFRAME_COL];
            int index = 0;
            for (int i = 0; i < AFRAME_ROW; i++)
            {
                for (int j = 0; j < AFRAME_COL; j++)
                {
                    animFrames[index++] = tmp[i][j];
                }

            }
            anim = new Animation(0.15f, animFrames);

        }

        if (type == "Inky")
        {
            TextureRegion[][] tmp = TextureRegion.split(inky, inky.getWidth() / 2, inky.getHeight());
            animFrames = new TextureRegion[AFRAME_ROW * AFRAME_COL];
            int index = 0;
            for (int i = 0; i < AFRAME_ROW; i++)
            {
                for (int j = 0; j < AFRAME_COL; j++)
                {
                    animFrames[index++] = tmp[i][j];
                }

            }
            anim = new Animation(0.15f, animFrames);
        }

        if (type == "Pinky")
        {
            TextureRegion[][] tmp = TextureRegion.split(pinky, pinky.getWidth() / 2, pinky.getHeight());
            animFrames = new TextureRegion[AFRAME_ROW * AFRAME_COL];
            int index = 0;
            for (int i = 0; i < AFRAME_ROW; i++)
            {
                for (int j = 0; j < AFRAME_COL; j++)
                {
                    animFrames[index++] = tmp[i][j];
                }

            }
            anim = new Animation(0.15f, animFrames);
        }

        if (type == "Clyde")
        {
            TextureRegion[][] tmp = TextureRegion.split(clyde, clyde.getWidth() / 2, clyde.getHeight());
            animFrames = new TextureRegion[AFRAME_ROW * AFRAME_COL];
            int index = 0;
            for (int i = 0; i < AFRAME_ROW; i++)
            {
                for (int j = 0; j < AFRAME_COL; j++)
                {
                    animFrames[index++] = tmp[i][j];
                }

            }
            anim = new Animation(0.15f, animFrames);
        }


        if (type == "Scared")
        {
            TextureRegion[][] tmp = TextureRegion.split(scared, scared.getWidth() / 2, scared.getHeight());
            animFrames = new TextureRegion[AFRAME_ROW * AFRAME_COL];
            int index = 0;
            for (int i = 0; i < AFRAME_ROW; i++)
            {
                for (int j = 0; j < AFRAME_COL; j++)
                {
                    animFrames[index++] = tmp[i][j];
                }

            }
            anim = new Animation(0.15f, animFrames);
        }
        return anim;
    }

    public Animation setPlayerAnimation()
    {
        TextureRegion[][] tmp = TextureRegion.split(pacman, pacman.getWidth() / 4, pacman.getHeight());
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
