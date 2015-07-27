/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Math.CoordinateTranslator;
import Math.Point2D;
import Math.PointManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.awt.Point;

/**
 *
 * @author Bakuryu
 */
public class TowerButton
{

    private Sprite towerSpr;
    private String towerType;
    private Point2D position;
    private ShapeDrawer sDraw;
    private SpriteBatch sBatch;
    private CoordinateTranslator corT2;
    private Rectangle buttonOutline;
    private Point2D mouseWP;
    private Point mouseSP;
    private boolean isSelected;
    private PointManager pointM;

    public TowerButton(double x, double y, String type, CoordinateTranslator corT2, PointManager pM)
    {
        buttonOutline = new Rectangle();
        pointM = pM;
        this.corT2 = corT2;
        sBatch = new SpriteBatch();
        sDraw = new ShapeDrawer();
        position = new Point2D(x, y);
        mouseWP = new Point2D();
        mouseSP = new Point();

        towerType = type;
        isSelected = false;
        createButton();
    }

    public void render()
    {

        if (isSelected)
        {
            sDraw.drawRect((int) (buttonOutline.x - 10), (int) (buttonOutline.y - 10), (int) buttonOutline.width, (int) buttonOutline.height, 3, Color.BLUE);
        }
        mouseSP = new Point(Gdx.input.getX(), 560 - Gdx.input.getY());

        mouseWP = new Point2D(corT2.screenToWorld(mouseSP));

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            if (buttonOutline.contains(mouseSP.x, mouseSP.y))
            {
                if (towerType == "reg" && pointM.getPoints() >= 3 || towerType == "sup" && pointM.getPoints() >= 8)
                {
                    isSelected = true;
                }
            }
        }
    }

    private void createButton()
    {
        if (towerType == "reg")
        {
            towerSpr = new Sprite(new Texture("graphics/RegTower.png"));
            buttonOutline = new Rectangle(corT2.worldToScreen(position).x, corT2.worldToScreen(position).y, (int) (towerSpr.getWidth() * 1.5) + 3, (int) (towerSpr.getHeight() * 1.5) + 3);
        }

        if (towerType == "sup")
        {
            towerSpr = new Sprite(new Texture("graphics/SupTower.png"));
            buttonOutline = new Rectangle(corT2.worldToScreen(position).x, corT2.worldToScreen(position).y, (int) (towerSpr.getWidth() * 1.5) + 3, (int) (towerSpr.getHeight() * 1.5) + 3);

        }
//        sBatch.begin();
//        sBatch.draw(towerSpr, corT2.worldToScreen(position).x, corT2.worldToScreen(position).y, towerSpr.getWidth() / 2, towerSpr.getHeight() / 2, towerSpr.getWidth(), towerSpr.getHeight(), (float) 1.5, (float) 1.5, 0);
//        sBatch.end();
        //sDraw.drawRect(buttonOutline.x - 10, buttonOutline.y - 10, (int) (buttonOutline.width * 1.5) + 3, (int) (buttonOutline.height * 1.5) + 3, 3, Color.RED);
        //sDraw.drawRect((int)(corT2.worldToScreen(position).x-10), (int)(corT2.worldToScreen(position).y-10), (int)(towerSpr.getWidth()*1.5)+3, (int)(towerSpr.getHeight()*1.5)+3, 3, Color.BLACK);

    }

    public void deselectButton()
    {
        isSelected = false;
    }

    public boolean getIsSelected()
    {
        return isSelected;
    }

    public Sprite getSprite()
    {
        return towerSpr;
    }

    public Point2D getPosition()
    {
        return position;
    }

    public String getTButtonType()
    {
        return towerType;
    }
}
