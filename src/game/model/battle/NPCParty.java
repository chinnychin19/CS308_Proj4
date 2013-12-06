package game.model.battle;

import java.util.List;
import game.controller.GameController;
import game.model.Fighter;
import game.model.FightingNPC;
import game.model.attack.Attack;

public class NPCParty extends AbstractEnemyParty {

    public NPCParty (GameController controller, FightingNPC enemy) {
        super(controller, enemy);
    }
}
