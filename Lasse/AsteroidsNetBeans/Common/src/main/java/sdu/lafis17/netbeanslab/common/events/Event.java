package sdu.lafis17.netbeanslab.common.events;


import java.io.Serializable;
import sdu.lafis17.netbeanslab.common.data.Entity;

/**
 *
 * @author Mads
 */
public class Event implements Serializable{
    private final Entity source;

    public Event(Entity source) {
        this.source = source;
    }

    public Entity getSource() {
        return source;
    }
}
