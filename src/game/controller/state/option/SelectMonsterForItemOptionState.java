package game.controller.state.option;

import java.util.List;

import constants.Constants;

import game.controller.AbstractBattleMode;
import game.model.Item;
import game.model.Monster;

public class SelectMonsterForItemOptionState extends
		PartyOptionState {

	private Item myItem;
	public SelectMonsterForItemOptionState(AbstractBattleMode mode, Item item) {
		super(mode);
		myItem = item;
	}

	@Override
    public void onInteract(){
        List<Monster> monsters = getMonsters();
        Monster selectedMonster = monsters.get(mySelected);
        myItem.applyEffect(selectedMonster);
        myMode.setOptionState(new StateTransitionTextOptionState(myMode, String.format(Constants.TEXT_YOU_USED_ON_BLANK, myItem.getName(),selectedMonster.getName())));
	}
	
	@Override
	public void onBack(){
		myMode.setOptionState(new ItemOptionState(myMode));
	}

}
