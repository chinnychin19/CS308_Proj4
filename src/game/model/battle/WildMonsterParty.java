package game.model.battle;

import java.util.List;
import game.controller.GameController;
import game.model.Monster;
import game.model.attack.Attack;


public class WildMonsterParty extends AbstractEnemyParty {

    public WildMonsterParty (GameController controller, Monster monster) {
        super(controller, monster);
    }

    /**
     * Returns the probability of catching the current enemy monster in this enemy party.
     * The player's monster level is found
     * healthFactor - the current HP of the enemy monster divided by the max HP
     * levelFactor - the level of the player's monster divided by the level of the enemy monster
     * 
     * The final formula is the enemy monster's catchrate * levelfactor / healthfactor
     * 
     * healthFactor decreases from 1 to 0 as the monster's HP decreases - this increases the 
     * probability of catching the monster, as it should.
     * 
     * levelFactor is small if the players monster has a low level compared to the enemy monster
     * and is big if the player monster has a high level compared to the enemy monster
     * A small levelFactor decreases the probability of catching a monster and vice versa
     * 
     * Catchrate - passed in through the definition.JSON.  Inherent and unique to every monster.
     * 
     * @return catch probability
     */
    public double getCatchProbability () {
        
        int playerMonsterLevel = getBattle().getPlayerParty().getCurrentMonster().getLevel().intValue();
        double healthFactor = (double) getCurrentMonster().getCurHP() / (double) getCurrentMonster().getMaxHP();
        double levelFactor = playerMonsterLevel / getCurrentMonster().getLevel();
        return getCurrentMonster().getCatchRate() * levelFactor / healthFactor;
        
    }
}
