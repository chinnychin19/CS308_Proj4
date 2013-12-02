package game.model.battle;

import game.controller.GameController;
import game.model.Fighter;
import game.model.attack.Attack;

public class WildPlayerParty extends AbstractBattleParty {
    private Attack myNextAttack;
    
    public WildPlayerParty (GameController controller, Fighter fighter) {
        super(controller, fighter);
    }

    @Override
    public void doTurn () {
        //TODO: can this stay commented out?
//        myNextAttack.doAttack(getCurrentMonster(), getBattle().getOtherParty(this).getCurrentMonster());
    }
    
    public void setNextAttack(Attack a) {
        myNextAttack = a;
    }
    
}
