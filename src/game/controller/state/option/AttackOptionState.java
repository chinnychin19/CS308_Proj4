package game.controller.state.option;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.WildBattleMode;
import game.model.attack.Attack;


public class AttackOptionState extends AbstractOptionState {
    public AttackOptionState (AbstractBattleMode mode) {
        super(mode, "ATTACK");
    }

    @Override
    public void paint () {
        super.paint();

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

    @Override
    public void act (Input input) {
        super.act(input);
        if (mySelected >= getAttacks().size()) {
            mySelected = Math.max(0, getAttacks().size() - 1);
        }
    }

    @Override
    protected void onInteract () {
        List<Attack> attacks = getAttacks();
        Attack chosen = attacks.get(mySelected);
        myMode.getBattle().attackEnemy(chosen);

    }

    @Override
    protected void onBack () {
        if(canGoBack()){
            myMode.setOptionState(new MainOptionState(myMode));
        }
    }

    private List<Attack> getAttacks () {
        return myMode.getBattle().getPlayerParty().getCurrentMonster().getAllAvailableAttacks();
    }
}