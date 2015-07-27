/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Bakuryu
 */
public class ShapeDrawer
{

    private Texture whiteRect;
    private SpriteBatch sBatch;
    private TextureRegion rect;

    public ShapeDrawer()
    {
        whiteRect = new Texture("graphics/whiteRect.png");
        sBatch = new SpriteBatch();
        rect = new TextureRegion(whiteRect);

    }

    public void drawRect(int x, int y, int width, int height, int thickness,Color c)
    {
        sBatch.setColor(c);
        sBatch.begin();
        sBatch.draw(rect, x, y, width, thickness);
        sBatch.draw(rect, x, y, thickness, height);
        sBatch.draw(rect, x, y + height - thickness, width, thickness);
        sBatch.draw(rect, x + width - thickness, y, thickness, height);
        sBatch.end();
    }

    public void drawLine(int x1, int y1, int x2, int y2, int thickness,Color c)
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        //float rad = (float) Math.atan2(dy, dx);
        //sBatch.draw(rect, x1, y1, dist, thickness, 0, 0, rad);
        sBatch.setColor(c);
        sBatch.begin();
        sBatch.draw(rect, x1, y1, dist, thickness);
        sBatch.end();
    }
}