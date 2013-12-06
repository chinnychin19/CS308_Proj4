package game.controller.state.mainmenu;

import game.controller.MainMenuMode;

public class SaveMenuState extends AbstractImmediateActMenuState{

    public SaveMenuState (MainMenuMode mode) {
        super("SAVE", mode);
    }

    @Override
    protected void action () {
        //TODO: Implement
        getMode().setState(new MainMenuState(getMode()));
    }

}
