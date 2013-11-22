package game.model;

import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

public class AbstractModelObject {
    private String myName;
    
    protected AbstractModelObject(){
        myName = "";
    }
    
    public AbstractModelObject (SmartJsonObject definition) {
       try{
        myName = definition.getString(Constants.JSON_NAME);
       } catch (SmartJsonException e){
           e.printStackTrace();
       } 
    }
    
    public String getName() {
        return myName;
    }
    
    protected void setName(String name){
        myName = name;
    }
}
