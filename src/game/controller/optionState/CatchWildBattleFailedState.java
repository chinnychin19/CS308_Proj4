package game.controller.optionState;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.state.option.AbstractOptionState;
import game.controller.state.option.MainOptionState;

public class CatchWildBattleFailedState extends AbstractOptionState {

    public CatchWildBattleFailedState (AbstractBattleMode mode) {
        super(mode);
    }
    
    @Override
    public void paint () {
        super.paint();

        int x = 15;
        int y = 30;
        myBuffer.drawString(Constants.MODE_MONSTER_NOT_CAUGHT, x, y);

    }
    
    @Override
    protected void onInteract () {
        myMode.setOptionState(new MainOptionState(myMode));
        myMode.getBattle().registerUserCompleted();
    }
    
    @Override
    protected void onBack () {
        myMode.setOptionState(new MainOptionState(myMode));
    }

}
