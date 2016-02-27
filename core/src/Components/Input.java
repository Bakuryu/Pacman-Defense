/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 *
 * @author Bakuryu
 */
public class Input
{

    public String checkKeys()
    {
        if (Gdx.input.isKeyPressed(Keys.RIGHT) && !(Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.LEFT)))// && !(Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.UP)))// && !(Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.DOWN)))
        {
            if (Gdx.input.isKeyPressed(Keys.DOWN))
            {
                return "DOWNR";
            }
            else if (Gdx.input.isKeyPressed(Keys.UP))
            {
                return "UPR";
            }
            else
            {
                return "RIGHT";
            }

        }
        else if (Gdx.input.isKeyPressed(Keys.LEFT) && !(Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.RIGHT)))// && !(Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.UP)) && !(Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.DOWN)))
        {
            if (Gdx.input.isKeyPressed(Keys.DOWN))
            {
                return "DOWNL";
            }
            else if (Gdx.input.isKeyPressed(Keys.UP))
            {
                return "UPL";
            }
            else
            {
                return "LEFT";
            }
        }
        else if (Gdx.input.isKeyPressed(Keys.UP) && /*!(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.RIGHT)) && */ !(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.LEFT)) && !(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.DOWN)))
        {
            if (Gdx.input.isKeyPressed(Keys.RIGHT))
            {
                return "UPR";
            }
            else if (Gdx.input.isKeyPressed(Keys.LEFT))
            {
                return "UPL";
            }
            else
            {
                return "UP";
            }
        }
        else if (Gdx.input.isKeyPressed(Keys.DOWN) && /*!(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.RIGHT)) &&*/ !(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.UP)) && !(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.LEFT)))
        {
            if (Gdx.input.isKeyPressed(Keys.RIGHT))
            {
                return "DOWNR";
            }
            else if (Gdx.input.isKeyPressed(Keys.LEFT))
            {
                return "DOWNL";
            }
            else
            {
                return "DOWN";
            }
        }
        else
        {
            return "NONE";
        }
    }
}
