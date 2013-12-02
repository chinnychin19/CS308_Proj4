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
        myOptions = new ArrayList<AbstractOptionState>();
        addOption("Attack", new AttackOptionState(mode));
        addOption("Party", new PartyOptionState(mode));
        addOption("Items", new ItemOptionState(mode));
        addOption("Catch", new CatchOptionState(mode));
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

    public void addOption (String s, AbstractOptionState state) {
        myOptions.add(state);
    }

    private class OptionBundle {
        private String myString;
        private AbstractOptionState myState;

        OptionBundle (String s, AbstractOptionState state) {
            myString = s;
            myState = state;
        }

        public String getName () {
            return myString;
        }

        public AbstractOptionState getState () {
            return myState;
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
