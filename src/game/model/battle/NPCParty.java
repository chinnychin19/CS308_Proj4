package game.model.battle;

import java.util.List;
import game.controller.GameController;
import game.model.Fighter;
import game.model.FightingNPC;
import game.model.attack.Attack;

public class NPCParty extends AbstractBattleParty {

    public NPCParty (GameController controller, FightingNPC enemy) {
        super(controller, enemy);
    }

    @Override
    public void doTurn () {
        Attack attack = getAttack();
        getBattle().attackPlayer(attack);
        super.doTurn();
    }

    public Attack getAttack () {
        List<Attack> attacks = getCurrentMonster().getAllAvailableAttacks();
        int rand = (int) (Math.random() * attacks.size());
        return attacks.get(rand);
    }

}
