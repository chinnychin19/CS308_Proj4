package game.controller.optionState;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.state.option.AbstractOptionState;


public abstract class AbstractBattleCompleteState extends AbstractOptionState {
    private String myText;

    public AbstractBattleCompleteState (AbstractBattleMode mode, String text) {
        super(mode, Constants.MODE_BATTLE_OVER);
        myText = text;
    }

    @Override
    public void paint () {
        super.paint();
        int x = 15;
        int y = 30;

        myBuffer.drawString(myText, x, y);

    }

    @Override
    protected void onBack () {
        onInteract();
    }

}
