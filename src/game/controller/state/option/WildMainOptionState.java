package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.controller.optionState.LivingPartyOptionState;

public class WildMainOptionState extends AbstractMainOptionState {

    public WildMainOptionState (AbstractBattleMode mode) {
        super(mode);
        addOption(new AttackOptionState(mode));
        addOption(new LivingPartyOptionState(mode));
        addOption(new ItemOptionState(mode));
        addOption(new CatchOptionState(mode));
        addOption(new RunAwayOptionState(mode));
    }

}
