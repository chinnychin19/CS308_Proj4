package game.controller.optionState;

import game.controller.AbstractBattleMode;


public class TextState extends AbstractOptionState {

    private String myText;
    private AbstractBattleMode myMode;

    // private AbstractOptionState myNextState;

    public TextState (AbstractBattleMode mode, String text) {
        super(mode, "DIALOGUE");
        myMode = mode;
        myText = text;
    }

    // public TextState (AbstractBattleMode mode, String text, AbstractOptionState nextState) {
    // super(mode);
    // myNextState = nextState;
    // new TextState(mode, text);
    // }

    @Override
    public void paint () {
        super.paint();
        int x = 15;
        int y = 30;
        myBuffer.drawString(myText, x, y);
    }

    @Override
    protected void onInteract () {
        // if(myNextState != null) myMode.setOptionState(myNextState);
        // else myMode.setOptionState(new MainOptionState(myMode));
        myMode.setOptionState(new MainOptionState(myMode));
    }

    @Override
    protected void onBack () {
        onInteract();
    }

}
