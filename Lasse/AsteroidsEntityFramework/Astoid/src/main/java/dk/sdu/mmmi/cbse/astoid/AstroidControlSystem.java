/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.astoid;

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
public class AstroidControlSystem implements IEntityProcessingService {

   @Override
    public void process(GameData gameData, World world) {

        for (Entity astrod : world.getEntities(Astroid.class)) {
            PositionPart positionPart = astrod.getPart(PositionPart.class);
            MovingPart movingPart = astrod.getPart(MovingPart.class);
            
            movingPart.setUp(true);
            movingPart.setRight(true);
//             int r = (int) (1 + Math.random() * 9);
//                switch (r) {
//                    case 1:
//                         movingPart.setLeft(true);
//                        System.out.println(r);
//                        break;
//                    case 2:
//                         movingPart.setLeft(false);
//                        System.out.println(r);
//                        break;
//                    case 3:
//                        movingPart.setRight(true);
//                        System.out.println(r);
//                        break;
//                    case 4:
//                        movingPart.setRight(false);
//                        System.out.println(r);
//                        break;
//                    case 5:
//                        movingPart.setUp(true);
//                        System.out.println(r);
//                        break;
//                    case 6:
//                        movingPart.setUp(true);
//                        System.out.println(r); 
//                        break;
//                    case 7:
////                        shoot();
////                        System.out.println(r); 
//                        break;
//                    case 8:
////                        shoot();
//                        System.out.println(r);
//                        break;
//                    case 9:
////                        shoot();
//                        System.out.println(r);  
//                        break;
//                    case 10:
////                        shoot();
//                        System.out.println(r); 
//                        break;
//                }

            
            
            movingPart.process(gameData, astrod);
            positionPart.process(gameData, astrod);

            updateShape(astrod);
        }
    }

    private void updateShape(Entity entity) {
        entity.setShapeX(new float[8]);
        entity.setShapeY(new float[8]);
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

        shapex[4] = (float) (x + Math.cos(radians +3.1415f) * 24);
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

