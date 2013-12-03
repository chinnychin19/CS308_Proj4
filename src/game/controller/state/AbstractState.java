package game.controller.state;

import game.controller.AbstractBattleMode;
import game.controller.AbstractMode;
import game.controller.Input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public abstract class AbstractState implements State{
    protected Graphics myBuffer;
    protected AbstractMode myMode;

	public AbstractState(AbstractMode mode, int x, int y, int w, int h){
		myMode = mode;
        myBuffer = myMode.getGraphics().create(x, y, w, h);
	}

	@Override
	public void paint() {
		myBuffer.setColor(Color.cyan);
        myBuffer.fillRect(0, 0, myBuffer.getClipBounds().width,
                          myBuffer.getClipBounds().height);
        myBuffer.setColor(Color.black);
        myBuffer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
	}

	@Override
	public void act(Input input) {
        if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
	}

	protected abstract void onBack();

	protected abstract void onInteract();

}
