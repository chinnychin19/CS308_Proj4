package game.controller.state.option;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import game.controller.AbstractBattleMode;
import game.controller.Input;
import game.controller.WildBattleMode;
import game.controller.state.NotListableException;
import game.model.attack.Attack;


public class AttackOptionState extends AbstractListableOptionState {
    
    public AttackOptionState (AbstractBattleMode mode) {
        super(mode, "ATTACK");
    }

    @Override
    public void paint () {
        try { 
            paintList(getAttacks()); 
        }
        catch (NotListableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void act (Input input) {
        super.act(input);
        actList(input, getAttacks());
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
            myMode.setOptionState(myMode.getAMainOptionState());
        }
    }

    private List<Attack> getAttacks () {
        return myMode.getBattle().getPlayerParty().getCurrentMonster().getAllAvailableAttacks();
    }
}
