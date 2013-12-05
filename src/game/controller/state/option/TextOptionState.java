package game.controller.state.option;

import game.controller.AbstractBattleMode;


public class TextOptionState extends AbstractOptionState {

    private String myText;
    private AbstractOptionState myNextState;


    public TextOptionState (AbstractBattleMode mode, String text) {
        this(mode, text, new MainOptionState(mode));

    }
    
     public TextOptionState (AbstractBattleMode mode, String text, AbstractOptionState nextState) {
         super(mode, "DIALOGUE");
         myText = text;
         myNextState = nextState;
     }
     
     public void setNextState(AbstractOptionState st){
         myNextState = st;
     }
     
    @Override
    public void paint () {
        super.paint();
        int x = 15;
        int y = 30;
        myBuffer.drawString(myText, x, y);
    }

    @Override
    protected void onInteract () {
        myMode.setOptionState(myNextState);
    }

    @Override
    protected void onBack () {
        onInteract();
    }

}
