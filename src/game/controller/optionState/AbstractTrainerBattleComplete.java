package game.controller.optionState;

import game.controller.AbstractBattleMode;

/**
 * Trainer battle is complete.  There is a won state and lost state that extend this class and determine the appropriate
 * action based on the result of the battle.
 * @author Team Rocket
 *
 */
public abstract class AbstractTrainerBattleComplete extends AbstractBattleCompleteState {

    public AbstractTrainerBattleComplete (AbstractBattleMode mode, String text) {
        super(mode, text);
    }
}
