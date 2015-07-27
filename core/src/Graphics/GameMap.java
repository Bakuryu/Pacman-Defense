/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Math.Point2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Bakuryu
 */
public class GameMap
{

    private Texture tex;
    private TiledMap map;
    private OrthographicCamera cam;
    private TiledMapRenderer tRenderer;
    private boolean blocked[][];
    private ArrayList<Rectangle> blocks;
    private ArrayList<Rectangle> free;
    private MapProperties mapProp;
    private MapLayers mapLayer;
    private int numTilesX;
    private int numTilesY;
    private Stage stage;

    public GameMap(Stage stage)
    {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        this.stage = stage;
        //tex = new Texture(Gdx.files.internal("badlogic.jpg"));
        cam = new OrthographicCamera();
        cam.setToOrtho(false, w, h);
        cam.update();
        map = new TmxMapLoader().load("TDMap.tmx");
        tRenderer = new OrthogonalTiledMapRenderer(map);
        mapProp = map.getProperties();
        mapLayer = map.getLayers();
        numTilesX = mapProp.get("width", Integer.class)-7;
        numTilesY = mapProp.get("height", Integer.class);
        free = new ArrayList<Rectangle>();
        blocks = new ArrayList<Rectangle>();
        createColliders();
    }

    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        tRenderer.setView((OrthographicCamera)stage.getCamera());

        tRenderer.render();
    }

    //Figure out which tiles are collidable and store them in an array to for the player to check against.
    public void createColliders()
    {
        // This will create an Array with all the Tiles in your map. When set to true, it means that Tile is blocked.
        blocked = new boolean[numTilesX][numTilesY];

        // Loop through the Tiles and read their Properties
        // Set here the Layer you want to Read. In your case, it'll be layer 1,
        // since the objects are on the second layer.
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Collision");

        for (int j = 0; j <numTilesY; j++)
        {
            for (int i = 0; i < numTilesX; i++)
            {
                if (layer.getCell(i, j) != null)
                {
                    // Read a Tile
                    //TiledMapTile tile2 = (layer.getCell(0, 34).getTile());
                    TiledMapTile tile = layer.getCell(i,j).getTile();

                    String value = tile.getProperties().get("blocked", String.class);

                    // If the value of the Property is "true"...
                    if (value.equals("true"))
                    {

                        //System.out.print("1 ");
                        // We set that index of the TileMap as blocked
                        blocked[i][j] = true;

                        // And create the collision Rectangle
                        Point2D wP = (convertFromTileCord(i, j));

                        blocks.add(new Rectangle((int)(i * layer.getTileWidth()), (int)(j * layer.getTileHeight()), (int)layer.getTileWidth(), (int)layer.getTileHeight()));
                    }
                    else
                    {
                        //System.out.print("0 ");
                    }
                }
                else
                {
                    //System.out.print("0 ");
                    free.add(new Rectangle((int)(i * layer.getTileWidth()), (int)((j) * layer.getTileHeight()), (int)layer.getTileWidth(), (int)layer.getTileHeight()));
                }

            }
            //.out.println("");
        }
    }
    /* Returns list of blocks on the map that are collidable*/

    public ArrayList<Rectangle> getBlocRect()
    {
        return blocks;
    }

    public ArrayList<Rectangle> getFreeRect()
    {
        return free;
    }

    public boolean[][] getBlocked()
    {
        return blocked;
    }

    public TiledMap getMap()
    {
        return map;
    }

    private Point2D convertFromTileCord(int x, int y)
    {
        double wX = 0;
        if (x != 39)
        {
            wX = x * 2.5;
        }
        double hDTF = (double) 100 / 35;
        double wY = (y * hDTF);

        Point2D worldPoint = new Point2D(wX, wY);
        return worldPoint;
    }
}
