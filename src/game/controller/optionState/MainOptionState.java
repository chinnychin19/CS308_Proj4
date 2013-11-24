package game.controller.optionState;

import game.controller.Input;
import game.controller.WildBattleMode;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class MainOptionState extends AbstractOptionState {
    List<OptionBundle> myOptions;
    public MainOptionState(WildBattleMode mode){
        super(mode);
        myOptions = new ArrayList<OptionBundle>();
        addOption("Attack", new AttackOptionState(mode));
        addOption("Party", new PartyOptionState(mode));
        addOption("Items", new ItemOptionState(mode));
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
    
    public void addOption(String s, AbstractOptionState state){
        myOptions.add(new OptionBundle(s, state));
    }
    
    private class OptionBundle {
        private String myString;
        private AbstractOptionState myState;

        OptionBundle (String s, AbstractOptionState state) {
            myString = s;
            myState = state;
        }
        
        public String getName(){
            return myString;
        }
        
        public AbstractOptionState getState(){
            return myState;
        }
    }

    @Override
    protected void onInteract () {
        myMode.setOptionState(myOptions.get(mySelected).getState());
    }

    /**
     * Does Nothing - null-op
     */
    @Override
    protected void onBack () {
        
    }
}
