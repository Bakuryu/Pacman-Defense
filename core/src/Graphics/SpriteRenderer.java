/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Entity.AgentEntity;
import Entity.BulletEntity;
import Entity.Entity;
import Entity.EntityManager;
import Entity.PlayerEntity;
import Entity.TowerEntity;
import Math.CoordinateTranslator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.awt.Point;

/**
 *
 * @author Bakuryu
 */
public class SpriteRenderer
{

    private EntityManager entM;
    private Animation anim;
    private TextureRegion curFrame;
    private SpriteBatch sBatch;
    private float stateTime;
    private CoordinateTranslator corT;
    private BitmapFont font;
    private int pRotation;
    private GameMap gMap;
    private ShapeDrawer sDraw;
    private ShapeRenderer shapRen;

    public SpriteRenderer(EntityManager entM, CoordinateTranslator corT, GameMap gMap)
    {
        sBatch = new SpriteBatch();
        this.entM = entM;
        this.corT = corT;
        stateTime = 0f;
        font = new BitmapFont();
        this.gMap = gMap;
        sDraw = new ShapeDrawer();
        shapRen = new ShapeRenderer();
    }

    public void render()
    {

        stateTime += Gdx.graphics.getDeltaTime();
        for (Entity e : entM.getEnts())
        {
            if (e instanceof AgentEntity)
            {
                renderAgentEntity((AgentEntity) e);
            }

            if (e instanceof TowerEntity)
            {
                renderTowerEntity((TowerEntity) e);
            }

            if (e instanceof BulletEntity)
            {
                renderBulletEntity((BulletEntity) e);
            }

            if (e instanceof PlayerEntity)
            {
                renderPlayerEntity((PlayerEntity) e);
            }
        }

    }

    private void renderAgentEntity(AgentEntity a)
    {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        anim = a.getAnimation();
        curFrame = anim.getKeyFrame(stateTime, true);
        sBatch.begin();
        Point aScrPos = new Point(corT.worldToScreen(a.getPosition()));
        a.getCollider().updatePos(aScrPos);
        sBatch.draw(curFrame, (float) aScrPos.getX(), (float) aScrPos.getY());
        sBatch.end();
    }

    private void renderTowerEntity(TowerEntity t)
    {
        sBatch.begin();
        Point tScrPos = new Point(corT.worldToScreen(t.getPosition()));
        //sBatch.draw(t.getTSprite(), tScrPos.x, tScrPos.y - 16);
        //System.out.println("Rotation: " + t.getRotation());
        sBatch.draw(t.getTSprite(), tScrPos.x, tScrPos.y - 16, t.getTSprite().getWidth() / 2, t.getTSprite().getHeight() / 2, t.getTSprite().getWidth(), t.getTSprite().getHeight(), 1, 1, (float) t.getRotation(), 0, 0, t.getTSprite().getWidth(), t.getTSprite().getHeight(), true, true);
        sBatch.end();
    }

    private void renderBulletEntity(BulletEntity b)
    {
        sBatch.begin();
        Point bScrPos = new Point(corT.worldToScreen(b.getPosition()));
        sBatch.draw(b.getBSprite(), bScrPos.x, bScrPos.y - 16);

        //System.out.println("Rotation: " + t.getRotation());
        sBatch.end();
        Point newScrPos = new Point(bScrPos.x, bScrPos.y - 16);
        b.getCollider().updatePos(newScrPos);
    }

    private void renderPlayerEntity(PlayerEntity p)
    {

        anim = p.getAnimation();
        curFrame = anim.getKeyFrame(stateTime, true);
        sBatch.begin();

        Point pScrPos = new Point(corT.worldToScreen(p.getPosition()));
        p.getCollider().updatePos(pScrPos);
        sBatch.setColor(Color.YELLOW);

        switch (p.getDirMove())
        {
            case "R":
                sBatch.draw(curFrame, pScrPos.x, pScrPos.y - 16, 16, 16, (float) curFrame.getRegionWidth(), (float) curFrame.getRegionHeight(), 1, 1, -180);
                break;
            case "L":
                sBatch.draw(curFrame, pScrPos.x, pScrPos.y - 16, 16, 16, (float) curFrame.getRegionWidth(), (float) curFrame.getRegionHeight(), 1, 1, 0);
                break;
            case "U":
                sBatch.draw(curFrame, pScrPos.x, pScrPos.y - 16, 16, 16, (float) curFrame.getRegionWidth(), (float) curFrame.getRegionHeight(), 1, 1, -90);
                break;
            case "D":
                sBatch.draw(curFrame, pScrPos.x, pScrPos.y - 16, 16, 16, (float) curFrame.getRegionWidth(), (float) curFrame.getRegionHeight(), 1, 1, 90);
                break;
        }

        sBatch.end();
        shapRen.begin(ShapeRenderer.ShapeType.Filled);
        shapRen.setColor(Color.RED);
        //shapRen.circle(pScrPos.x, pScrPos.y - 16, 1);
        shapRen.end();
        sBatch.begin();
        sBatch.setColor(Color.GREEN);
        Point colBox = new Point((int)p.getCollider().getHitBox().x,(int)p.getCollider().getHitBox().y);
        for (Rectangle rect : gMap.getMapCollisions())
        {
            Point rectPos = new Point(corT.worldToScreen(rect.x, rect.y));
            sDraw.drawRect((int) rect.x, (int) rect.y-16, (int) rect.width, (int) rect.height, 1, Color.RED);
        }
        //sDraw.drawRect(colBox.x, colBox.y-16, 32, 32, 1, Color.ORANGE);
        sBatch.end();
    }
}
