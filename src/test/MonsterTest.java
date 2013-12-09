package test;

import org.junit.Test;
import constants.Constants;
import game.model.Monster;
import game.model.Type;


public class MonsterTest extends AbstractModelTest {
    protected Monster myMonster;

    @Override
    protected void setUp () {
        super.setUp();
        myMonster = myPlayer.getParty().get(0);
    }

    @Test
    public void testInit () {
        assertEquals("tyler", myMonster.getName());
        assertEquals(0.4, myMonster.getCatchRate(), .001);
        assertEquals(new Type("water"), myMonster.getType());
        assertEquals(2, myMonster.getMyAttacks().size());
    }

    @Test
    public void testChangeHealth () {
        int curHP = myMonster.getStat(Constants.STAT_CUR_HP);
        myMonster.changeHealth(-10);
        assertEquals(curHP-10, myMonster.getStat(Constants.STAT_CUR_HP));
    }
    
    @Test
    public void testIsDead () {
        assertEquals(false, myMonster.isDead());
        myMonster.changeHealth(-1*myMonster.getStat(Constants.STAT_MAX_HP));
        assertEquals(true, myMonster.isDead());
    }
}
