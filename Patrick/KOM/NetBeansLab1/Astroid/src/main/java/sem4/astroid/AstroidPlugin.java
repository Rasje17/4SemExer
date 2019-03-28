/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem4.astroid;

import sem4.common.data.Entity;
import sem4.common.data.GameData;
import sem4.common.data.World;
import sem4.common.data.entityparts.MovingPart;
import sem4.common.data.entityparts.PositionPart;
import sem4.common.services.IGamePluginService;

/**
 *
 * @author rasmus
 */
public class AstroidPlugin implements IGamePluginService{

  private Entity astroid;

    public AstroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        
        // Add entities to the world
        astroid = createAstroidShip(gameData);
        world.addEntity(astroid);
    }

    private Entity createAstroidShip(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 75;
        float maxSpeed = 60;
        float rotationSpeed = 0.1f;
        float x = gameData.getDisplayWidth() / 2 + gameData.getDisplayWidth() / 4;
        float y = gameData.getDisplayHeight() / 2 + gameData.getDisplayHeight() / 4;
        float radians =  (float) (Math.random() * (3.1415f * 2));
//        float radians = 3.1415f / 2;
        
        Entity astroidShip = new Astroid();
        astroidShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        astroidShip.add(new PositionPart(x, y, radians));
        
        return astroidShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(astroid);
    }

}

