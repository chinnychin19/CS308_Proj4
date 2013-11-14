package game.model;

import org.json.simple.JSONObject;

public class AbstractModelObject {
    private String myName;
    
    protected AbstractModelObject(){
        
    }
    
    public AbstractModelObject (JSONObject definition) {
        myName = definition.get("name").toString(); //TODO: constants
    }
    
    public String getName() {
        return myName;
    }
    
    protected void setName(String name){
        myName = name;
    }
}
