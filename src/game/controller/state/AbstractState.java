package game.controller.state;

import game.controller.AbstractBattleMode;
import game.controller.AbstractMode;
import game.controller.Input;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public abstract class AbstractState implements State, Listable {
    protected Graphics myBuffer;
    protected String myName;

    public AbstractState (String name, AbstractMode mode, int x, int y, int w, int h) {
        myBuffer = mode.getGraphics().create(x, y, w, h);
        myName = name;
    }

    @Override
    public void paint () {
        myBuffer.setColor(getBackgroundColor());
        myBuffer.fillRect(0, 0, myBuffer.getClipBounds().width,
                          myBuffer.getClipBounds().height);
        myBuffer.setColor(getFontColor());
        myBuffer.setFont(getFont());
    }

    @Override
    public void act (Input input) {
        if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
    }

    public String getName () {
        return myName;
    }

    protected abstract void onBack ();

    protected abstract void onInteract ();

    protected Color getBackgroundColor () {
        return Color.cyan;
    }

    protected Color getFontColor () {
        return Color.BLACK;
    }

    protected Font getFont () {
        return new Font(Font.MONOSPACED, Font.BOLD, 20);
    }

}
