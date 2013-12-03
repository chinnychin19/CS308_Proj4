package game.controller.optionState;

import java.awt.Color;
import java.util.List;
import game.controller.AbstractBattleMode;
import game.model.attack.Attack;

public class BattleOverState extends AbstractOptionState {
    private String myText;
    public BattleOverState (AbstractBattleMode mode, String text) {
        super(mode);
        myText = text;
    }

    @Override
    public void paint(){
       super.paint();
       int x = 15;
       int y = 30;
       
       myBuffer.drawString(myText, x, y);
          
    }
    
    @Override
    protected void onInteract () {
        myMode.getController().setWanderingMode();
    }

    @Override
    protected void onBack () {
        onInteract();
    }

}
