package game.controller.state.option;

import game.controller.AbstractBattleMode;


public class BattleOverState extends AbstractOptionState {
    private String myText;

    public BattleOverState (AbstractBattleMode mode, String text) {
        super(mode);
        myText = text;
    }

    @Override
    public void paint () {
        super.paint();
        int x = 15; //TODO: Chinmay change these numbers
        int y = 30;

        myBuffer.drawString(myText, x, y);

    }

    @Override
    protected void onInteract () {
        myMode.getController().setWanderingMode();
    }

    @Override
    protected void onBack () {
        onInteract();
    }

}
