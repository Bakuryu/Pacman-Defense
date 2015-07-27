/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Entity.AgentEntity;
import Entity.Entity;
import Entity.EntityManager;
import Entity.TowerEntity;
import Math.CoordinateTranslator;
import Math.Point2D;
import Math.PointManager;
import Math.TileConverter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Bakuryu
 */
public class TowerGUI
{

    private Input input;
    private Point mouseSP;
    private Point2D mouseWP;
    private Point2D noConsMouseWP;
    private CoordinateTranslator corT;
    private ShapeRenderer sRender;
    private GameMap gMap;

    private SpriteBatch sBatch;

    private Point2D tileInWorld;
    private Point tileInScreen;
    private EntityManager entM;
    private SideMenuGUI sGUI;
    private TileConverter tCon;
    private PointManager pointM;
    private ShapeDrawer sDraw;

    private boolean isPlacing;

    public TowerGUI(GameMap gMap, CoordinateTranslator corT, EntityManager entM, SideMenuGUI sGUI, PointManager pM)
    {
        sDraw = new ShapeDrawer();
        pointM = pM;
        this.sGUI = sGUI;
        this.entM = entM;
        isPlacing = false;
        sRender = new ShapeRenderer();
        sBatch = new SpriteBatch();
        this.corT = corT;
        mouseSP = new Point();
        mouseWP = new Point2D();
        noConsMouseWP = new Point2D();
        this.gMap = gMap;
        tCon = new TileConverter();

    }

    public void update(float t)
    {
//        drawAgentHitBox((AgentEntity)entM.getEnts().get(0));

        input = Gdx.input;
        mouseSP = new Point(input.getX(), input.getY());
        mouseWP = corT.screenToWorld(mouseSP);
        noConsMouseWP = new Point2D(mouseWP);
        if(input.isButtonPressed(Input.Buttons.RIGHT) || pointM.getPoints() < 3)
        {
            isPlacing = false;
            sGUI.deselectButtons();
        }
        
        if(sGUI.isButtonSelected())
        {
            isPlacing = true;
        }
       

        //System.out.println("TileCord: " + tx + ", " + ty);
        if (isPlacing && !(noConsMouseWP.getX() > 100))
        {
            if (input.isButtonPressed(Input.Buttons.LEFT))
            {
                if (isLegal(tileInWorld) && pointM.getPoints() >= 3)
                {

                    String type = sGUI.getSelectedType();
                    if (type == "reg")
                    {
                        pointM.subPoints(3);
                    }
                    else
                    {
                        pointM.subPoints(8);
                    }
                    TowerEntity tEntity = new TowerEntity(type, tileInWorld, entM);
                    entM.addEnt(tEntity);

                }
            }
        }

    }

    public void render()
    {

        checkMouseBounds();

        tileInWorld = new Point2D(tCon.convertFromTileCord(tCon.convertToTileCord(mouseWP).x, tCon.convertToTileCord(mouseWP).y));
        tileInScreen = corT.worldToScreen(tileInWorld);

        if (isPlacing)
        {
            for (Rectangle r : gMap.getFreeRect())
            {

                sDraw.drawRect(r.x, r.y, r.width, r.height, 1, Color.WHITE);

            }

            if (isLegal(tileInWorld) && !(noConsMouseWP.getX() > 100))
            {

                if (isEdgeR(tileInWorld))
                {
                    sDraw.drawRect((int) tileInScreen.getX() - 16, (int) tileInScreen.getY() - 16, 32, 32, 4, Color.GREEN);
                }
                else
                {
                    sDraw.drawRect((int) tileInScreen.getX(), (int) tileInScreen.getY() - 16, 32, 32, 4, Color.GREEN);
                }

            }

            if (!isLegal(tileInWorld) && !(noConsMouseWP.getX() > 100))
            {

                if (isEdgeR(tileInWorld))
                {
                    sDraw.drawRect((int) tileInScreen.getX() - 16, (int) tileInScreen.getY() - 16, 32, 32, 4, Color.RED);
                }
                else
                {
                    sDraw.drawRect((int) tileInScreen.getX(), (int) tileInScreen.getY() - 16, 32, 32, 4, Color.RED);
                }
            }

        }

    }

