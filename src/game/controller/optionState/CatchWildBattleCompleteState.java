package game.controller.optionState;

import constants.Constants;
import game.controller.AbstractBattleMode;


public class CatchWildBattleCompleteState extends AbstractWildBattleCompleteState {

    public CatchWildBattleCompleteState (AbstractBattleMode mode) {
        super(mode, Constants.MODE_MONSTER_CAUGHT);
    }

}
