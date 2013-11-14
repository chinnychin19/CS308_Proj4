package game.model;

import org.json.simple.JSONObject;


public class KeyItem extends AbstractModelObject {
    // Key items only have names.
    public KeyItem (JSONObject definition) {
        super(definition);
    }
    
    public KeyItem (String name){
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
