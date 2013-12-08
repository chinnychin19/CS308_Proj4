package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.model.battle.Battle;

public class UserStateTransitionTextOptionState extends StateTransitionTextOptionState{

    public UserStateTransitionTextOptionState (AbstractBattleMode mode, String text, Battle battle) {
        super(mode, text, battle);
    }
    
    @Override
    protected void onInteract () {
        myMode.removeHitMarkers();
        myMode.setOptionState(myMode.getAMainOptionState());
        getBattle().handleMonsterDeaths();
    }
}
