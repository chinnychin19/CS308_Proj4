package game.model.statisticeffect;

import game.model.Monster;
import util.jsonwrapper.SmartJsonObject;

public class HPStatisticEffect extends AbstractStatisticEffect {

	public HPStatisticEffect(SmartJsonObject object) {
		super(object);		
	}

	@Override
	public void apply(Monster monster) {
		monster.changeHealth(getChange());
	}
}
