package game.controller.state.option;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.optionState.LivingPartyOptionState;
import game.controller.state.NotListableException;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;


public class MainOptionState extends AbstractListableOptionState {

    List<AbstractOptionState> myOptions;
    
    public MainOptionState (AbstractBattleMode mode) {
        super(mode, "MAIN");
        // TODO: feels dirty
        myOptions = new ArrayList<AbstractOptionState>();
        myOptions.add(new AttackOptionState(mode));
        myOptions.add(new LivingPartyOptionState(mode));
        myOptions.add(new ItemOptionState(mode));
        myOptions.add(new CatchOptionState(mode));
        myOptions.add(new RunAwayOptionState(mode));
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
