package game.model;

import game.controller.AbstractBattleMode;

public enum LevelChange {
    
    NONE, LEVEL_UP, EVOLUTION, BOTH;
    
    public static LevelChange getStateChange(boolean level, boolean evolution){
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
