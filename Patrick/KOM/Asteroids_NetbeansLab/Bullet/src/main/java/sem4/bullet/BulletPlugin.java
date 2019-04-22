package sem4.bullet;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sem4.common.data.Entity;
import sem4.common.data.GameData;
import sem4.common.data.World;
import sem4.common.data.entityparts.MovingPart;
import sem4.common.data.entityparts.PositionPart;
import sem4.common.services.IGamePluginService;


@ServiceProviders(value =  {
    @ServiceProvider(service = IGamePluginService.class)
})
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