package game.model.battle;

import java.util.List;
import game.controller.GameController;
import game.model.Fighter;
import game.model.Monster;
import game.model.attack.Attack;

public abstract class AbstractEnemyParty extends AbstractBattleParty {

    public AbstractEnemyParty (GameController controller, Fighter fighter) {
        super(controller, fighter);
    }
    public AbstractEnemyParty (GameController controller, Monster monster) {
        super(controller, monster);
    }

    @Override
    public void doTurn () {
        Attack attack = getAttack();
        getBattle().attackPlayer(attack);
        getBattle().handleMonsterDeaths();
        //super.doTurn();
    }

    private Attack getAttack () {
        List<Attack> attacks = getCurrentMonster().getAllAvailableAttacks();
        int rand = (int) (Math.random() * attacks.size());
        return attacks.get(rand);
    }

}
