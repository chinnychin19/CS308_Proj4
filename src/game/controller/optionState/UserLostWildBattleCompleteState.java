package game.controller.optionState;

import game.controller.AbstractBattleMode;

public class UserLostWildBattleCompleteState extends AbstractWildBattleCompleteState {

    public UserLostWildBattleCompleteState (AbstractBattleMode mode) {
        super(mode, "YOU LOST :(");
    }

    @Override
    protected void onInteract () {
        super.onInteract();
        //let's keep it simple. the player's monsters are all revived with no consequence
        myMode.getController().getModel().getPlayer().healAllMonsters();
        myMode.getController().getModel().getPlayer().goToLastSavedLoc();
    }

}
