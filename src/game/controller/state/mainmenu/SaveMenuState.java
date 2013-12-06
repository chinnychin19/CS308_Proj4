package game.controller.state.mainmenu;

import constants.Constants;
import game.controller.MainMenuMode;

public class SaveMenuState extends AbstractImmediateActMenuState{

    public SaveMenuState (MainMenuMode mode) {
        super(Constants.MAIN_MENU_SAVE, mode);
    }

    @Override
    protected void action () {
        //TODO: Implement
        getMode().setState(new MainMenuState(getMode()));
    }

}
