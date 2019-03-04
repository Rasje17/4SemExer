/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author rasmus
 */
public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);

            int r = (int) (1 + Math.random() * 9);
            switch (r) {
                case 1:
                    movingPart.setLeft(true);
                    System.out.println(r);
                    break;
                case 2:
                    movingPart.setLeft(false);
                    System.out.println(r);
                    break;
                case 3:
                    movingPart.setRight(true);
                    System.out.println(r);
                    break;
                case 4:
                    movingPart.setRight(false);
                    System.out.println(r);
                    break;
                case 5:
                    movingPart.setUp(true);
                    System.out.println(r);
                    break;
                case 6:
                    movingPart.setUp(true);
                    System.out.println(r);
                    break;
                case 7:
//                        shoot();
//                        System.out.println(r); 
                    break;
                case 8:
//                        shoot();
                    System.out.println(r);
                    break;
                case 9:
//                        shoot();
                    System.out.println(r);
                    break;
                case 10:
//                        shoot();
                    System.out.println(r);
                    break;
            }

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
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
