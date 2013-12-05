package game.model.battle;

import constants.Constants;
import game.controller.AbstractBattleMode;
import game.controller.optionState.LivingPartyOptionState;
import game.controller.state.option.MainOptionState;
import game.controller.optionState.UserLostWildBattleCompleteState;
import game.controller.optionState.UserWonWildBattleCompleteState;
import game.controller.state.option.TextOptionState;
import game.model.Monster;
import game.model.StateChange;
import game.model.attack.Attack;


public class Battle {
    AbstractBattleParty myPlayerParty;
    AbstractBattleParty myEnemyParty;
    AbstractBattleMode myMode;
    private static final double CATCH_MIN = 0.80;
    private static final double CATCH_MAX = 0.99;

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
        myMode.setOptionState(new MainOptionState(myMode));
        if (checkNoMonstersDiedOnTurn()) {
            myEnemyParty.doTurn();
            if (checkNoMonstersDiedOnTurn()) {
                myMode.setOptionState(new MainOptionState(myMode));
            }
        }

    }

    private boolean checkNoMonstersDiedOnTurn () {
        boolean shouldContinue = true;
        System.out.println("checking Monsters");
        // check if battle is over first

        if (myPlayerParty.getNumberOfAliveMonsters() == 0) {
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

        if (myEnemyParty.getCurrentMonster().isDead()) {
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
        StateChange state =
                myPlayerParty.getCurrentMonster().addExperience(myEnemyParty.getCurrentMonster()
                        .getRewardExperience());
        System.out.println(state);
        switch (state) {
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
        double randomFactor = CATCH_MIN + Math.random() * (CATCH_MAX - CATCH_MIN);
        double probability = ((WildMonsterParty) myEnemyParty).getCatchProbability()*randomFactor;
        System.out.print(probability);
        if (Math.random() < probability) {
            acquireWildMonster();
            return true;
        }
        return false;

        // acquireWildMonster();
        // return true;
    }

    public void acquireWildMonster () {
        myMode.getModel().getPlayer().getParty().add(myEnemyParty.getCurrentMonster());
    }

}
