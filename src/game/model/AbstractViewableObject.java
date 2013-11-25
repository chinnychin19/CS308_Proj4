package game.model;

import game.controller.GameController;
import java.awt.Image;
import location.Loc;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


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
}
