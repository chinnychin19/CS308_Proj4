package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.state.AbstractState;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import constants.Constants;


public abstract class AbstractOptionState extends AbstractState{
    protected AbstractBattleMode myMode;
    protected int mySelected = 0;
    private boolean myCanGoBack;

    protected int myNumOptions;


    public AbstractOptionState (AbstractBattleMode mode) {
        this(mode, Constants.MODE_DEFAULT);
    }

    public AbstractOptionState (AbstractBattleMode mode, String name) {
        this(mode, name, true);
    }

    public AbstractOptionState (AbstractBattleMode mode,  String name, boolean canGoBack) {
        super(name, mode, 0, Constants.HEIGHT*2/3, Constants.WIDTH, Constants.HEIGHT/3);
        myNumOptions = 0;
        myMode = mode;
        mySelected = 0;
        myName = name;
        myCanGoBack = canGoBack;
    }

    public String getName () {
        return myName;
    }

    public void paint () {
        myBuffer.setColor(Color.cyan);
        myBuffer.fillRect(0, 0, myBuffer.getClipBounds().width,
                          myBuffer.getClipBounds().height);
        myBuffer.setColor(Color.black);
        myBuffer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    }

    protected boolean canGoBack () {
        return myCanGoBack;
    }

    protected abstract void onInteract ();

    protected abstract void onBack ();

    public void act (Input input) {
        if (input.isKeyUpPressed()) {
           if(mySelected != 0) mySelected--;
           else if(myNumOptions != 0) mySelected = myNumOptions - 1;
        }

        if (input.isKeyDownPressed()) {
            if (mySelected == myNumOptions - 1)
                mySelected = 0;
            else mySelected++;
        }

        if (input.isKeyLeftPressed()) {
            if (mySelected - 3 >= 0) mySelected = mySelected - 3;
        }

        if (input.isKeyRightPressed()) {
            if (mySelected + 3 < myNumOptions) mySelected = mySelected + 3;
        }

        if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
    }

}
