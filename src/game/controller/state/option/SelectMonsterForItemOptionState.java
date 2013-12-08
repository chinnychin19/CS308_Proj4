package game.controller.state.option;

import java.util.List;

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
        myMode.setOptionState(new StateTransitionTextOptionState(myMode, String.format("You used %s on %s", myItem.getName(),selectedMonster.getName())));
        //TODO: String constant
	}
	
	@Override
	public void onBack(){
		myMode.setOptionState(new ItemOptionState(myMode));
	}

}
