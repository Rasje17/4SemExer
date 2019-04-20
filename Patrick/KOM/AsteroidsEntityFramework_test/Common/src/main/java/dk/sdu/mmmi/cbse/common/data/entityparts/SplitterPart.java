package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class SplitterPart implements EntityPart {

    private boolean shouldSplit = false;
    private boolean hasSplit = false;
    private String ID;

    
    public SplitterPart(String id) {
        this.ID = id;
    }
    
    
    public boolean ShouldSplit() {
        return shouldSplit;
    }

    public void setShouldSplit(boolean shouldSplit) {
        this.shouldSplit = shouldSplit;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean hasSplit() {
        return hasSplit;
    }

    public void setHasSplit(boolean hasSplit) {
        this.hasSplit = hasSplit;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
    }
    
}