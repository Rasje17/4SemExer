package dk.sdu.mmmi.cbse.asteroid;

import static dk.sdu.mmmi.cbse.asteroid.AsteroidType.LARGE;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SplitterPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;
import java.util.UUID;

public class AsteroidPlugin implements IGamePluginService {
    
    private Entity asteroid;

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }
    
    private Entity createAsteroid(GameData gameData) {
        float deacceleration = 0;   // constant speed
        float acceleration = 200;   // start at max speed
        float maxSpeed = 200;
        float rotationSpeed = 0.07f;
        Random rand = new Random();
        float r = rand.nextFloat();
        float x = gameData.getDisplayWidth() * r;
        r = rand.nextFloat();
        float y = gameData.getDisplayHeight() * r;
        r = rand.nextFloat();
        float radians = 2*3.1415f*r;
        
        float[] color = new float[4];
        color[0] = 1.0f;
        color[1] = 1.0f;
        color[2] = 1.0f;
        color[3] = 1.0f;

        Entity asteroid = new Asteroid(LARGE);
        MovingPart movingPart = new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed);
        asteroid.add(movingPart);
        asteroid.add(new PositionPart(x, y, radians));
        asteroid.add(new LifePart(1));

        UUID uuid = UUID.randomUUID();
        asteroid.add(new SplitterPart(uuid.toString()));
        
        asteroid.setColor(color);
        
        // since an asteroid will move constantly, and not switch directions all the time
        movingPart.setUp(true);
        int v = rand.nextInt(100)+1;
            if (v > 50) {
                movingPart.setLeft(true);
            }
            else {
                movingPart.setRight(true);
            }
        
        return (Asteroid) asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity ast : world.getEntities(Asteroid.class)) {
            world.removeEntity(ast);
        }
    }
}