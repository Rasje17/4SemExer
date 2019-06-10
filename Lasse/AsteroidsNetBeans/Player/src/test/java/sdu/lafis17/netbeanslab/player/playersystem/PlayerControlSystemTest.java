/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.lafis17.netbeanslab.player.playersystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sdu.lafis17.netbeanslab.common.data.Entity;
import sdu.lafis17.netbeanslab.common.data.GameData;
import sdu.lafis17.netbeanslab.common.data.GameKeys;
import sdu.lafis17.netbeanslab.common.data.World;
import sdu.lafis17.netbeanslab.common.data.entityparts.MovingPart;
import sdu.lafis17.netbeanslab.common.data.entityparts.PositionPart;
import sdu.lafis17.netbeanslab.core.managers.GameInputProcessor;

/**
 *
 * @author Pottemuld
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
        
        
        World world = new World();
        PlayerPlugin plugin = new PlayerPlugin();
        plugin.start(gameData, world);
        PlayerControlSystem instance = new PlayerControlSystem();
        gameData.getKeys().setKey(GameKeys.UP, true);
        gameData.setDelta(1);
        
        float startX = 0;
        float startY = 0;
        float endX = 0;
        float endY = 0;
        
        
        for (Entity player : world.getEntities(Player.class)) {
            PositionPart start = player.getPart(PositionPart.class);
            startX = start.getX();
            startY = start.getY();
            System.out.println("StartX: " + startX + " startY: " + startY);
            
        }
        instance.process(gameData, world);
        
        for (Entity player : world.getEntities(Player.class)) {
            PositionPart end = player.getPart(PositionPart.class);
            endX = end.getX();
            endY = end.getY();
            System.out.println("EndX " + endX + " startY: " + startY);
        }
        
        boolean moved = false;
        if(startX != endX) {
            moved = true;
        } 
        if(startY != endY) {
            moved = true;
        }
        
        assertTrue(moved);
    }
    
}
