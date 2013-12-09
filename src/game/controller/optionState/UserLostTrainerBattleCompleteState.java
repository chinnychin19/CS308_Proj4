package game.controller.optionState;

import constants.Constants;
import location.Loc;
import game.controller.AbstractBattleMode;
import game.model.World;

public class UserLostTrainerBattleCompleteState extends AbstractBattleCompleteState {

    public UserLostTrainerBattleCompleteState (AbstractBattleMode mode) {
        super(mode, Constants.YOU_LOST);
    }

    @Override
    protected void onInteract () {
        myMode.getController().getModel().getPlayer().healAllMonsters();
        myMode.getController().getModel().getPlayer().goToLastSavedLoc();
        myMode.getController().setWanderingMode();
    }
}
