package game.controller.optionState;

import java.awt.Color;
import java.util.List;
import game.controller.AbstractBattleMode;
import game.model.attack.Attack;


public class CatchOptionState extends AbstractOptionState {

    public CatchOptionState (AbstractBattleMode mode) {
        super(mode);
    }

    @Override
    protected void onInteract () {
        myMode.setOptionState(new BattleOverState(myMode, "You caught the monster!"));
        if (myMode.getBattle().caughtWildMonster()) {
            myMode.getBattle().transferWildMonster();
        }
        else {
            //myMode.setOptionState(new BattleOverState(myMode, "You did not catch the monster!"));
            //myMode.setOptionState(new MainOptionState(myMode));
            //myMode.getBattle().registerUserCompleted();
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
