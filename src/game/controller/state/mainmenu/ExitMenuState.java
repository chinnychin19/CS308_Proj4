package game.controller.state.mainmenu;

import game.controller.MainMenuMode;

public class ExitMenuState extends AbstractImmediateActMenuState{

    public ExitMenuState (MainMenuMode mode) {
        super("EXIT", mode);
    }

    @Override
    protected void action () {
        System.out.println("action");
        getMode().getController().setWanderingMode();
    }

}
