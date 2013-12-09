package game.controller.state.mainmenu;

import constants.Constants;
import game.controller.MainMenuMode;

public class ExitMenuState extends AbstractImmediateActMenuState{

    public ExitMenuState (MainMenuMode mode) {
        super(Constants.MAIN_MENU_EXIT, mode);
    }

    @Override
    protected void action () {
        getMode().getController().setWanderingMode();
    }

}
