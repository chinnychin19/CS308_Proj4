package game.model;

import org.json.simple.JSONObject;

public class AbstractModelObject {
    private String myName;
    public AbstractModelObject (JSONObject definition) {
        myName = definition.get("name").toString(); //TODO: constants
    }
    
    public String getName() {
        return myName;
    }
}
