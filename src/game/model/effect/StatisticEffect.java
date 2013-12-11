package game.model.effect;

import game.model.AbstractModelObject;
import game.model.GameModel;
import game.model.Monster;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

public class StatisticEffect extends AbstractModelObject{
    private int myChange;

    public StatisticEffect (GameModel model, SmartJsonObject definition) {
        super(model, definition);    
    }
    @Override
    public void readDefinition (SmartJsonObject definition) throws SmartJsonException {
        super.readDefinition(definition);
        myChange = definition.getInt(Constants.CHANGE);

    }
    
    public void apply(Monster m) {
        m.changeStat(getName(), myChange);
    }
}
