package game.model.status;

import java.util.ArrayList;
import java.util.Collection;
import org.json.simple.JSONObject;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import game.model.AbstractModelObject;
import game.model.GameModel;
import game.model.Monster;
import game.model.effect.StatisticEffect;

public class Status extends AbstractModelObject {
    private Collection<StatisticEffect> myStatisticEffects;
    
    protected Status (GameModel model) {
        super(model);
        myStatisticEffects = new ArrayList<StatisticEffect>();
    }
    
    public Status (GameModel model, SmartJsonObject definition) {
        super(model, definition);
        
    }
    
    @Override
    public void readDefinition (SmartJsonObject definition) throws SmartJsonException {
        myStatisticEffects = new ArrayList<StatisticEffect>();
        for (Object o : definition.getJSONArray(Constants.STATISTIC_EFFECTS)) {
            SmartJsonObject effectJSON = new SmartJsonObject((JSONObject) o);
            myStatisticEffects.add(new StatisticEffect(effectJSON));
        }
    }
    
    public void doStatus(Monster m) {
        for (StatisticEffect effect : myStatisticEffects) {
            effect.apply(m);
        }
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
