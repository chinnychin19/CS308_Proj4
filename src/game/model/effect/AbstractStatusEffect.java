package game.model.effect;

import jsoncache.JSONCache;
import constants.Constants;
import game.model.AbstractModelObject;
import game.model.GameModel;
import game.model.Monster;
import game.model.status.Status;
import util.Target;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;

public abstract class AbstractStatusEffect extends AbstractModelObject {
    protected Status myStatus;
    protected Target myTarget;

    protected AbstractStatusEffect(GameModel m) {
        super(m);
        myStatus = null;
        myTarget = Target.SELF;
    }
    
    public AbstractStatusEffect(GameModel m, SmartJsonObject json) {
        super(m, json);
        JSONCache cache = getModel().getDefinitionCache();
        try {
            SmartJsonObject statusDefinition = cache.getInstance(Constants.JSON_STATUS, getName());
            myStatus = new Status(getModel(), statusDefinition);
            String targetString = json.getString(Constants.JSON_TARGET);
            myTarget = Target.getTarget(targetString);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return getName();
    }
    
    public boolean targetIsSelf() {
        return myTarget == Target.SELF;
    }
    
    public abstract void apply(Monster m);
}
