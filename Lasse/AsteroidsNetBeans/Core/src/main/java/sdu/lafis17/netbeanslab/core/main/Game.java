package sdu.lafis17.netbeanslab.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sdu.lafis17.netbeanslab.common.data.Entity;
import sdu.lafis17.netbeanslab.common.data.GameData;
import sdu.lafis17.netbeanslab.common.data.World;
import sdu.lafis17.netbeanslab.common.services.IEntityProcessingService;
import sdu.lafis17.netbeanslab.common.services.IGamePluginService;
import sdu.lafis17.netbeanslab.core.managers.GameInputProcessor;


public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private final GameData gameData = new GameData();
    private World world = new World();
    private List<IGamePluginService> gamePlugins = new CopyOnWriteArrayList<>();
    private List<IEntityProcessingService> processors = new CopyOnWriteArrayList<>();
    ApplicationContext context = new ClassPathXmlApplicationContext("playerBeanConfig.xml");

    @Override
    public void create() {
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        IGamePluginService playerPlugin =(IGamePluginService) context.getBean("playerPlugin");
        System.out.println("Player plugin loaded");
        IEntityProcessingService playerControlSystem = (IEntityProcessingService) context.getBean("playerControlSystem");
        
        playerPlugin.start(gameData, world);
        gamePlugins.add(playerPlugin);
        processors.add(playerControlSystem);
//
//        for (IGamePluginService plugin : gamePlugins) {
//            plugin.start(gameData, world);
//        }

    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();

        update();
        draw();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : processors) {
            entityProcessorService.process(gameData, world);
        }

//        // Post Update
//        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
//            postEntityProcessorService.process(gameData, world);
//        }
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

}
