package game.controller.state.option;

import constants.Constants;
import game.controller.AbstractBattleMode;


public class TextOptionState extends AbstractOptionState {

    private String myText;
    private AbstractOptionState myNextState;


    public TextOptionState (AbstractBattleMode mode, String text) {
        this(mode, text, mode.getAMainOptionState());

    }
    
     public TextOptionState (AbstractBattleMode mode, String text, AbstractOptionState nextState) {
         super(mode, Constants.TEXT_DIALOGUE);
         myText = text;
         myNextState = nextState;
     }
     
     public void setNextState(AbstractOptionState st){
         myNextState = st;
     }
     
    @Override
    public void paint () { // Word wraps the string
        super.paint();
        int x = Constants.TEXT_START_X;
        int y = Constants.TEXT_START_Y;
        int offset = Constants.TEXT_START_INC;
        int charsPerLine = Constants.TEXT_CHARS_PER_LINE;
        String[] words = myText.split(Constants.SPLIT_SLASH_S);
        int curLineNumber = Constants.ZERO;
        int curWordIndex = Constants.ZERO;
        while (true) {
            String curLine = Constants.BLANK_STRING;
            while (true) {
                if (curWordIndex == words.length) {
                    break;
                }
                if (curLine.length() + words[curWordIndex].length() <= charsPerLine) {
                    curLine += words[curWordIndex] + Constants.SPACE_STRING;
                    curWordIndex++;
                } else {
                    break;
                }
            }
            myBuffer.drawString(curLine, x, y+curLineNumber*offset);
            if (curWordIndex == words.length) {
                break;
            }
            curLineNumber++;
        }
    }
    
    @Override
    protected void onInteract () {
        myMode.setOptionState(myNextState);
        myMode.removeHitMarkers();
    }

    @Override
    protected void onBack () {
        onInteract();
    }

}
