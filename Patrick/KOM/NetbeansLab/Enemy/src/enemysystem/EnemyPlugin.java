package enemysystem;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.data.entityparts.MovingPart;
import common.data.entityparts.PositionPart;
import common.services.IGamePluginService;
import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    
    private Entity enemy;
    
    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }
    
    private Entity createEnemyShip(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        Random rand = new Random();
        float r = rand.nextFloat();
        float x = gameData.getDisplayWidth() * r;
        r = rand.nextFloat();
        float y = gameData.getDisplayHeight() * r;
        r = rand.nextFloat();
        float radians = 3.1415f*2*r;
        
        Entity enemyShip = new Enemy();
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
    
}