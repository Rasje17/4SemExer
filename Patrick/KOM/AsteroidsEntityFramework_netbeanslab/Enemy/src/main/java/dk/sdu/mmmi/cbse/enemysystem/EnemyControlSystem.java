package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
import dk.sdu.mmmi.cbse.bullet.BulletPlugin;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.ArrayList;
import java.util.Random;

public class EnemyControlSystem implements IEntityProcessingService {
    
    ArrayList<IEntityProcessingService> bullets = new ArrayList<>();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);

            Random rand = new Random();
            int r = rand.nextInt(10);
            System.out.println(r);
            switch (r) {
                case 0:
                    movingPart.setLeft(true);
                    break;
                case 1:
                    movingPart.setLeft(false);
                    break;
                case 2:
                    movingPart.setRight(true);
                    break;
                case 3:
                    movingPart.setRight(false);
                    break;
                case 4:
                    movingPart.setUp(false);
                    break;
                case 5:
                    movingPart.setUp(true);
                    break;
                case 6:
                    movingPart.setUp(true);
                    break;
                case 7:
                    movingPart.setUp(true);
                    break;
                case 8:
                    shoot(gameData, world, enemy);
                    break;
                case 9:
                    shoot(gameData, world, enemy);
                    break;
            }
            
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }
    
    private void shoot(GameData gameData, World world, Entity enemy) {
        float x = enemy.getShapeX()[0];
        float y = enemy.getShapeY()[0];
        PositionPart pos = enemy.getPart(PositionPart.class);
        float radians = pos.getRadians();
        
        IGamePluginService b = new BulletPlugin(x, y, radians);
        IEntityProcessingService bullet = new BulletControlSystem();
        
        b.start(gameData, world);
        bullets.add(bullet);
    }
    
    public ArrayList<IEntityProcessingService> getBullets() {
        return this.bullets;
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}