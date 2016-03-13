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
import Math.Point2D;
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
    private Animation aAnim;
    private Animation pAnim;
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
            renderDebug();
        }

    }

    private void renderAgentEntity(AgentEntity a)
    {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        aAnim = a.getAnimation();
        curFrame = aAnim.getKeyFrame(stateTime, true);
        sBatch.begin();
        Point aScrPos = new Point(corT.worldToScreen(a.getPosition()));
        a.getCollider().updatePos(aScrPos);
        sBatch.draw(curFrame, (float) aScrPos.getX() - 8, (float) aScrPos.getY() - 8);
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
        pAnim = p.getAnimation();
        curFrame = pAnim.getKeyFrame(stateTime, true);
        int rotation = 0;

        sBatch.begin();

        Point pScrPos = new Point(corT.worldToScreen(p.getPosition()));
        p.getHurtBox().updatePos(pScrPos);
        p.getCollider().updatePos(pScrPos);

        switch (p.getDirMove())
        {
            case "R":
                rotation = -180;
                break;
            case "L":
                rotation = 0;
                break;
            case "U":
                rotation = -90;
                break;
            case "D":
                rotation = 90;
                break;
            case "N":
                rotation = 0;
                break;
        }
        sBatch.draw(curFrame, pScrPos.x - 8, pScrPos.y - 8, 16, 16, (float) curFrame.getRegionWidth(), (float) curFrame.getRegionHeight(), 1, 1, rotation);

        sBatch.end();

    }

    private void renderDebug()
    {

        //Player Debug Visuals
        PlayerEntity p = (PlayerEntity) entM.getEnts().get(0);
        //System.out.println("Screen Pos: " + corT.worldToScreen(p.getPosition()));

        Rectangle pRBox = new Rectangle(p.getCollider().getHitBox().x + 16, p.getCollider().getHitBox().y + 3, p.getCollider().getHitBox().width, p.getCollider().getHitBox().height - 6);
        Rectangle pLBox = new Rectangle(p.getCollider().getHitBox().x - 16, p.getCollider().getHitBox().y + 3, p.getCollider().getHitBox().width, p.getCollider().getHitBox().height - 6);
        Rectangle pUBox = new Rectangle(p.getCollider().getHitBox().x + 3, p.getCollider().getHitBox().y + 16, p.getCollider().getHitBox().width - 6, p.getCollider().getHitBox().height);
        Rectangle pDBox = new Rectangle(p.getCollider().getHitBox().x + 3, p.getCollider().getHitBox().y - 16, p.getCollider().getHitBox().width - 6, p.getCollider().getHitBox().height);

        shapRen.begin(ShapeRenderer.ShapeType.Filled);
        shapRen.setColor(Color.RED);
        shapRen.circle((float) corT.worldToScreen(p.getCentPos()).x, (float) corT.worldToScreen(p.getCentPos()).y, 3);
        shapRen.setColor(Color.BLUE);
        shapRen.circle((float) corT.worldToScreen(p.getPosition()).x, (float) corT.worldToScreen(p.getPosition()).y, 1);
        shapRen.end();
        sBatch.begin();
        sDraw.drawRect((int) p.getCollider().getColX(), (int) p.getCollider().getColY(), (int) p.getCollider().getColW(), (int) p.getCollider().getColH(), 1, Color.WHITE);
        sDraw.drawRect((int) p.getHurtBox().getColX(), (int) p.getHurtBox().getColY(), (int) p.getHurtBox().getColW(), (int) p.getHurtBox().getColH(), 1, Color.RED);
        for (Rectangle rect : gMap.getMapCollisions())
        {
            sDraw.drawRect((int) rect.x, (int) rect.y, (int) rect.width, (int) rect.height, 1, Color.RED);
        }

        sDraw.drawRect((int) pRBox.x, (int) pRBox.y, (int) pRBox.width, (int) pRBox.height, 1, Color.ORANGE);
        sDraw.drawRect((int) pLBox.x, (int) pLBox.y, (int) pLBox.width, (int) pLBox.height, 1, Color.ORANGE);
        sDraw.drawRect((int) pUBox.x, (int) pUBox.y, (int) pUBox.width, (int) pUBox.height, 1, Color.ORANGE);
        sDraw.drawRect((int) pDBox.x, (int) pDBox.y , (int) pDBox.width, (int) pDBox.height, 1, Color.ORANGE);
        sBatch.end();

        //Agent Debug Visuals
//        Rectangle aRBox = new Rectangle();
//        Rectangle aLBox = new Rectangle();
//        Rectangle aUBox = new Rectangle();
//        Rectangle aDBox = new Rectangle();
        sBatch.begin();
        for (int i = 1; i < entM.getEnts().size(); i++)
        {
            AgentEntity a = (AgentEntity) entM.getEnts().get(i);
            Rectangle aRBox = new Rectangle(a.getCollider().getHitBox().x + 16, a.getCollider().getHitBox().y + 3, a.getCollider().getHitBox().width, a.getCollider().getHitBox().height - 6);
            Rectangle aLBox = new Rectangle(a.getCollider().getHitBox().x - 16, a.getCollider().getHitBox().y + 3, a.getCollider().getHitBox().width, a.getCollider().getHitBox().height - 6);
            Rectangle aUBox = new Rectangle(a.getCollider().getHitBox().x + 3, a.getCollider().getHitBox().y + 16, a.getCollider().getHitBox().width - 6, a.getCollider().getHitBox().height);
            Rectangle aDBox = new Rectangle(a.getCollider().getHitBox().x + 3, a.getCollider().getHitBox().y - 16, a.getCollider().getHitBox().width - 6, a.getCollider().getHitBox().height);
//            shapRen.begin(ShapeRenderer.ShapeType.Filled);
//            shapRen.setColor(Color.YELLOW);
//            shapRen.circle((float) corT.worldToScreen(a.getCenterPos()).x, (float) corT.worldToScreen(a.getCenterPos()).y, 3);
//            shapRen.setColor(Color.BLUE);
//            shapRen.circle((float) corT.worldToScreen(a.getPosition()).x, (float) corT.worldToScreen(a.getPosition()).y, 1);
//            shapRen.end();
//            sDraw.drawRect((int) a.getCollider().getColX(), (int) a.getCollider().getColY(), (int) a.getCollider().getColW(), (int) a.getCollider().getColH(), 1, Color.WHITE);

//            sDraw.drawRect((int) aRBox.x, (int) aRBox.y - 16, (int) aRBox.width, (int) aRBox.height, 1, Color.ORANGE);
//            sDraw.drawRect((int) aLBox.x, (int) aLBox.y - 16, (int) aLBox.width, (int) aLBox.height, 1, Color.ORANGE);
//            sDraw.drawRect((int) aUBox.x, (int) aUBox.y - 16, (int) aUBox.width, (int) aUBox.height, 1, Color.ORANGE);
//            sDraw.drawRect((int) aDBox.x, (int) aDBox.y - 16, (int) aDBox.width, (int) aDBox.height, 1, Color.ORANGE);
        }
        sBatch.end();

        sBatch.begin();
        boolean[][] validPath = gMap.getAIPaths();
        Point screenCord = new Point();
        for (int j = 0; j < 37; j++)
        {
            for (int i = 0; i < 55; i++)
            {
                if (validPath[i][j])
                {
                    screenCord = new Point(corT.worldToScreen(i, j));
                   // sDraw.drawRect(screenCord.x, screenCord.y, 16, 16, 1, Color.GREEN);
                }
            }
        }
        sBatch.end();
    }

}
