package game.controller.optionState;

import game.controller.AbstractBattleMode;
import game.controller.Input;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class MainOptionState extends AbstractOptionState {
    List<AbstractOptionState> myOptions;

    public MainOptionState (AbstractBattleMode mode) {
        super(mode, "MAIN");
        //TODO: feels dirty
        myOptions = new ArrayList<AbstractOptionState>();
        myOptions.add( new AttackOptionState(mode));
        myOptions.add( new LivingPartyOptionState(mode));
        myOptions.add(new ItemOptionState(mode));
        myOptions.add(new CatchOptionState(mode));
    }

    @Override
    public void act (Input input) {
        super.act(input);
        if (mySelected >= myOptions.size()) {
            mySelected = Math.max(0, myOptions.size() - 1);
        }
    }

    @Override
    public void paint () {
        super.paint();

        int x = 15;
        int y = 30;
        int inc = 50;
        for (int i = 0; i < myOptions.size(); i++) {
            if (i == mySelected) {
                myBuffer.setColor(Color.white);
            }
            myBuffer.drawString(myOptions.get(i).getName(), x, y + i * inc);
            if (i == mySelected) {
                myBuffer.setColor(Color.black);
            }
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
