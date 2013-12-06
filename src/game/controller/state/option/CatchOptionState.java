package game.controller.state.option;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.optionState.CatchWildBattleCompleteState;
import game.controller.optionState.CatchWildBattleFailedState;


public class CatchOptionState extends AbstractOptionState {

    public CatchOptionState (AbstractBattleMode mode) {
        super(mode, Constants.MODE_CATCH);
    }

    @Override
    protected void onInteract () {
        if (myMode.getBattle().caughtWildMonster()) {
            myMode.setOptionState(new CatchWildBattleCompleteState(myMode));
        }
        else myMode.setOptionState(new CatchWildBattleFailedState(myMode));
    }

    @Override
    public void paint () {
        super.paint();

        int x = 15;
        int y = 30;
        myBuffer.drawString(Constants.PROMPT_PRESS_TO_CATCH, x, y);

    }

    @Override
    protected void onBack () {
        myMode.setOptionState(new MainOptionState(myMode));
    }

}