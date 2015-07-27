/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Entity.WaveManager;
import Math.CoordinateTranslator;
import Math.PointManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author Bakuryu
 */
public class SideMenuGUI
{

    private CoordinateTranslator corT2;
    private OrthographicCamera cam;
    private SpriteBatch sBatch;
    private TowerButton regTowerB;
    private TowerButton supTowerB;
    private Skin skin;
    private Stage stage;
    private String sType;
    private BitmapFont font;
    private int towerPoints;
    private PointManager pointM;
    private ShapeDrawer sDraw;
    private WaveManager waveM;

    public SideMenuGUI(CoordinateTranslator corT2, PointManager pM, WaveManager wM)
    {
        waveM = wM;
        sDraw = new ShapeDrawer();
        pointM = pM;
        sType = "reg";
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage();

        sBatch = new SpriteBatch();
        this.corT2 = corT2;
        cam = new OrthographicCamera(112, 560);
        font = new BitmapFont();
        regTowerB = new TowerButton(6.5, 57, "reg", corT2,pointM);
        supTowerB = new TowerButton(6.5, 36, "sup", corT2,pointM);

    }

    public void update(float t)
    {

    }

    public void render()
    {
        cam.position.set(640f, 0, 0);

        sDraw.drawRect((int) cam.position.x, (int) cam.position.y, 112, 560, 2, Color.WHITE);
        sDraw.drawLine(corT2.worldToScreen(0, 77).x, corT2.worldToScreen(0, 77).y, corT2.worldToScreen(17.5, 77).x, corT2.worldToScreen(17.5, 77).y, 3, Color.WHITE);
        renderScore();
        renderTSelect();
        renderWave();

    }

    private void renderScore()
    {
        sDraw.drawLine(corT2.worldToScreen(2, 95).x, corT2.worldToScreen(2, 95).y, corT2.worldToScreen(15, 95).x, corT2.worldToScreen(15, 95).y, 2, Color.WHITE);
        sBatch.begin();
        font.draw(sBatch, "Tower Points", corT2.worldToScreen(2, 98).x, corT2.worldToScreen(2, 98).y);
        font.draw(sBatch, "" + (pointM.getPoints()), corT2.worldToScreen(8, 92).x, corT2.worldToScreen(8, 92).y);
        sBatch.end();
    }

    private void renderTSelect()
    {
        sBatch.begin();
        font.draw(sBatch, "Towers", corT2.worldToScreen(5, 74).x, corT2.worldToScreen(5, 74).y);
        font.draw(sBatch, "Regular", corT2.worldToScreen(4.9, 68).x, corT2.worldToScreen(4.9, 68).y);
        font.draw(sBatch, "Cost: 3", corT2.worldToScreen(5.2, 54).x, corT2.worldToScreen(5.2, 54).y);
        font.draw(sBatch, "Super", corT2.worldToScreen(5.7, 47).x, corT2.worldToScreen(5.7, 47).y);
        font.draw(sBatch, "Cost: 8", corT2.worldToScreen(5.2, 33).x, corT2.worldToScreen(5.2, 33).y);
        //sBatch.begin();egTowerB.getSprite()
        sBatch.draw(regTowerB.getSprite(), corT2.worldToScreen(regTowerB.getPosition()).x, corT2.worldToScreen(regTowerB.getPosition()).y, regTowerB.getSprite().getWidth() / 2, regTowerB.getSprite().getHeight() / 2, regTowerB.getSprite().getWidth(), regTowerB.getSprite().getHeight(), (float) 1.5, (float) 1.5, 0);
        sBatch.draw(supTowerB.getSprite(), corT2.worldToScreen(regTowerB.getPosition()).x, corT2.worldToScreen(supTowerB.getPosition()).y, regTowerB.getSprite().getWidth() / 2, supTowerB.getSprite().getHeight() / 2, supTowerB.getSprite().getWidth(), supTowerB.getSprite().getHeight(), (float) 1.5, (float) 1.5, 0);

        //sBatch.end();
        sBatch.end();
        sDraw.drawLine(corT2.worldToScreen(5, 71).x, corT2.worldToScreen(5, 71).y, corT2.worldToScreen(12.5, 71).x, corT2.worldToScreen(12.5, 71).y, 2, Color.WHITE);

        regTowerB.render();
        supTowerB.render();

    }

    private void renderWave()
    {
        sBatch.begin();
        font.draw(sBatch, "Wave", corT2.worldToScreen(5.5, 86).x, corT2.worldToScreen(5.5, 86).y);
        font.draw(sBatch, "" + waveM.getCurWave(), corT2.worldToScreen(8, 81).x, corT2.worldToScreen(8, 81).y);
        sBatch.end();
        sDraw.drawLine(corT2.worldToScreen(5.5, 83).x, corT2.worldToScreen(5.5, 83).y, corT2.worldToScreen(11.5, 83).x, corT2.worldToScreen(11.5, 83).y, 2, Color.WHITE);

    }

    public String getSelectedType()
    {

        if (regTowerB.getIsSelected())
        {
            sType = regTowerB.getTButtonType();
        }
        
        if(supTowerB.getIsSelected())
        {
            sType = supTowerB.getTButtonType();
        }

        return sType;
    }

    public boolean isButtonSelected()
    {
        
        return regTowerB.getIsSelected() || supTowerB.getIsSelected();
    }

    public void deselectButtons()
    {
        regTowerB.deselectButton();
        supTowerB.deselectButton();
    }
}
