package game.controller.state.mainmenu;

import constants.Constants;
import game.controller.MainMenuMode;
import game.model.StateSaver;

public class SaveMenuState extends AbstractImmediateActMenuState{

    public SaveMenuState (MainMenuMode mode) {
        super(Constants.MAIN_MENU_SAVE, mode);
    }

    @Override
    protected void action () {
        //TODO: Implement <<-- get rid of now??
        getMode().getModel().saveState();
        getMode().setState(new MainMenuState(getMode()));
    }

}