    private boolean isLegal(Point2D p)
    {
        int i = 0;
        int rEnd = 0;
        int j = 0;
        int cEnd = 0;
        boolean isLegal = true;
        boolean hasBlockedTile = false;
        Point pTileCord = new Point(tCon.convertToTileCord(p));

        int tx = pTileCord.x;
        int ty = pTileCord.y;

        //A tile is open to the the left or above the current position
        if (pTileCord.x != 39 && pTileCord.y != 34)
        {
            i = pTileCord.x;
            j = pTileCord.y;

            cEnd = j - 2;

            rEnd = i + 2;
        }

        if (pTileCord.x == 39 && pTileCord.y != 34)
        {
            i = pTileCord.x - 1;
            j = pTileCord.y;

            cEnd = j - 2;

            rEnd = i + 2;
        }

        if (pTileCord.x != 39 && pTileCord.y == 34)
        {
            i = pTileCord.x;
            j = pTileCord.y;

            cEnd = j - 2;

            rEnd = i + 2;
        }

        if (pTileCord.x == 39 && pTileCord.y == 34)
        {
            i = pTileCord.x - 1;
            j = pTileCord.y - 1;

            cEnd = j - 2;
            rEnd = i + 2;

        }
        //System.out.println("DrawTile: " + i + ", " + j);
        for (int k = j; k > cEnd; k--)
        {
            //System.out.println("");
            for (int l = i; l < rEnd; l++)
            {
//                System.out.print("(" + l + ", " + k + ") ");
//                System.out.print(isNeighborBlocked(l, k) + " ");
//                System.out.print(isTower(l,k));

                if (isNeighborBlocked(l, k) || isTower(l, k))
                {
                    hasBlockedTile = true;
                }

            }
        }
        //System.out.println("");
        if (hasBlockedTile == false)
        {
            isLegal = true;
        }

        if (hasBlockedTile == true)
        {
            isLegal = false;
        }
        //System.out.println("IsLegal?: " + isLegal);
        return isLegal;
    }

    private boolean isEdgeR(Point2D p)
    {
        Point pTileCord = new Point(tCon.convertToTileCord(p));
        return pTileCord.x == 39;
    }

//Don't need this apparently?   
//    private boolean isEdgeD(Point2D p)
//    {
//        Point pTileCord = new Point(convertToTileCord(p));
//        return isNeighborBlocked(pTileCord.x, pTileCord.y - 1);
//    }
    public boolean isNeighborBlocked(int x, int y)
    {
        boolean isBlocked[][] = gMap.getBlocked();
        return (isBlocked[x][y]);

    }

    public void checkMouseBounds()
    {
        if (mouseWP.getX() > 97.5)
        {
            mouseWP.setX(97.5);
        }
        if (mouseWP.getY() > 97.5)
        {
            mouseWP.setY(97.5);
        }

        if (mouseWP.getY() < (double) 100 / 35)
        {
            mouseWP.setY((double) 100 / 35);
        }
    }

    public void setIsPlacing(boolean b)
    {
        isPlacing = b;
    }

    public boolean IsPlacing()
    {
        return isPlacing;
    }

    private boolean isTower(int x, int y)
    {
        Point mTileCord = new Point(x, y);
        boolean TowerBlock = false;
        for (Entity e : entM.getEnts())
        {
            if (e instanceof TowerEntity)
            {
//                System.out.println("T Pos: " + e.getPosition());
//                System.out.println("TIW: " + tileInWorld);
                //if()
                TowerEntity t = (TowerEntity) e;

                if ((t.getTilesCovered().contains(mTileCord)))
                {
                    TowerBlock = true;
                }
            }
        }
        return TowerBlock;
    }

    public void drawAgentHitBox(AgentEntity a)
    {
        sBatch.begin();
        sBatch.setColor(Color.WHITE);
        sDraw.drawRect(a.getCollider().getHitBox().x, a.getCollider().getHitBox().y, a.getCollider().getHitBox().width, a.getCollider().getHitBox().height, 2, Color.WHITE);
        sBatch.end();
    }
}
