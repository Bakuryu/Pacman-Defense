package TWDGDX;

import Entity.AgentEntity;
import Entity.AgentEntityFactory;
import Entity.EntityManager;
import Entity.PlayerEntity;
import Entity.WaveManager;
import Graphics.TowerGUI;
import Graphics.GameMap;
import Graphics.SideMenuGUI;
import Graphics.SpriteRenderer;
import Math.CoordinateTranslator;
import Math.Point2D;
import Math.PointManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TWDGame extends ApplicationAdapter
{

    private GameMap gMap;
    private EntityManager entM;
    private AgentEntity agent;
    private PlayerEntity player;
    private float deltaTime;
    private SpriteRenderer sRen;
    private FPSLogger fLog;
    private TowerGUI tGUI;
    private Stage stage;
    private SideMenuGUI sGUI;
    private PointManager pointM;
    private WaveManager waveM;
    private AgentEntityFactory aFactory;
    private CoordinateTranslator corT1;
    private CoordinateTranslator corT2;
    private boolean isGameWon;
    private boolean isGameOver;
    private BitmapFont font;
    private SpriteBatch sBatch;
    private Sound gameStart;
    private Sound pacDie;

    @Override
    public void create()
    {
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        sBatch = new SpriteBatch();
        fLog = new FPSLogger();
        deltaTime = 0f;
        gMap = new GameMap(stage);
        entM = new EntityManager();
        pointM = new PointManager();
        pointM.addPoints(6);

        player = new PlayerEntity(10, 73);
        aFactory = new AgentEntityFactory(player, pointM);
        waveM = new WaveManager(aFactory, entM);
        //agent = new AgentEntity(70, -5.71, "Blinky",player,pointM);
        //entM.addEnt(agent);
        entM.addEnt(player);
        corT1 = new CoordinateTranslator(Gdx.graphics.getWidth() - 112, Gdx.graphics.getHeight(), 100, 100, new Point2D(0, 0));
        corT2 = new CoordinateTranslator(Gdx.graphics.getWidth() - 640, Gdx.graphics.getHeight(), 17.5, 100, new Point2D(-100, 0));
        sRen = new SpriteRenderer(entM, corT1);

        sGUI = new SideMenuGUI(corT2, pointM, waveM);
        tGUI = new TowerGUI(gMap, corT1, entM, sGUI, pointM);
        gameStart = Gdx.audio.newSound(Gdx.files.internal("sound/startM.wav"));
        gameStart.play();
        pacDie = Gdx.audio.newSound(Gdx.files.internal("sound/pacDie.wav"));
        //curFrame = new TextureRegion();
    }

    @Override
    public void render()
    {
        stage.getViewport().apply();
        stage.draw();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        deltaTime = 400 * Gdx.graphics.getRawDeltaTime();
        if (!isGameOver && !isGameWon)
        {
            //fLog.log();
            gMap.render();

            tGUI.render();

            sGUI.render();
            sRen.render();

            update();
        }
        else if (isGameOver)
        {
            sBatch.begin();
            font.draw(sBatch, "Game Over", (float) (Gdx.graphics.getWidth() / 2), (float) (Gdx.graphics.getHeight() / 2));
            sBatch.end();
        }
        else
        {
            sBatch.begin();
            font.draw(sBatch, "You Won!", (float) (Gdx.graphics.getWidth() / 2), (float) (Gdx.graphics.getHeight() / 2));
            sBatch.end();
        }

    }

    public void update()
    {
        tGUI.update(deltaTime);
        waveM.update(deltaTime);
        if (waveM.getCurWave() >= 12)
        {
            isGameWon = true;
        }
        entM.updateEnts(deltaTime);
        if (player.getHP() <= 0)
        {
            pacDie.play();
            isGameOver = true;
        }

    }
}
