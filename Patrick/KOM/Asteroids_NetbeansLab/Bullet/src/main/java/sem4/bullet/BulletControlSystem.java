package sem4.bullet;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import sem4.common.data.Entity;
import sem4.common.data.GameData;
import sem4.common.data.World;
import sem4.common.data.entityparts.MovingPart;
import sem4.common.data.entityparts.PositionPart;
import sem4.common.services.IEntityProcessingService;


@ServiceProviders(value =  {
    @ServiceProvider(service = IEntityProcessingService.class)
})
public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            
            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);
            
            updateShape(bullet);
        }
    }
    
    private void updateShape(Entity entity) {
        float[] shapex = new float[2];
        float[] shapey = new float[2];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 3);
        shapey[0] = (float) (y + Math.sin(radians) * 3);

        shapex[1] = (float) (x + Math.cos(radians - 3.1415f) * 3);
        shapey[1] = (float) (y + Math.sin(radians - 3.1145f) * 3);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}