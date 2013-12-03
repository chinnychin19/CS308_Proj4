package game.model;

import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;

/**
 * Key items are used to progress the Player class throughout the game.  They are used to unlock different parts of the map
 * or to acquire different stats, powerups, or items
 * @author Chinmay
 *
 */
public class KeyItem extends AbstractModelObject {
    // Key items only have names.
    public KeyItem (GameModel model, SmartJsonObject definition) {
        super(model, definition);
    }
    
    public KeyItem (GameModel model, String name){
        super(model);
        setName(name);
    }
    
    @Override
    public int hashCode () {
        return getName().hashCode();
    }
    
    @Override
    public boolean equals(Object o){
        if(o==null || !(o instanceof KeyItem))
            return false;
        return ((KeyItem)o).getName().equals(getName());
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
