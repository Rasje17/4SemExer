/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem4.common.data.entityparts;

import sem4.common.data.Entity;
import sem4.common.data.GameData;

/**
 *
 * @author Someone
 */
public class LifePart implements EntityPart {

    private int life;
    private boolean isHit = false;
    private float expiration;

    public LifePart(int life, float expiration) {
        this.life = life;
        this.expiration = expiration;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public float getExpiration() {
        return expiration;
    }

    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }  
    
    public void reduceExpiration(float delta){
        this.expiration -= delta;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        
    }
}
