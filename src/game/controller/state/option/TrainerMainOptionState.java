package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.controller.optionState.LivingPartyOptionState;

public class TrainerMainOptionState extends AbstractMainOptionState {

    public TrainerMainOptionState (AbstractBattleMode mode) {
        super(mode);
        addOption(new AttackOptionState(mode));
        addOption(new LivingPartyOptionState(mode));
        addOption(new ItemOptionState(mode));
    }

}
