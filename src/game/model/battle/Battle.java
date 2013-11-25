package game.model.battle;

import game.model.Monster;
import game.model.attack.Attack;

public class Battle {
    AbstractBattleParty myPlayerParty;
    AbstractBattleParty myEnemyParty;
    public Battle(AbstractBattleParty playerParty, AbstractBattleParty enemyParty) {
        myPlayerParty = playerParty;
        myEnemyParty = enemyParty;
    }
    
    public AbstractBattleParty getOtherParty(AbstractBattleParty self) {
        return (self == myPlayerParty) ? myEnemyParty : myPlayerParty;
    }
    
    public AbstractBattleParty getPlayerParty() {
        return myPlayerParty;
    }
    
    public AbstractBattleParty getEnemyParty() {
        return myEnemyParty;
    }
    
    public void setNextPlayerAttack (Attack a) {
        //TODO: make abstract player party? extend for wild and trainer battles?
        ((WildPlayerParty) myPlayerParty).setNextAttack(a);
    }
    
    public void conductTurns() { //TODO: implement properly
        myPlayerParty.doTurn(); //TODO: supposed to wait for player to choose something
        myEnemyParty.doTurn();
        System.out.println("health: "+myPlayerParty.getCurrentMonster().getCurHP());
    }
    
    public boolean isOver() {
        boolean aLost = true, bLost = false;
        for (Monster m : myPlayerParty.getMonsters()) {
            if (m.getCurHP() > 0) {
                aLost = false;
                break;
            }
        }
        for (Monster m : myEnemyParty.getMonsters()) {
            if (m.getCurHP() > 0) {
                bLost = false;
                break;
            }
        }
        return aLost || bLost;
    }
}
