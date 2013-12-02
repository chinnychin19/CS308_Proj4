package game.model.battle;

import game.controller.AbstractBattleMode;
import game.controller.optionState.BattleOverState;
import game.controller.optionState.LivingPartyOptionState;
import game.controller.optionState.MainOptionState;
import game.controller.optionState.PartyOptionState;
import game.controller.optionState.TextState;
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
        if (checkNoMonstersDiedOnTurn()) {
            myEnemyParty.doTurn();            
        }
        if(checkNoMonstersDiedOnTurn()) {
            myMode.setOptionState(new MainOptionState(myMode));
        }
    }
    
    private boolean checkNoMonstersDiedOnTurn () {
        // check if battle is over first
        if (myPlayerParty.getNumberOfAliveMonsters() == 0) {
            computerWon();
            return false; // my monsters are all dead
        }
        else if (myEnemyParty.getNumberOfAliveMonsters() == 0) {
            userWon();
            return false; //opposing monster is dead
        }
        
        if (myPlayerParty.getCurrentMonster().isDead()) {
            myMode.setOptionState(new TextState(myMode, "Monster Died.  Choose a new Monster",
                                                new LivingPartyOptionState(myMode, false)));
            return false; //my current monster is dead
        }
        return true;
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
