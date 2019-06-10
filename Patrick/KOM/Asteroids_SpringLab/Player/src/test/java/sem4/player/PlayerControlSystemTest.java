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
 * @author pvies
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
        System.out.println("Testing process");
        GameData gd = new GameData();
        gd.setDisplayWidth(100);
        gd.setDisplayHeight(100);
        gd.setDelta(10);
        World wd = new World();
        PlayerPlugin player = new PlayerPlugin();
        player.start(gd, wd);
        
        gd.getKeys().setKey(GameKeys.UP, true);
        
        
        float x1 = 0;
        float x2 = 0;
        float y1 = 0;
        float y2 = 0;
        
        for (Entity entity : wd.getEntities(Player.class)) {
            PositionPart pospart = entity.getPart(PositionPart.class);
            x1 = pospart.getX();
            y1 = pospart.getY();
        }
        
        PlayerControlSystem instance = new PlayerControlSystem();
        instance.process(gd, wd);
        
        for (Entity entity : wd.getEntities(Player.class)) {
            PositionPart pospart = entity.getPart(PositionPart.class);
            x2 = pospart.getX();
            y2 = pospart.getY();
        }
        
        boolean test = false;
        if (x1 != x2 || y1 != y2) {
            test = true;
        }
        
        assertEquals(true, test);
    }
    
}
