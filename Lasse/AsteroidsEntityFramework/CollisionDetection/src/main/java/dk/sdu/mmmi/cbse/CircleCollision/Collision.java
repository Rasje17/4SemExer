/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.CircleCollision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.events.Event;

/**
 *
 * @author rasmus
 */
public class Collision extends Event {
    
    public Collision(Entity source) {
        super(source);
    }
    
}
