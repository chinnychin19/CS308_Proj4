package game.controller.state.option;

import game.controller.AbstractBattleMode;
import constants.Constants;


public class RunAwayOptionState extends AbstractOptionState {

    public RunAwayOptionState (AbstractBattleMode mode) {
        super(mode, Constants.MODE_RUN_AWAY);
    }

    @Override
    protected void onInteract () {
        myMode.getBattle().reapplyStatistics();
        myMode.getController().setWanderingMode();
    }

    @Override
    public void paint () {
        super.paint();

        int x = 15; //TODO: Chinmay magic numbers 
        int y = 30;
        myBuffer.drawString(Constants.PROMPT_PRESS_TO_RUN, x, y);

    }

    @Override
    protected void onBack () {
        myMode.setOptionState(myMode.getAMainOptionState());
    }
}
