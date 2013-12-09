package game.controller.state.mainmenu;

import game.controller.MainMenuMode;
import game.model.Item;
import game.model.Monster;
/**
 * Select the monster that you want from the ItemMenuState
 * @author Team Rocket
 *
 */

public class SelectMonsterForItemMenuState extends MonsterSelecterState{
    private Item myItem;
    public SelectMonsterForItemMenuState (MainMenuMode mode, Item item) {
        super(mode);
        myItem = item;
    }

 @Override
    protected void onBack () {
     getMode().setState(new ItemMenuState(getMode()));
    }

    @Override
    protected void onInteract () {
        Monster selectedMonster = getMonsterList().get(getSelected());
        myItem.applyEffect(selectedMonster);
        getMode().setState(new MainMenuState(getMode()));
    }
}
