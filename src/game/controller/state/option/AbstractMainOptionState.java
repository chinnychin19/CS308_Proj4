package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.state.NotListableException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractMainOptionState extends AbstractListableOptionState {

    private List<AbstractOptionState> myOptions;
    
    public AbstractMainOptionState (AbstractBattleMode mode) {
        super(mode, "MAIN");
        myOptions = new ArrayList<AbstractOptionState>();
    }
    
    protected List<AbstractOptionState> getOptions() {
        return myOptions;
    }
    
    protected void addOption(AbstractOptionState st) {
        myOptions.add(st);
    }

    @Override
    public void act (Input input) {
        super.act(input);
        actList(input, myOptions);
    }

    @Override
    public void paint () {        
        try { 
            paintList(myOptions); 
        }
        catch (NotListableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onInteract () {
        myMode.setOptionState(myOptions.get(mySelected));
    }

    /**
     * Does Nothing - null-op
     */
    @Override
    protected void onBack () {

    }
}
