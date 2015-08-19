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
    private AgentEntity agentB;
    private AgentEntity agentI;
    private AgentEntity agentP;
    private AgentEntity agentC;
    private PlayerEntity player;
    private float deltaTime;
    private SpriteRenderer sRen;
    private FPSLogger fLog;
    private TowerGUI tGUI;
    private Stage stage;
    //private SideMenuGUI sGUI;
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

        
//        aFactory = new AgentEntityFactory(player, pointM);
//       waveM = new WaveManager(aFactory, entM);
        
        agentB = new AgentEntity(29.5, 26, "Blinky",player,pointM);
        agentI = new AgentEntity(24, 21, "Inky",player,pointM);
        agentP = new AgentEntity(29.5, 21, "Pinky",player,pointM);
        agentC = new AgentEntity(35, 21, "Clyde",player,pointM);
        player = new PlayerEntity(29.5, 16,gMap);
        entM.addEnt(player);
        entM.addEnt(agentB);
        entM.addEnt(agentI);
        entM.addEnt(agentP);
        entM.addEnt(agentC);
        
        corT1 = new CoordinateTranslator(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-100, 61, 42, new Point2D(0, 0));
        corT2 = new CoordinateTranslator(Gdx.graphics.getWidth() - 640, Gdx.graphics.getHeight(), 17.5, 100, new Point2D(-100, 0));
        sRen = new SpriteRenderer(entM, corT1,gMap);

        //sGUI = new SideMenuGUI(corT2, pointM, waveM);
        //tGUI = new TowerGUI(gMap, corT1, entM, sGUI, pointM);
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

            //tGUI.render();

            //sGUI.render();
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
//        tGUI.update(deltaTime);
//        waveM.update(deltaTime);
//        if (waveM.getCurWave() >= 12)
//        {
//            isGameWon = true;
//        }
        entM.updateEnts(deltaTime);
//        if (player.getHP() <= 0)
//        {
//            pacDie.play();
//            isGameOver = true;
//        }

    }
}
