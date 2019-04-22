package dk.sdu.mmmi.cbse.bullet;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.data.entityparts.MovingPart;
import common.data.entityparts.PositionPart;
import common.services.IEntityProcessingService;

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
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
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