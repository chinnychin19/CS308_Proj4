package game.controller.state.mainmenu;

import constants.Constants;
import game.controller.AbstractMode;
import game.controller.MainMenuMode;
import game.controller.state.AbstractState;

public abstract class AbstractMenuState extends AbstractState {
    private MainMenuMode myMode;
    public AbstractMenuState (String name, MainMenuMode mode) {
            super(name,mode, Constants.MAIN_MENU_X, Constants.MAIN_MENU_Y, Constants.WIDTH, Constants.HEIGHT);
            myMode = mode;
    }
    
    protected MainMenuMode getMode(){
        return myMode;
    }
}
