package game.controller.state;

import game.controller.AbstractBattleMode;
import game.controller.AbstractMode;

import java.awt.Graphics;

public class AbstractState {
    protected Graphics myBuffer;
    protected AbstractMode myMode;

	public AbstractState(AbstractMode mode, int x, int y, int w, int h){
        myBuffer = myMode.getGraphics().create(x, y, w, h);
	}

}
