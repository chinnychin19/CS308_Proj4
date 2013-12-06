package game.controller.optionState;

import location.Loc;
import game.controller.AbstractBattleMode;
import game.model.World;

public class UserLostTrainerBattleCompleteState extends AbstractBattleCompleteState {

    public UserLostTrainerBattleCompleteState (AbstractBattleMode mode) {
        super(mode, "You Lost :(");
    }

    @Override
    protected void onInteract () {
        //TODO: lose money
        myMode.getController().getModel().getPlayer().healAllMonsters();
        myMode.getController().getModel().getPlayer().goToLastSavedLoc();
        myMode.getController().setWanderingMode();
    }
}
