package game.controller.optionState;

import game.controller.AbstractBattleMode;

public class UserWonWildBattleCompleteState extends AbstractWildBattleCompleteState {

    public UserWonWildBattleCompleteState (AbstractBattleMode mode) {
        super(mode, "YOU WON :)");
    }

    @Override
    protected void onInteract () {
        super.onInteract();
    }
}
