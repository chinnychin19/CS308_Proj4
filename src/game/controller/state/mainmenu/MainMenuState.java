package game.controller.state.mainmenu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import constants.Constants;
import game.controller.AbstractMode;
import game.controller.Input;
import game.controller.MainMenuMode;
import game.controller.optionState.LivingPartyOptionState;
import game.controller.state.AbstractState;
import game.controller.state.Listable;
import game.controller.state.NotListableException;
import game.controller.state.option.AbstractOptionState;
import game.controller.state.option.AttackOptionState;
import game.controller.state.option.CatchOptionState;
import game.controller.state.option.ItemOptionState;

public class MainMenuState extends AbstractListableState {
    private List<AbstractState> myOptions;
    public MainMenuState(MainMenuMode mode){
        this(mode, Constants.MAIN_MENU_X, Constants.MAIN_MENU_Y, Constants.WIDTH, Constants.HEIGHT);
    }
    
    public MainMenuState (MainMenuMode mode, int x, int y, int w, int h) {
        super("", mode);
        myOptions = new ArrayList<AbstractState>();
        myOptions.add(new MonsterSelecterState(getMode()));
        myOptions.add(new ItemMenuState(getMode()));
        myOptions.add(new KeyItemMenuState(getMode()));
        myOptions.add(new SaveMenuState(getMode()));
        myOptions.add(new ToggleMusicMenuState(getMode()));
        myOptions.add(new ExitMenuState(getMode()));
    }
    
    @Override
    public void paint () {
        super.paint();

        try {
            paintList(myOptions);
        }
        catch (NotListableException e) {
            e.printStackTrace();
        }
    }
    
    public void act (Input input) {
        actList(input, myOptions);
    }
    
    @Override
    protected void onBack () {
        getMode().getController().setWanderingMode();
    }

    @Override
    protected void onInteract () {
        getMode().setState(myOptions.get(getSelected()));
    }
}
