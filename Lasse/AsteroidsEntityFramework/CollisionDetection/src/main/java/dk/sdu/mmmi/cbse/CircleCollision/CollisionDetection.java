/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.CircleCollision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

/**
 *
 * @author rasmus
 */
public class CollisionDetection implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            for (Entity k : world.getEntities()) {
                if (e.getID() == k.getID()) {
                    continue;
                } else {
                    
                    //getting the x and y position off each element
                    PositionPart positionPart1 = e.getPart(PositionPart.class);
                    double x1 = positionPart1.getX();
                    double y1 = positionPart1.getY();
                    PositionPart positionPart2 = k.getPart(PositionPart.class);
                    double x2 = positionPart2.getX();
                    double y2 = positionPart2.getY();
                    
                    //statement test if pytagoras c² between center points are shorter than the radus of each object combined
                    if (Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2))<(e.getRadius()+k.getRadius())){
                        
                    }
                }
            }

        }
    }

}
