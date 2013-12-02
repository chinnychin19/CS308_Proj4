package game.controller.optionState;

import game.controller.AbstractBattleMode;


public class CatchOptionState extends AbstractOptionState {

    public CatchOptionState (AbstractBattleMode mode) {
        super(mode, "CATCH");
    }

    @Override
    protected void onInteract () {
      
        if (myMode.getBattle().caughtWildMonster()) {
            myMode.setOptionState(new CatchWildBattleCompleteState(myMode));
        }
    }

    @Override
    public void paint () {
        super.paint();

        int x = 15;
        int y = 30;
        myBuffer.drawString("Press interact to ty to catch", x, y);

    }

    @Override
    protected void onBack () {
        myMode.setOptionState(new MainOptionState(myMode));
    }

}
