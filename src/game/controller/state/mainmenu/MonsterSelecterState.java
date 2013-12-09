package game.controller.state.mainmenu;

import java.awt.Color;
import java.util.List;
import constants.Constants;
import game.controller.AbstractMode;
import game.controller.Input;
import game.controller.MainMenuMode;
import game.controller.state.NotListableException;
import game.model.Monster;

public class MonsterSelecterState extends AbstractListableState{
    private int mySelected;
    public MonsterSelecterState (MainMenuMode mode) {
        super(Constants.MAIN_MENU_PARTY, mode);
        mySelected = 0;
    }

    @Override
    public void paint () {
        super.paint();
        
        try {
            paintList(getMonsterList());
        }
        catch (NotListableException e) {
            e.printStackTrace();
        }
    }
    
    public void act (Input input) {
        actList(input, getMonsterList());
    }
    
    @Override
    protected void onBack () {
        getMode().setState(new MainMenuState(getMode()));
    }

    @Override
    protected void onInteract () {
        getMode().setState(new MonsterSelectingState(getMode(), mySelected));
    }
    
    private List<Monster> getMonsterList(){
        return getMode().getModel().getPlayer().getParty();
    }
}
