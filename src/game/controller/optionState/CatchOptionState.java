package game.controller.optionState;

import game.controller.AbstractBattleMode;


public class CatchOptionState extends AbstractOptionState {

    public CatchOptionState (AbstractBattleMode mode) {
        super(mode);
    }

    @Override
    protected void onInteract () {
      
        if (myMode.getBattle().caughtWildMonster()) {
            myMode.setOptionState(new BattleOverState(myMode, "You caught the monster!"));
           //myMode.setOptionState(new TextState(myMode, "You caught the monster!"));
        }
//        else {
//            // myMode.setOptionState(new BattleOverState(myMode, "You did not catch the monster!"));
//            myMode.setOptionState(new MainOptionState(myMode));
//            myMode.getBattle().registerUserCompleted();
//        }
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
