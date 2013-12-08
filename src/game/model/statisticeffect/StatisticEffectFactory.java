package game.model.statisticeffect;

import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;

public class StatisticEffectFactory {
	
	public AbstractStatisticEffect produceStatisticEffect(SmartJsonObject json){
		try {
			String myName = json.getString(Constants.STAT_NAME);
			
			if(myName.equals("hp")){
				return new HPStatisticEffect(json);
			} 
			if(myName.equals("attack")){
				return new AttackStatisticEffect(json);
			}
			else{
				return null;
			}
		} catch (NoStringValueJsonException e) {
			e.printStackTrace();
			return null;
		}
	}
}
