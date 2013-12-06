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
        getBattle().attackPlayer(attack);
        super.doTurn();
    }

    public Attack getAttack () {
        List<Attack> attacks = getCurrentMonster().getAllAvailableAttacks();
        int rand = (int) (Math.random() * attacks.size());
        return attacks.get(rand);
    }

    public double calculateCatchProbability () {
        return getBattle().getEnemyParty().getCurrentMonster().getCatchProbability();
    }
}
