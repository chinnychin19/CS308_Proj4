package game.model.statisticeffect;

import constants.Constants;
import game.model.Monster;
import util.jsonwrapper.SmartJsonObject;

public class HPStatisticEffect extends AbstractStatisticEffect {

	public HPStatisticEffect(SmartJsonObject object) {
		super(object);		
	}

	@Override
	public void apply(Monster monster) {
		if(getChange() == Constants.REVIVE_TO_FULL){
			monster.heal();
		}
		else{
			monster.changeHealth(getChange());
		}
	}
}
