package game.model;

import game.controller.GameController;
import game.controller.Input;

import java.awt.Image;
import location.Loc;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

/**
 * Abstract class for viewable objects to implement.  Contains the functionality associated with whether or not an object can 
 * step on this viewable object -- Default is false.
 * @author tylernisonoff
 *
 */
public abstract class AbstractViewableObject extends AbstractViewable {

    public AbstractViewableObject (GameModel model,
                                   World world,
                                   SmartJsonObject definition,
                                   SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld);
    }
    
    @Override
    public boolean canStepOn() {
        return false;
    }
    
    @Override
    public void doFrame(World w, Input input) {
    	if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
    }
    
    protected abstract void onInteract();
    
    protected abstract void onBack();

}
