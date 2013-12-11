package game.model.attack;

import constants.Constants;

public class AttackResult {
    private String myMonsterName;
    private String myAttackName;
    private double myDamage;
    private double myEffectiveness;
    private boolean myIsHit; // "is hit," NOT "I shit"
    
    public AttackResult(String monsterName, String attackName, double damage, 
                        double effectiveness, boolean isHit) {
        myMonsterName = monsterName;
        myAttackName = attackName;
        myDamage = damage;
        myEffectiveness = effectiveness;
        myIsHit = isHit;
    }
    
    public boolean isHit() {
        return myIsHit;
    }
    
    @Override
    public String toString() {
        String ret = Constants.BLANK_STRING;
        ret += String.format("%s used %s. ", myMonsterName, myAttackName);
        if (myIsHit) {
            if (Math.abs(myEffectiveness - 1) > 0.001) { // multiplier != 1
                if (myEffectiveness > 1) {
                    ret += Constants.MATCHUP_EFFECTIVE;
                } else {
                    ret += Constants.MATCHUP_NOT_EFFECTIVE;
                }
            }
            ret += String.format(Constants.PROMPT_ATTACK_DAMAGE_DID, myDamage);
        } else {
            ret += Constants.PROMPT_ATTACK_MISSED;
        }
        return ret;
    }
}
