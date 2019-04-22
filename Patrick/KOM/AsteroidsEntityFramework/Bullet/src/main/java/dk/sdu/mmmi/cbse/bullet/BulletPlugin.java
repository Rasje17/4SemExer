package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    private Entity bullet;
    
    private float x, y, radians;
    
    public BulletPlugin() {
        
    }
    
    // bullets are made by the ships that shoot them, and thus need to be told where their starting coordinates are
    public BulletPlugin(float x, float y, float heading) {
        this.x = x;
        this.y = y;
        this.radians = heading;
    }
    
    @Override
    public void start(GameData gameData, World world) {
        bullet = createBullet();
        world.addEntity(bullet);
    }

    private Entity createBullet() {
        float maxSpeed = 500;
        float acceleration = 1000;  // bullets will start at max speed
        float deacceleration = 10;
        float rotationSpeed = 0;

        Entity bullet = new Bullet();
        
        MovingPart movingPart = new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed);
        bullet.add(movingPart);
        bullet.add(new PositionPart(x, y, radians));
        
        // bullets will always move only forwards
        movingPart.setUp(true);
        
        return bullet;
    }
    
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(bullet);
    }
}