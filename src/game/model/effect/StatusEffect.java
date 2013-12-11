package game.model.effect;

import constants.Constants;
import jsoncache.JSONCache;
import util.jsonwrapper.SmartJsonObject;
import game.model.GameModel;
import game.model.Monster;

public class StatusEffect extends AbstractStatusEffect {

    public StatusEffect(GameModel m, SmartJsonObject json) {
        super(m, json);
    }
    
    @Override
    public void apply (Monster m) {
        m.setStatus(myStatus);
    }

}
