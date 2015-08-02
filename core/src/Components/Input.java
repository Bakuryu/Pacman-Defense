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

    private int keyPressed;
    
    public Input()
    {
        keyPressed = 0;
    }
    
    public String checkKeys()
    {
        if (Gdx.input.isKeyPressed(Keys.RIGHT) && !(Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.LEFT)) && !(Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.UP)) && !(Gdx.input.isKeyPressed(Keys.RIGHT) && Gdx.input.isKeyPressed(Keys.DOWN)))
        {
            return "RIGHT";
        }
        else if (Gdx.input.isKeyPressed(Keys.LEFT) && !(Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.RIGHT)) && !(Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.UP)) && !(Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.DOWN)))
        {
            return "LEFT";
        }
        else if (Gdx.input.isKeyPressed(Keys.UP) && !(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.RIGHT)) && !(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.LEFT)) && !(Gdx.input.isKeyPressed(Keys.UP) && Gdx.input.isKeyPressed(Keys.DOWN)))
        {
            return "UP";
        }
        else if (Gdx.input.isKeyPressed(Keys.DOWN) && !(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.RIGHT)) && !(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.UP)) && !(Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.LEFT)))
        {
            return "DOWN";
        }
        else
        {
            return "NONE";
        }
        
    }
}
