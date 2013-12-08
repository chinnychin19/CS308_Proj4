package game.model.statisticeffect;

import util.jsonwrapper.SmartJsonObject;
import game.model.Monster;

/**
 * 
 * @author rtoussaint
 *
 */

public class AttackStatisticEffect extends AbstractStatisticEffect {

	public AttackStatisticEffect(SmartJsonObject object) {
		super(object);		
	}
	
	@Override
	public void apply(Monster monster) {
		monster.changeAttack(getChange());

	}

}
