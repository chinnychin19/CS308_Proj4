package game.controller.optionState;

import game.controller.AbstractBattleMode;
import game.model.FightingNPC;

public class UserWonTrainerBattleCompleteState extends AbstractBattleCompleteState {

    public UserWonTrainerBattleCompleteState (AbstractBattleMode mode) {
        super(mode, "You won :)");
    }

    @Override
    protected void onInteract () {
        ((FightingNPC) myMode.getBattle().getEnemyParty().getFighter()).setDefeated(true);
        //TODO: gifts
        //TODO: money
        //TODO: post dialogue
        myMode.getController().setWanderingMode();
    }
}
