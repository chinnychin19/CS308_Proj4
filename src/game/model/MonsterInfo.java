package game.model;

import constants.Constants;
import game.controller.state.Listable;

public class MonsterInfo implements Listable{
    Monster myMonster;
    public MonsterInfo (Monster m){
        myMonster = m;
    }
    @Override
    public String getName () {
        return myMonster.getName()+"\t HP:"+myMonster.getStat(Constants.STAT_CUR_HP)+"/"+myMonster.getStat(Constants.STAT_MAX_HP);
    }
    
    
}
