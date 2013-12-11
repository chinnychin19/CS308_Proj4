package game.model;

import game.controller.AbstractBattleMode;
import game.controller.AbstractMode;
import game.controller.MainMenuMode;
import game.model.statisticeffect.AbstractStatisticEffect;
import game.model.statisticeffect.StatisticEffectFactory;

import org.json.simple.JSONObject;

import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;

/**
 * @author rtoussaint
 */

public abstract class Item extends AbstractItem {

        private String myConsciousness;
        private AbstractStatisticEffect myEffect;

    public Item (GameModel model, SmartJsonObject definition) {
        super(model, definition);
        loadFromWorld(definition);
    }
    
    public void loadFromWorld (SmartJsonObject definition) {
        try {
                myConsciousness = definition.getString(Constants.RECIPIENT_ORIGINAL_STATUS);
                myEffect = new StatisticEffectFactory().produceStatisticEffect(definition);
                		
                		//new AbstractStatisticEffect(definition.getSmartJsonObject("statisticEffect"));
        }
        catch (SmartJsonException e) {

        }
    }
    
    private void canUse(Monster m){
    	AbstractMode currentMode = getModel().getController().getMode();
    	if(currentMode instanceof MainMenuMode || currentMode instanceof AbstractBattleMode){
    		myEffect.apply(m);
    	}
    }
    
  
}