package game.controller.optionState;

import game.controller.AbstractBattleMode;

public abstract class AbstractTrainerBattleComplete extends AbstractBattleCompleteState {

    public AbstractTrainerBattleComplete (AbstractBattleMode mode, String text) {
        super(mode, text);
    }
}
