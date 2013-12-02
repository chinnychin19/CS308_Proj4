package game.controller.optionState;

import game.controller.AbstractBattleMode;


public class CatchWildBattleCompleteState extends AbstractWildBattleCompleteState {

    public CatchWildBattleCompleteState (AbstractBattleMode mode) {
        super(mode, "You caught the monster!");
    }

}
