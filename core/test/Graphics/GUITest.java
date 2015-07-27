/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import com.badlogic.gdx.Gdx;
import Math.CoordinateTranslator;
import Math.Point2D;
import com.badlogic.gdx.Gdx;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Bakuryu
 */
public class GUITest
{

    private GameMap gMap;
    private GUI gUI;
    private CoordinateTranslator corT;

    public GUITest()
    {
        gMap = new GameMap();
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        GameMap gMap = new GameMap();
        CoordinateTranslator corT = new CoordinateTranslator(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 100, 100, new Point2D(0, 0));
        GUI gUI = new GUI(gMap, corT);
    }

    /**
     * Test the isNeighborBlocked method
     */
    @Test
    public void testIsNeighborBlocked()
    {
        assertTrue(!gUI.isNeighborBlocked(0, 0));
    }

    /**
     * Test of update method, of class GUI.
     */
    @Test
    public void testUpdate()
    {

    }

    /**
     * Test of render method, of class GUI.
     */
    @Test
    public void testRender()
    {

    }

}
