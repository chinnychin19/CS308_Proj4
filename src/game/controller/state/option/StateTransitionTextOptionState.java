package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.model.battle.Battle;

public class StateTransitionTextOptionState extends TextOptionState {
    private Battle myBattle;
    public StateTransitionTextOptionState (AbstractBattleMode mode, String text, Battle battle) {
        super(mode, text);
        myBattle = battle;
    }
    
    @Override
    protected void onInteract () {
        myMode.removeHitMarkers();
        myMode.setOptionState(myMode.getAMainOptionState());
        myBattle.doNextTurn();
    }
    
    protected Battle getBattle(){
        return myBattle;
    }
}
