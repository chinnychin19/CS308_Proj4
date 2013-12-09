package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import location.Direction;
import location.Loc;
import game.model.FightingNPC;
import game.model.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import reflection.Reflection;
import util.jsonwrapper.SmartJsonObject;
import constants.Constants;


public class FightingNPCTest extends AbstractModelTest {
    FightingNPC myFightingNPC;

    @Override
    protected void setUp () {
        try {
            super.setUp();
            SmartJsonObject myFightingNPCJSON =
                    new SmartJsonObject(
                                        (JSONObject) ((JSONArray) myWorldJSON
                                                .get(Constants.JSON_FIGHTING_NPC)).get(1));
            myFightingNPC =
                    new FightingNPC(myModel, myWorld, myModel.getDefinitionCache()
                            .getInstance(Constants.JSON_FIGHTING_NPC,
                                         "fighter1"),
                                    myFightingNPCJSON);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInit () {
        assertEquals("fighter1", myFightingNPC.getName());
        assertEquals(5, myFightingNPC.getLineOfSightDistance());
        assertEquals(Direction.LEFT, myFightingNPC.getDirection());
        assertEquals(-3, myFightingNPC.getLoc().getX());
        assertEquals(6, myFightingNPC.getLoc().getY());
    }

    @Test
    public void testCheckLineOfSight () {
        try {
            myFightingNPC.setDefeated(false);
            myFightingNPC.setLoc(new Loc(0, 0), myWorld);
            myPlayer.setLoc(new Loc(-1, 0), myWorld);
            String methodName = "checkLineOfSight";
            Method method;

            method = myFightingNPC.getClass().getDeclaredMethod(methodName);

            method.setAccessible(true);
            assertEquals(true,
                         method.invoke(myFightingNPC));
        }
        catch (SecurityException e) {
            e.printStackTrace();
            fail();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            fail();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
            fail();
        }
    }

}
