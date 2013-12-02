package game.model.battle;

import game.controller.AbstractBattleMode;
import game.controller.optionState.BattleOverState;
import game.controller.optionState.MainOptionState;
import game.model.attack.Attack;


public class Battle {
    AbstractBattleParty myPlayerParty;
    AbstractBattleParty myEnemyParty;
    AbstractBattleMode myMode;
    private static final double CATCH_MIN = 0.90;
    private static final double RANDOM_FACTOR = CATCH_MIN + Math.random() * (1.00 - CATCH_MIN);

    public Battle (AbstractBattleParty playerParty,
                   AbstractBattleParty enemyParty,
                   AbstractBattleMode mode) {
        myPlayerParty = playerParty;
        myEnemyParty = enemyParty;
        myMode = mode;
    }

    public AbstractBattleParty getOtherParty (AbstractBattleParty self) {
        return (self == myPlayerParty) ? myEnemyParty : myPlayerParty;
    }

    public AbstractBattleParty getPlayerParty () {
        return myPlayerParty;
    }

    public AbstractBattleParty getEnemyParty () {
        return myEnemyParty;
    }

    public void setNextPlayerAttack (Attack a) {
        // TODO: make abstract player party? extend for wild and trainer battles?
        ((WildPlayerParty) myPlayerParty).setNextAttack(a);
    }

    public void attackEnemy (Attack a) {
        a.doAttack(myPlayerParty.getCurrentMonster(), myEnemyParty.getCurrentMonster());
    }

    public void attackPlayer (Attack a) {
        a.doAttack(myEnemyParty.getCurrentMonster(), myPlayerParty.getCurrentMonster());
    }

    public void registerUserCompleted () {
        if (myEnemyParty.getNumberOfAliveMonsters() == 0) {
            System.out.println("=============\nYOU WON!\n=============");
            userWon();
        }
        else {
            myEnemyParty.doTurn();
            System.out.println("health: " + myPlayerParty.getCurrentMonster().getCurHP());
            if (myPlayerParty.getNumberOfAliveMonsters() == 0) {
                computerWon();
            }
            else {
                myMode.setOptionState(new MainOptionState(myMode));
            }
        }
    }

    private void computerWon () {
        myMode.setOptionState(new BattleOverState(myMode, "You were defeated :("));
    }

    private void userWon () {
        myMode.setOptionState(new BattleOverState(myMode, "You won! :)"));
    }

    public boolean isOver () {
        return myEnemyParty.getNumberOfAliveMonsters() + myPlayerParty.getNumberOfAliveMonsters() == 0;
    }

    public boolean caughtWildMonster () {
        WildMonsterParty wildMonster = (WildMonsterParty) myEnemyParty;
        double probability = wildMonster.calculateCatchProbability() * RANDOM_FACTOR;
        if (Math.random() <= probability) {
            transferWildMonster();
            return true;
        }
        return false;
    }

    public void transferWildMonster () {
        myMode.getModel().getPlayer().addMonsterToParty(myEnemyParty.getCurrentMonster());
    }
}
