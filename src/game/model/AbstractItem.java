package game.model;

import org.json.simple.JSONObject;

public abstract class AbstractItem extends AbstractModelObject {

    public AbstractItem (JSONObject definition) {
        super(definition);
    }
    
}
