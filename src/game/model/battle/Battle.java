package game.model.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Map<Monster, TemporaryStatistics> myTemporaryStatistics;
    public Battle (AbstractBattleParty playerParty,
                   AbstractBattleParty enemyParty,
                   AbstractBattleMode mode) {
        myPlayerParty = playerParty;
        myEnemyParty = enemyParty;
        myMode = mode;
        myIsUsersTurn = true;
        myTemporaryStatistics = new HashMap<Monster, TemporaryStatistics>();
        for(Monster m : myPlayerParty.getMonsters()){
            myTemporaryStatistics.put(m,new TemporaryStatistics(m));
        }
        for(Monster m : myEnemyParty.getMonsters()){
            myTemporaryStatistics.put(m,new TemporaryStatistics(m));
        }
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
        myMode.setOptionState(new StateTransitionTextOptionState(myMode, result.toString()));
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
        System.out.println(Constants.TEXT_HANDLING_USER_MOSNTER);
        if (myPlayerParty.getNumberOfAliveMonsters() == 0) {
            userLost();
        }
        else {
            myMode.setOptionState(new TextOptionState(myMode, Constants.PROMPT_MONSTER_DEAD,
                                                      new LivingPartyOptionState(myMode, false)));
        }
    }
    private void handleEnemyMonsterDied () {
        myIsUsersTurn = true;
        LevelChange change =
                myPlayerParty.getCurrentMonster().addExperience(myEnemyParty.getCurrentMonster()
                                                                        .getRewardExperience(), myTemporaryStatistics.get(myPlayerParty.getCurrentMonster()));
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
        healEnemyParty();
        reapplyStatistics();
        myMode.setOptionState(myMode.getBattleCompleteState(false));
    }

    private void userWon () {
        if (myMode instanceof TrainerBattleMode) {
            ((FightingNPC) myEnemyParty.getFighter()).setDefeated(true);
        }
        reapplyStatistics();
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
        if (myIsUsersTurn) {
            Monster playerMonster = myPlayerParty.getCurrentMonster();
            playerMonster.getStatus().doStatus(playerMonster);
            System.out.println("Status: "+ playerMonster.getStatus());            
        }
        toggleUsersTurn();
        handleMonsterDeaths();
        if(!myIsUsersTurn){
            getEnemyParty().doTurn();
            Monster enemyMonster = getEnemyParty().getCurrentMonster(); 
            enemyMonster.getStatus().doStatus(enemyMonster);
        }
    }

    private void toggleUsersTurn () {
        myIsUsersTurn = !myIsUsersTurn;
    }
    
    public void reapplyStatistics(){
        System.out.println("reapplying statistics");
        for(TemporaryStatistics temp : myTemporaryStatistics.values()){
            temp.reapply();
        }
    }
    
    private void healEnemyParty(){
        for(Monster m : myEnemyParty.getMonsters()){
            m.heal();
        }
    }
}
