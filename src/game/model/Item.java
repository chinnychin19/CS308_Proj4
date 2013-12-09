package game.model;

import game.controller.AbstractBattleMode;
import game.controller.AbstractMode;
import game.controller.MainMenuMode;
import game.controller.state.Listable;
import game.model.statisticeffect.StatisticEffect;
import org.json.simple.JSONObject;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;

/**
 * 
 * @author rtoussaint
 *
 */


public class Item extends AbstractModelObject {

        private String myConsciousness;
        private StatisticEffect myEffect;

    public Item (GameModel model, SmartJsonObject definition) {
        super(model, definition);
    }
    
    @Override
    public void readDefinition (SmartJsonObject definition) throws SmartJsonException {
    	super.readDefinition(definition);
        myConsciousness = definition.getString(Constants.CONSCIOUSNESS);
        myEffect = new StatisticEffect(definition.getSmartJsonObject(Constants.TEXT_STAT_EFFECT));
    }
    
    public void applyEffect(Monster m){
		myEffect.apply(m);
    }
    
  
}