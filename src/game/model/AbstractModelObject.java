package game.model;

import org.json.simple.JSONObject;
import constants.Constants;

public class AbstractModelObject {
    private String myName;
    
    protected AbstractModelObject(){
        
    }
    
    public AbstractModelObject (JSONObject definition) {
        myName = definition.get(Constants.JSON_NAME).toString();
    }
    
    public String getName() {
        return myName;
    }
    
    protected void setName(String name){
        myName = name;
    }
}
