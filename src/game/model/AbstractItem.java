package game.model;

import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;

public abstract class AbstractItem extends AbstractModelObject {

    public AbstractItem (SmartJsonObject definition) {
        super(definition);
    }
    
}
