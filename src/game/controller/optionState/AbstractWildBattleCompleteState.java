package game.controller.optionState;

import game.controller.AbstractBattleMode;

public abstract class AbstractWildBattleCompleteState extends AbstractBattleCompleteState {

    public AbstractWildBattleCompleteState (AbstractBattleMode mode, String text) {
        super(mode, text);
    }

    @Override
    protected void onInteract() {
        myMode.getController().setWanderingMode();
    }
}
