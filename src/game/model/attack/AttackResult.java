package game.model.attack;

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
    
    @Override
    public String toString() {
        String ret = "";
        ret += String.format("%s used %s. ", myMonsterName, myAttackName);
        if (myIsHit) {
            if (Math.abs(myEffectiveness - 1) > 0.001) { // multiplier != 1
                if (myEffectiveness > 1) {
                    ret += "The matchup is super effective! ";
                } else {
                    ret += "The matchup is not very effective. ";
                }
            }
            ret += String.format("The attack did %.0f damage. ", myDamage);
        } else {
            ret += "The attack missed! ";
        }
        return ret;
    }
}
