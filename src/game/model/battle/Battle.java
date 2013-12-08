package game.model.battle;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.TrainerBattleMode;
import game.controller.optionState.LivingPartyOptionState;
import game.controller.optionState.UserLostWildBattleCompleteState;
import game.controller.optionState.UserWonWildBattleCompleteState;
import game.controller.state.option.TextOptionState;
import game.controller.state.option.StateTransitionTextOptionState;
import game.controller.state.option.UserStateTransitionTextOptionState;
import game.model.FightingNPC;
import game.model.LevelChange;
import game.model.Monster;
import game.model.attack.Attack;
import game.model.attack.AttackResult;


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
        AttackResult result = a.doAttack(myPlayerParty.getCurrentMonster(), myEnemyParty.getCurrentMonster());
        if (result.isHit()) {
            myMode.markEnemyMonsterHit();
        }
        myMode.pushState(new StateTransitionTextOptionState(myMode, result.toString()));
    }

    public void attackPlayer (Attack a) {
        AttackResult result = a.doAttack(myEnemyParty.getCurrentMonster(), myPlayerParty.getCurrentMonster());
        if (result.isHit()) {
            myMode.markPlayerMonsterHit();
        }
        myMode.pushState(new UserStateTransitionTextOptionState(myMode, result.toString()));

    }
    
    public void handleMonsterDeaths () {
        if (myPlayerParty.getCurrentMonster().isDead()) {
            handleUserMonsterDied();
        }
        if (myEnemyParty.getCurrentMonster().isDead()){
            handleEnemyMonsterDied();
        }
    }

    private void handleUserMonsterDied () {
        System.out.println("Handling user monster");
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
        LevelChange change =
                myPlayerParty.getCurrentMonster().addExperience(myEnemyParty.getCurrentMonster()
                                                                        .getRewardExperience());
        if (myEnemyParty.getNumberOfAliveMonsters() == 0) {
            userWon();
        }
        else {
            myEnemyParty.chooseRandomNextMonster();
        }
        if (change == LevelChange.BOTH || change == LevelChange.LEVEL_UP) {
            myMode.pushState(new TextOptionState(myMode, Constants.BATTLE_LEVEL_UP));
        }
        if (change == LevelChange.BOTH || change == LevelChange.EVOLUTION) {
            myMode.pushState(new TextOptionState(myMode, Constants.BATTLE_EVOLVE));
        }
        myMode.pushState(new TextOptionState(myMode, Constants.BATTLE_KILLED_ENEMY_MONSTER));
        myIsUsersTurn = true;
    }

    private void userLost () {
        myMode.setOptionState(myMode.getBattleCompleteState(false));
    }

    private void userWon () {
        if (myMode instanceof TrainerBattleMode) {
            ((FightingNPC) myEnemyParty.getFighter()).setDefeated(true);
        }
        myMode.setOptionState(myMode.getBattleCompleteState(true));
    }

    public boolean isOver () {
        return myEnemyParty.getNumberOfAliveMonsters() + myPlayerParty.getNumberOfAliveMonsters() == 0;
    }

    public boolean caughtWildMonster () {
        double randomFactor = CATCH_MIN + Math.random() * (CATCH_MAX - CATCH_MIN);
        double probability = ((WildMonsterParty) myEnemyParty).getCatchProbability() * randomFactor;
        return (Math.random() < probability);
    }

    public void doNextTurn () {
        toggleUsersTurn();
        handleMonsterDeaths();
        if(!myIsUsersTurn) {
            getEnemyParty().doTurn();
        }
    }

    private void toggleUsersTurn () {
        myIsUsersTurn = !myIsUsersTurn;
    }
}
