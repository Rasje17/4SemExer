/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.astoid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

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
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2 + gameData.getDisplayWidth() / 4;
        float y = gameData.getDisplayHeight() / 2 + gameData.getDisplayHeight() / 4;
        float radians = 3.1415f / 2;
        
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

