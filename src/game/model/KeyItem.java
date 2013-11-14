package game.model;

import org.json.simple.JSONObject;

public class KeyItem extends AbstractModelObject {
    //Key items only have names.
    public KeyItem (JSONObject definition) {
        super(definition);
    }

}
