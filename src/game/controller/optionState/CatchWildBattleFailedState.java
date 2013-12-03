package game.controller.optionState;

import game.controller.AbstractBattleMode;

public class CatchWildBattleFailedState extends AbstractOptionState {

    public CatchWildBattleFailedState (AbstractBattleMode mode) {
        super(mode);
    }
    
    @Override
    public void paint () {
        super.paint();

        int x = 15;
        int y = 30;
        myBuffer.drawString("You failed to catch the wild monster!", x, y);

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
