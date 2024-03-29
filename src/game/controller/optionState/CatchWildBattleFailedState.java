package game.controller.optionState;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.state.option.AbstractOptionState;
import game.controller.state.option.AbstractMainOptionState;
import game.controller.state.option.WildMainOptionState;

/**
 * The player tried to catch a monster in wild battle mode and failed.  After the failed action, this state is presented
 * which will greet the player a text box explaining the situation and then continue game mode.
 * @author Team Rocket
 *
 */
public class CatchWildBattleFailedState extends AbstractOptionState {

    public CatchWildBattleFailedState (AbstractBattleMode mode) {
        super(mode);
    }
    
    @Override
    public void paint () {
        super.paint();

        int x = 15; //TODO: Chinmay will edit
        int y = 30;
        myBuffer.drawString(Constants.MODE_MONSTER_NOT_CAUGHT, x, y);

    }
    
    @Override
    protected void onInteract () {
        myMode.setOptionState(new WildMainOptionState(myMode));
        myMode.getBattle().doNextTurn();
    }
    
    @Override
    protected void onBack () {
        myMode.setOptionState(new WildMainOptionState(myMode));
        myMode.getBattle().doNextTurn();
    }

}
