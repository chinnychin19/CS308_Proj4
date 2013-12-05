package game.model.battle;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.optionState.LivingPartyOptionState;
import game.controller.optionState.UserLostWildBattleCompleteState;
import game.controller.optionState.UserWonWildBattleCompleteState;
import game.controller.state.option.MainOptionState;
import game.controller.state.option.TextOptionState;
import game.controller.state.option.StateTransitionTextOptionState;
import game.model.StateChange;
import game.model.attack.Attack;




public class Battle {
    AbstractBattleParty myPlayerParty;
    AbstractBattleParty myEnemyParty;
    AbstractBattleMode myMode;
    private static final double CATCH_MIN = 0.95;
    private static final double RANDOM_FACTOR = CATCH_MIN + Math.random() * (0.99 - CATCH_MIN);
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

    public void setNextPlayerAttack (Attack a) {
        // TODO: make abstract player party? extend for wild and trainer battles?
        ((WildPlayerParty) myPlayerParty).setNextAttack(a);
    }

    public void attackEnemy (Attack a) {
        double damage = a.doAttack(myPlayerParty.getCurrentMonster(), myEnemyParty.getCurrentMonster());
        myMode.pushState(new StateTransitionTextOptionState(myMode, String.format("%s did %f damage", myPlayerParty.getCurrentMonster().getName(), damage), this));
    }

    public void attackPlayer (Attack a) {
        double damage = a.doAttack(myEnemyParty.getCurrentMonster(), myPlayerParty.getCurrentMonster());
        myMode.pushState(new TextOptionState(myMode, String.format("%s did %f damage", myEnemyParty.getCurrentMonster().getName(), damage)));
    }
    
    private boolean checkNoMonstersDiedOnTurn () {
        boolean shouldContinue = true;
        System.out.println("checking Monsters");
        // check if battle is over first
        
        if (myPlayerParty.getNumberOfAliveMonsters() == 0 ) {
            userLost();
            shouldContinue = false;
        }

        if (myEnemyParty.getNumberOfAliveMonsters() == 0) {
            userWon();
            shouldContinue = false;
        }
        
        if (myPlayerParty.getCurrentMonster().isDead()) {
            handleUserMonsterDied();
        }
        
        if (myEnemyParty.getCurrentMonster().isDead()){
            handleWildMonsterDied();
        }
                
        return shouldContinue;
    }

    private void handleUserMonsterDied () {
        myMode.setOptionState(new TextOptionState(myMode, Constants.PROMPT_MONSTER_DEAD,
                                            new LivingPartyOptionState(myMode, false)));
    }

    private void handleWildMonsterDied () {
        System.out.println("Wild Monster Died");
        StateChange state = myPlayerParty.getCurrentMonster().addExperience(myEnemyParty.getCurrentMonster().getRewardExperience());
        System.out.println(state);
        switch(state){
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
        myMode.setOptionState(new UserLostWildBattleCompleteState(myMode));
    }

    private void userWon () {
        myMode.setOptionState(new UserWonWildBattleCompleteState(myMode));
    }

    public boolean isOver () {
        return myEnemyParty.getNumberOfAliveMonsters() + myPlayerParty.getNumberOfAliveMonsters() == 0;
    }

    public boolean caughtWildMonster () {
        WildMonsterParty wildMonster = (WildMonsterParty) myEnemyParty;
        double probability = wildMonster.calculateCatchProbability() * RANDOM_FACTOR;
        if (Math.random() <= probability) {
            acquireWildMonster();
            return true;
        }
        return false;
    }

    public void acquireWildMonster () {
        myMode.getModel().getPlayer().addMonsterToParty(myEnemyParty.getCurrentMonster());
    }

    public void doNextTurn () {
        // TODO Auto-generated method stub
        toggleUsersTurn();
        checkNoMonstersDiedOnTurn();
        if(!myIsUsersTurn){
            getEnemyParty().doTurn();
        }
    }
    
    private void toggleUsersTurn(){
        myIsUsersTurn = !myIsUsersTurn;
    }
}
