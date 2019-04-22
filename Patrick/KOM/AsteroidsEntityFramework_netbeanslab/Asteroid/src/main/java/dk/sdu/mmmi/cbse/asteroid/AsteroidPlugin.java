package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    
    private Entity asteroid;

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }
    
    private Entity createAsteroid(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 100;
        float rotationSpeed = 0.07f;
        Random rand = new Random();
        float r = rand.nextFloat();
        float x = gameData.getDisplayWidth() * r;
        r = rand.nextFloat();
        float y = gameData.getDisplayHeight() * r;
        r = rand.nextFloat();
        float radians = 2*3.1415f*r;
        
        Entity asteroid = new Asteroid();
        MovingPart movingPart = new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed);
        asteroid.add(movingPart);
        asteroid.add(new PositionPart(x, y, radians));
        
        // since an asteroid will move constantly, and not switch directions all the time
        movingPart.setUp(true);
        int v = rand.nextInt(100)+1;
            if (v > 50) {
                movingPart.setLeft(true);
            }
            else {
                movingPart.setRight(true);
            }
        
        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }
}