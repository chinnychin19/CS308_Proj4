package game.model.statisticeffect;

import util.jsonwrapper.SmartJsonObject;
import game.model.Monster;

/**
 * 
 * @author rtoussaint
 *
 */

public class DefenseStatisticEffect extends AbstractStatisticEffect {

	public DefenseStatisticEffect(SmartJsonObject object) {
		super(object);		
	}
	
	@Override
	public void apply(Monster monster) {
		monster.changeDefense(getChange());
	}

}
