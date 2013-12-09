package game.controller.optionState;

import constants.Constants;
import game.controller.AbstractBattleMode;

/**
 * State presented after the player loses a wild region battle
 * @author Team Rocket
 *
 */
public class UserLostWildBattleCompleteState extends AbstractWildBattleCompleteState {

    public UserLostWildBattleCompleteState (AbstractBattleMode mode) {
        super(mode, Constants.YOU_LOST);
    }

    @Override
    protected void onInteract () {
        super.onInteract();
        //let's keep it simple. the player's monsters are all revived with no consequence
        myMode.getController().getModel().getPlayer().healAllMonsters();
        myMode.getController().getModel().getPlayer().goToLastSavedLoc();
    }

}
