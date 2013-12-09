package game.model.battle;

import constants.Constants;
import game.model.Monster;

public class TemporaryStatistics {
    private int myMaxHP;
    private int myAttack;
    private int myDefense;
    private Monster myMonster;
    public TemporaryStatistics(Monster m){
        myMaxHP = m.getStat(Constants.STAT_MAX_HP);
        myAttack = m.getStat(Constants.STAT_ATTACK);
        myDefense = m.getStat(Constants.STAT_DEFENSE);
        myMonster = m;
    }
    
    public void reapply(){
        myMonster.changeStat(Constants.STAT_MAX_HP, myMaxHP - myMonster.getStat(Constants.STAT_MAX_HP));
        myMonster.changeStat(Constants.STAT_ATTACK, myAttack - myMonster.getStat(Constants.STAT_ATTACK));
        myMonster.changeStat(Constants.STAT_DEFENSE, myDefense - myMonster.getStat(Constants.STAT_DEFENSE));
    }
    
    public void boostHP(int hp){
        myMaxHP += hp;
    }
    
    public void boostAttack(int attack){
        myAttack += attack;
    }
    public void boostDefense(int defense){
        myDefense += defense;
    }
}
