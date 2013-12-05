package game.model;

import game.controller.AbstractBattleMode;

public enum StateChange {
    
    NONE, LEVEL_UP, EVOLUTION, BOTH;
    
    public static StateChange getStateChange(boolean level, boolean evolution){
        if(level){
            if(evolution){
                return BOTH;
            }
            return LEVEL_UP;
        } else{
            if(evolution){
                return EVOLUTION;
            }
            return NONE; 
        }
    }
}
