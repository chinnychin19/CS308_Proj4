package game.controller.optionState;

import game.controller.Input;
import game.controller.WildBattleMode;
import java.awt.Graphics;
import java.util.List;
import constants.Constants;

public abstract class AbstractOptionState {
    protected Graphics myBuffer;
    protected WildBattleMode myMode;
    protected int mySelected = 0;

    public AbstractOptionState(WildBattleMode mode){
        myMode = mode;
        int x = 0, y = Constants.HEIGHT * 2 / 3, w = Constants.WIDTH, h =
                Constants.HEIGHT / 3;
        myBuffer = myMode.getGraphics().create(x, y, w, h);
    }
    public abstract void paint();
    
    protected abstract void onInteract();
    
    public void act(Input input){
        if (input.isKeyUpPressed()) {
            mySelected--;
        }
        else if (input.isKeyDownPressed()) {
            mySelected++;
        }
        
        if(input.isKeyInteractPressed()){
            onInteract();
        }
    }
    
    
}
