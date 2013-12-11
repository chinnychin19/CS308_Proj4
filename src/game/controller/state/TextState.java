package game.controller.state;

import constants.Constants;
import game.controller.AbstractMode;


public class TextState extends AbstractState {
    private String myText;
    private AbstractMode myMode;

    public TextState (AbstractMode mode, int x, int y, int w, int h, String text) {
        super(Constants.BLANK_STRING, mode, x, y, w, h);
        myText = text;
        myMode = mode;
        getMode().turnMovementOff();
    }

    @Override
    public void paint () {
        super.paint();
        int x = Constants.TEXT_START_X;
        int y = Constants.TEXT_START_Y;
        int offset = Constants.TEXT_Y_INC;
        int charsPerLine = Constants.TEXT_CHARS_PER_LINE;
        String[] words = myText.split(Constants.SPLIT_SLASH_S);
        int curLineNumber = 0;
        int curWordIndex = 0;
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
    protected void onBack () {
        getMode().turnMovementOn();
        myMode.clearDynamicStates();
    }

    @Override
    protected void onInteract () {
        
    }

    protected AbstractMode getMode () {
        return myMode;
    }
}
