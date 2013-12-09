package game.controller.optionState;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.state.option.AbstractOptionState;

/**
 * Base class for the end of a battle that presents text after a completed battle and returns the player to wandering mode
 * @author Team Rocket
 *
 */

public abstract class AbstractBattleCompleteState extends AbstractOptionState {
    private String myText;

    public AbstractBattleCompleteState (AbstractBattleMode mode, String text) {
        super(mode, Constants.MODE_BATTLE_OVER);
        myText = text;
    }

    @Override
    public void paint () {
        super.paint();
        int x = 15; //TODO: Chinmay will change this
        int y = 30;

        myBuffer.drawString(myText, x, y);

    }

    @Override
    protected void onInteract(){
        myMode.getBattle().reapplyStatistics();
    }
    
    @Override
    protected void onBack () {
        onInteract();
    }

}
