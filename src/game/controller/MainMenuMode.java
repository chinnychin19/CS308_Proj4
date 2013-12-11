package game.controller;

import util.Sound;
import game.controller.state.AbstractState;
import game.controller.state.mainmenu.MainMenuState;
import game.controller.state.option.AbstractOptionState;
import game.model.GameModel;
import game.view.GameView;

public class MainMenuMode extends AbstractMode{
    
    private AbstractState myState;

    public MainMenuMode (GameModel model, GameView view) {
        super(model, view);
        myState = new MainMenuState(this);
        mySound = new Sound("music/nfl.wav", getController());
    }

    @Override
    public void paint () {
        myState.paint();
    }

    @Override
    public void act () {
        myState.act(getInput());
    }

    @Override
    public void turnOn () {
        super.turnOn();
    }
    
    @Override
    public void turnOff () {
        super.turnOff();
    }
    
    public void setState(AbstractState state){
        myState = state;
    }

}
