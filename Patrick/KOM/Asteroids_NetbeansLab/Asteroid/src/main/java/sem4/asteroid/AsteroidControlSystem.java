package sem4.asteroid;

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
public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);

            positionPart.process(gameData, asteroid);
            movingPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = new float[8];
        float[] shapey = new float[8];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 24);
        shapey[0] = (float) (y + Math.sin(radians) * 24);

        shapex[1] = (float) (x + Math.cos(radians - 3.1415f / 4) * 24);
        shapey[1] = (float) (y + Math.sin(radians - 3.1415f / 4) * 24);

        shapex[2] = (float) (x + Math.cos(radians - 3.1415f / 2) * 24);
        shapey[2] = (float) (y + Math.sin(radians - 3.1415f / 2) * 24);

        shapex[3] = (float) (x + Math.cos(radians - 3 * 3.1415f / 4) * 24);
        shapey[3] = (float) (y + Math.sin(radians - 3 * 3.1415f / 4) * 24);

        shapex[4] = (float) (x + Math.cos(radians + 3.1415f) * 24);
        shapey[4] = (float) (y + Math.sin(radians + 3.1415f) * 24);

        shapex[5] = (float) (x + Math.cos(radians + 3 * 3.1415f / 4) * 24);
        shapey[5] = (float) (y + Math.sin(radians + 3 * 3.1415f / 4) * 24);

        shapex[6] = (float) (x + Math.cos(radians + 3.1415f / 2) * 24);
        shapey[6] = (float) (y + Math.sin(radians + 3.1415f / 2) * 24);

        shapex[7] = (float) (x + Math.cos(radians + 3.1415f / 4) * 24);
        shapey[7] = (float) (y + Math.sin(radians + 3.1415f / 4) * 24);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
