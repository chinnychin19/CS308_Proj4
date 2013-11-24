package game.controller.optionState;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import game.controller.Input;
import game.controller.WildBattleMode;
import game.model.attack.Attack;

public class AttackOptionState extends AbstractOptionState {
    public AttackOptionState (WildBattleMode mode) {
        super(mode);
    }

    @Override
    public void paint () {
        myBuffer.setColor(Color.cyan);
        myBuffer.fillRect(0, 0, myBuffer.getClipBounds().width,
                                 myBuffer.getClipBounds().height);
        myBuffer.setColor(Color.black);
        myBuffer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        int x = 15;
        int y = 30;
        int inc = 50;
        
        List<Attack> attacks = getAttacks();
        for (int i = 0; i < attacks.size(); i++) {
            if (i == mySelected) {
                myBuffer.setColor(Color.white);
            }
            myBuffer.drawString(attacks.get(i).getName(), x, y + i * inc);
            if (i == mySelected) {
                myBuffer.setColor(Color.black);
            }
        }
    }
    
    private List<Attack> getAttacks(){
        return myMode.getBattle().getPlayerParty().getCurrentMonster().getAllAvailableAttacks();
    }

    @Override
    protected void onInteract () {
        List<Attack> attacks = getAttacks();
        Attack chosen = attacks.get(mySelected);
        myMode.getBattle().setNextPlayerAttack(chosen);
        myMode.getBattle().conductTurns();
        myMode.setOptionState(new MainOptionState(myMode));

    }
}
