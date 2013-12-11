package game.model;

import constants.Constants;

public enum Consciousness {
    DEAD, ALIVE, BOTH;
    
    public static Consciousness fromString(String s){
        if(Constants.ALIVE_STRING.equals(s)){
            return ALIVE;
        }
        if(Constants.DEAD_STRING.equals(s)){
            return DEAD;
        }
        return BOTH;
    }
}
