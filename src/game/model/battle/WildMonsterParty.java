package game.model.battle;

import java.util.List;
import game.controller.GameController;
import game.model.Fighter;
import game.model.Monster;
import game.model.attack.Attack;

public class WildMonsterParty extends AbstractBattleParty {

    public WildMonsterParty (GameController controller, Monster monster) {
        super(controller, monster);
    }

    @Override
    public void doTurn () {
        Attack attack = getAttack();
        System.out.println(attack.getName());
        attack.doAttack(getCurrentMonster(), getBattle().getOtherParty(this).getCurrentMonster());
    }

    public Attack getAttack() {
        List<Attack> attacks = getCurrentMonster().getAllAvailableAttacks();
        int rand = (int) (Math.random() * attacks.size());
        return attacks.get(rand);
    }
}
