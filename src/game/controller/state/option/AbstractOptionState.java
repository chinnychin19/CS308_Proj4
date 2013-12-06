package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import constants.Constants;


public abstract class AbstractOptionState {
    protected Graphics myBuffer;
    protected AbstractBattleMode myMode;
    protected int mySelected = 0;
    private boolean myCanGoBack;
    protected String myName;
    List<AbstractOptionState> myOptions;

    public AbstractOptionState (AbstractBattleMode mode) {
        this(mode, Constants.MODE_DEFAULT);
    }

    public AbstractOptionState (AbstractBattleMode mode, String name) {
        this(mode, name, true);
    }

    public AbstractOptionState (AbstractBattleMode mode, String name, boolean canGoBack) {
        myMode = mode;
        mySelected = 0;
        myName = name;
        int x = 0, y = Constants.HEIGHT * 2 / 3, w = Constants.WIDTH, h =
                Constants.HEIGHT / 3;
        myBuffer = myMode.getGraphics().create(x, y, w, h);
        myCanGoBack = canGoBack;
        myOptions = new ArrayList<AbstractOptionState>();
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
        if (input.isKeyUpPressed() && mySelected != 0) {
            mySelected--;
        }

        if (input.isKeyDownPressed()) {
            if (mySelected == myOptions.size() - 1)
                mySelected = 0;
            else mySelected++;
        }

        if (input.isKeyLeftPressed()) {
            if (mySelected - 3 >= 0) mySelected = mySelected - 3;
        }

        if (input.isKeyRightPressed()) {
            if (mySelected + 3 < myOptions.size()) mySelected = mySelected + 3;
        }

        if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
    }

}
