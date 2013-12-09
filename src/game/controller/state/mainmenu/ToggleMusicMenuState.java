package game.controller.state.mainmenu;

import game.controller.MainMenuMode;

public class ToggleMusicMenuState extends AbstractImmediateActMenuState {

    public ToggleMusicMenuState (MainMenuMode mode) {
        super("TOGGLE MUSIC", mode);
    }

    @Override
    protected void action () {
        getMode().setState(new MainMenuState(getMode()));
    }

}
