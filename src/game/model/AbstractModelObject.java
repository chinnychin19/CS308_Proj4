package game.model;

import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

/**
 * Creates a new instance of a Model Object which has a name and a model attached to it.
 * @author tylernisonoff
 *
 */

public class AbstractModelObject {
    private String myName;
    private GameModel myModel;

    protected AbstractModelObject (GameModel model) {
        myName = Constants.EMPTY_STRING;
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
    
    /**
     * Gets the model that is attached to this object
     * @return the model
     */
    public GameModel getModel(){
        return myModel;
    }
    /**
     * Gets the name of this object
     * @return the name
     */
    public String getName () {
        return myName;
    }

    /**
     * Set the name for this object
     * @param name the name of the object
     */
    protected void setName (String name) {
        myName = name;
    }
}
