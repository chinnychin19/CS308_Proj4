package game.model;

import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


public class AbstractModelObject {
    private String myName;
    private GameModel myModel;

    protected AbstractModelObject (GameModel model) {
        myName = "";
        myModel = model;
    }

    public AbstractModelObject (GameModel model, SmartJsonObject definition) {
        myModel = model;
        try {
            myName = definition.getString(Constants.JSON_NAME);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    public GameModel getModel(){
        return myModel;
    }

    public String getName () {
        return myName;
    }

    protected void setName (String name) {
        myName = name;
    }
}
