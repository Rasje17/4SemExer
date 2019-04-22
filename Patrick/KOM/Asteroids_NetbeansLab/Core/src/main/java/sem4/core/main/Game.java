package sem4.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import sem4.asteroid.AsteroidControlSystem;
import sem4.asteroid.AsteroidPlugin;
import sem4.common.ServiceLoader.SLFunctions;
import sem4.common.data.Entity;
import sem4.common.data.GameData;
import sem4.common.data.World;
import sem4.common.services.IEntityProcessingService;
import sem4.common.services.IGamePluginService;
import sem4.core.managers.GameInputProcessor;
import sem4.player.PlayerPlugin;
import sem4.player.PlayerControlSystem;
import sem4.enemy.EnemyPlugin;
import sem4.enemy.EnemyControlSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private final SLFunctions sl = new SLFunctions();

    private final GameData gameData = new GameData();
//    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
//    private List<IGamePluginService> entityPlugins = new ArrayList<>();
    private World world = new World();
    
    @Override
    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

//        IGamePluginService playerPlugin = new PlayerPlugin();
//        IEntityProcessingService playerProcess = new PlayerControlSystem();
//        entityPlugins.add(playerPlugin);
//        entityProcessors.add(playerProcess);
//        
//        IGamePluginService enemyPlugin = new EnemyPlugin();
//        IEntityProcessingService enemyProcess = new EnemyControlSystem();
//        entityPlugins.add(enemyPlugin);
//        entityProcessors.add(enemyProcess);
//        
//        IGamePluginService asteroidPlugin = new AsteroidPlugin();
//        IEntityProcessingService asteroidProcess = new AsteroidControlSystem();
//        entityPlugins.add(asteroidPlugin);
//        entityProcessors.add(asteroidProcess);
        
        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getEntityPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {

            sr.setColor(1, 1, 1, 1);

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    private ArrayList<IEntityProcessingService> getEntityProcessingServices() {
        return sl.loadServices(IEntityProcessingService.class);
    }

    private ArrayList<IGamePluginService> getEntityPluginServices() {
        return sl.loadServices(IGamePluginService.class);
    }
    
}