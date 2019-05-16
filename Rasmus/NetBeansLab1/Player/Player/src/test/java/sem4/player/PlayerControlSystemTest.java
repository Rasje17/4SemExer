/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem4.player;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sem4.common.data.Entity;
import sem4.common.data.GameData;
import sem4.common.data.GameKeys;
import sem4.common.data.World;
import sem4.common.data.entityparts.PositionPart;

/**
 *
 * @author Rasmus
 */
public class PlayerControlSystemTest {

    public PlayerControlSystemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of process method, of class PlayerControlSystem.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        GameData gameData = new GameData();
        gameData.setDisplayHeight(600);
        gameData.setDisplayWidth(600);
        gameData.getKeys().setKey(GameKeys.UP, true);
        gameData.setDelta(1);

        World world = new World();
        PlayerPlugin playPlug = new PlayerPlugin();
        playPlug.start(gameData, world);

        float startX = 0;
        float startY = 0;
        float endY = 0;
        float endX = 0;

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart startpos = player.getPart(PositionPart.class);
            startX = startpos.getX();
            startY = startpos.getY();
//            System.out.println("x11= " + startX + "y11= " + startY);
        }
        PlayerControlSystem instance = new PlayerControlSystem();
        instance.process(gameData, world);

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart endpos = player.getPart(PositionPart.class);
            endX = endpos.getX();
            endY = endpos.getY();
//            System.out.println("x12= " + endX + "y12= " + endY);
        }
//        System.out.println("x21= " + startX + "y21= " + startY);
//        System.out.println("x22= " + endX + "y22= " + endY);

        boolean moved = false;
        if (startX != endX || startY != endY) {
            moved = true;
        }
        assertTrue(moved);
    }

}
