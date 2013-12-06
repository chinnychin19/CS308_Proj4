package game.controller.state.mainmenu;

import game.controller.Input;
import game.controller.MainMenuMode;

public abstract class AbstractImmediateActMenuState extends AbstractMenuState{
    
    public AbstractImmediateActMenuState (String name, MainMenuMode mode) {
        super(name, mode);
        // TODO Auto-generated constructor stub

    }
    
    @Override
    public void paint(){
        action();
    }
    
    @Override
    protected void onBack () {
        
    }

    @Override
    protected void onInteract () {
   
    }
    
    protected abstract void action();

}
