package dk.sdu.mmmi.cbse.asteroid;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.data.entityparts.MovingPart;
import common.data.entityparts.PositionPart;
import common.services.IEntityProcessingService;

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
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
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