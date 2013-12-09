package game.controller.optionState;

import constants.Constants;
import game.controller.AbstractBattleMode;

public class UserWonWildBattleCompleteState extends AbstractWildBattleCompleteState {

    public UserWonWildBattleCompleteState (AbstractBattleMode mode) {
        super(mode, Constants.YOU_WON);
    }

    @Override
    protected void onInteract () {
        super.onInteract();
    }
}
