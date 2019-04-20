package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ProjectilePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {

    private Entity thisBullet;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities()) {
            if (entity.getPart(ShootingPart.class) != null) {
                ShootingPart shootingPart = entity.getPart(ShootingPart.class);

                if (shootingPart.isShooting()) {
                    PositionPart positionPart = entity.getPart(PositionPart.class);
                    thisBullet = createBullet(positionPart.getX() + entity.getRadius(), positionPart.getY() + entity.getRadius(), positionPart.getRadians(), shootingPart.getID());
                    shootingPart.setIsShooting(false);
                    world.addEntity(thisBullet);
                }
            }
        }

        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            timerPart.reduceExpiration(gameData.getDelta());
            if (timerPart.getExpiration() < 0) {
                world.removeEntity(thisBullet);
            }

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);
            timerPart.process(gameData, bullet);
            lifePart.process(gameData, bullet);

            updateShape(bullet);
        }
    }
    
    private Entity createBullet(float x, float y, float radians, String uuid) {
        float maxSpeed = 400;
        float acceleration = 1000;  // bullets will start at max speed
        float deacceleration = 0;
        float rotationSpeed = 0;    // bullets cannot change direction

        Entity bullet = new Bullet();
        
        MovingPart movingPart = new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed);
        bullet.add(movingPart);
        bullet.add(new PositionPart(x, y, radians));
        bullet.add(new LifePart(1));
        bullet.add(new TimerPart(3));
        
        bullet.add(new ProjectilePart(uuid));
        bullet.setRadius(2);
        
        float[] color = new float[4];
        color[0] = 0.2f;
        color[1] = 0.5f;
        color[2] = 0.7f;
        color[3] = 1.0f;
        
        bullet.setColor(color);
        
        // bullets will always move only forwards
        movingPart.setUp(true);
        
        return bullet;
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * entity.getRadius());
        shapey[0] = (float) (y + Math.sin(radians) * entity.getRadius());

        shapex[1] = (float) (x + Math.cos(radians - 3.1415f) * entity.getRadius());
        shapey[1] = (float) (y + Math.sin(radians - 3.1145f) * entity.getRadius());

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
