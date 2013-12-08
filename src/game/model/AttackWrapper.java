package game.model;

import game.model.attack.Attack;

public class AttackWrapper {
    private Attack myAttack;
    private int myUnlockLevel;

    public AttackWrapper (Attack a, int unlockLevel) {
        myAttack = a;
        myUnlockLevel = unlockLevel;
    }

    public Attack getAttack () {
        return myAttack;
    }

    public boolean canUse (int level) {
        return myUnlockLevel <= level;
    }
}
