package game.controller.state.mainmenu;

import constants.Constants;
import game.controller.MainMenuMode;

/**
 * Music Menu State that enables the user to turn on or off the music.
 * @author Team Rocket
 *
 */
public class ToggleMusicMenuState extends AbstractImmediateActMenuState {

    public ToggleMusicMenuState (MainMenuMode mode) {
        super(Constants.TOGGLE_MUSIC, mode);
    }

    @Override
    protected void action () {
        getMode().getController().toggleMusic();
        getMode().setState(new MainMenuState(getMode()));
    }

}
