package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class ProjectilePart implements EntityPart {
    
    private String ID;

    
    public ProjectilePart(String id) {
        this.ID = id;
    }

    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getID() {
        return ID;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
    }
    
}