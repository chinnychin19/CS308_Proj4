package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.model.battle.Battle;

public class StateTransitionTextOptionState extends TextOptionState {
    public StateTransitionTextOptionState (AbstractBattleMode mode, String text) {
        super(mode, text);
    }
    
    @Override
    protected void onInteract () {
        myMode.removeHitMarkers();
        myMode.setOptionState(myMode.getAMainOptionState());
        myMode.getBattle().doNextTurn();
    }
}
