package game.model.battle;

import game.controller.AbstractBattleMode;
import game.controller.optionState.BattleOverState;
import game.controller.optionState.MainOptionState;
import game.model.Monster;
import game.model.attack.Attack;

public class Battle {
    AbstractBattleParty myPlayerParty;
    AbstractBattleParty myEnemyParty;
    AbstractBattleMode myMode;
    public Battle(AbstractBattleParty playerParty, AbstractBattleParty enemyParty, AbstractBattleMode mode) {
        myPlayerParty = playerParty;
        myEnemyParty = enemyParty;
        myMode = mode;
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
    
    public void attackEnemy(Attack a){
        a.doAttack( myPlayerParty.getCurrentMonster(), myEnemyParty.getCurrentMonster());
    }
    
    public void attackPlayer(Attack a){
        a.doAttack( myEnemyParty.getCurrentMonster(),myPlayerParty.getCurrentMonster());
    }
    public void registerUserCompleted(){
        if(myEnemyParty.getNumberOfAliveMonsters() == 0){
            System.out.println("=============\nYOU WON!\n=============");
            userWon();
        } else{
            myEnemyParty.doTurn();
            System.out.println("health: "+myPlayerParty.getCurrentMonster().getCurHP());
            if(myPlayerParty.getNumberOfAliveMonsters() == 0){
                computerWon();
            } else{
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
