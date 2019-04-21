package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {
    
    private AsteroidType type;
    
    public Asteroid(AsteroidType type) {
        setShapeX(new float[8]);
        setShapeY(new float[8]);
        
        this.type = type;
    }
    
    public String getSize() {
        return type.getSize();
    }
    
}