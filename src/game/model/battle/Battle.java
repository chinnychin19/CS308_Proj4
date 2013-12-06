package game.model.battle;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.TrainerBattleMode;
import game.controller.optionState.LivingPartyOptionState;
import game.controller.optionState.UserLostWildBattleCompleteState;
import game.controller.optionState.UserWonWildBattleCompleteState;
import game.controller.state.option.TextOptionState;
import game.controller.state.option.StateTransitionTextOptionState;
import game.model.FightingNPC;
import game.model.LevelChange;
import game.model.Monster;
import game.model.attack.Attack;


public class Battle {
    AbstractBattleParty myPlayerParty;
    AbstractBattleParty myEnemyParty;
    AbstractBattleMode myMode;
    private static final double CATCH_MIN = 0.80;
    private static final double CATCH_MAX = 0.99;
    private boolean myIsUsersTurn;

    public Battle (AbstractBattleParty playerParty,
                   AbstractBattleParty enemyParty,
                   AbstractBattleMode mode) {
        myPlayerParty = playerParty;
        myEnemyParty = enemyParty;
        myMode = mode;
        myIsUsersTurn = true;
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

    public void attackEnemy (Attack a) {
        double damage = a.doAttack(myPlayerParty.getCurrentMonster(), myEnemyParty.getCurrentMonster());
        myMode.pushState(new StateTransitionTextOptionState(myMode, String.format("Your %s did %f damage", myPlayerParty.getCurrentMonster().getName(), damage), this));
    }

    public void attackPlayer (Attack a) {
        double damage = a.doAttack(myEnemyParty.getCurrentMonster(), myPlayerParty.getCurrentMonster());
        myMode.pushState(new TextOptionState(myMode, String
                .format("%s did %f damage", myEnemyParty.getCurrentMonster().getName(), damage)));

    }
    
    private void handleMonsterDeaths () {
        if (myPlayerParty.getCurrentMonster().isDead()) {
            handleUserMonsterDied();
        }
        if (myEnemyParty.getCurrentMonster().isDead()){
            handleEnemyMonsterDied();
        }
    }

    private void handleUserMonsterDied () {
        if (myPlayerParty.getNumberOfAliveMonsters() == 0) {
            userLost();
        }
        else {
            myMode.setOptionState(new TextOptionState(myMode, Constants.PROMPT_MONSTER_DEAD,
                                                      new LivingPartyOptionState(myMode, false)));
        }
    }

    //TODO: string constants
    private void handleEnemyMonsterDied () {
        myIsUsersTurn = true;
        LevelChange change = myPlayerParty.getCurrentMonster().addExperience(myEnemyParty.getCurrentMonster().getRewardExperience());
        if (myEnemyParty.getNumberOfAliveMonsters() == 0) {
            userWon();
        } else {
            myEnemyParty.chooseRandomNextMonster();
        }
        switch(change){
            case NONE:
                break;
            case LEVEL_UP:
                myMode.pushState(new TextOptionState(myMode, "You Leveled Up!"));
                break;
            case EVOLUTION:
                myMode.pushState(new TextOptionState(myMode, "You Evolved!"));
                break;
            case BOTH:
                myMode.pushState(new TextOptionState(myMode, "You Evolved!"));
                myMode.pushState(new TextOptionState(myMode, "You Leveled Up!"));
                break;
            default:
                break;
        }
        myMode.pushState(new TextOptionState(myMode, "You Killed Da Monster!"));
    }

    private void userLost () {
        // Heal enemy's monsters for the next time you battle
        for (Monster m: myEnemyParty.getMonsters()) {
            m.heal();
        }
        myMode.setOptionState(new UserLostWildBattleCompleteState(myMode));
    }

    private void userWon () {
        if (myMode instanceof TrainerBattleMode) {
            ((FightingNPC) myEnemyParty.getFighter()).setDefeated(true);
        }
        myMode.setOptionState(new UserWonWildBattleCompleteState(myMode));
    }

    public boolean isOver () {
        return myEnemyParty.getNumberOfAliveMonsters() + myPlayerParty.getNumberOfAliveMonsters() == 0;
    }

    public boolean caughtWildMonster () {
        double randomFactor = CATCH_MIN + Math.random() * (CATCH_MAX - CATCH_MIN);
        double probability = ((WildMonsterParty) myEnemyParty).getCatchProbability() * randomFactor;
        System.out.print(probability + " ---> This is the catch probability in Battle.java");
        return (Math.random() < probability);
    }

    public void doNextTurn () {
        toggleUsersTurn();
        handleMonsterDeaths(); // sets myIsUsersTurn to true if enemy dies
        if(!myIsUsersTurn) {
            getEnemyParty().doTurn();
        }
    }

    private void toggleUsersTurn () {
        myIsUsersTurn = !myIsUsersTurn;
    }
}
