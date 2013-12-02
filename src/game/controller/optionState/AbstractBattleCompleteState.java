package game.controller.optionState;

import game.controller.AbstractBattleMode;


public abstract class AbstractBattleCompleteState extends AbstractOptionState {
    private String myText;

    public AbstractBattleCompleteState (AbstractBattleMode mode, String text) {
        super(mode, "BATTLE OVER!");
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
