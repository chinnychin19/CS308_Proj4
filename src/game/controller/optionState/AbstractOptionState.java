package game.controller.optionState;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import constants.Constants;


public abstract class AbstractOptionState {
    protected Graphics myBuffer;
    protected AbstractBattleMode myMode;
    protected int mySelected;
    protected String myName;

    public AbstractOptionState (AbstractBattleMode mode, String name) {
        myMode = mode;
        mySelected = 0;
        myName = name;
        int x = 0, y = Constants.HEIGHT * 2 / 3, w = Constants.WIDTH, h =
                Constants.HEIGHT / 3;
        myBuffer = myMode.getGraphics().create(x, y, w, h);
    }
    
    public String getName() {
        return myName;
    }

    public void paint () {
        myBuffer.setColor(Color.cyan);
        myBuffer.fillRect(0, 0, myBuffer.getClipBounds().width,
                          myBuffer.getClipBounds().height);
        myBuffer.setColor(Color.black);
        myBuffer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    }

    protected abstract void onInteract ();

    protected abstract void onBack ();

    public void act (Input input) {
        if (input.isKeyUpPressed() && mySelected > 0) {
            mySelected--;
        }
        else if (input.isKeyDownPressed()) {
            mySelected++;
        }

        if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
    }

}
