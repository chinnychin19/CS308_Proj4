package test;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;
import jsoncache.JSONReader;
import junit.framework.TestCase;
import location.Direction;
import location.Loc;
import game.controller.GameController;
import game.model.GameModel;
import game.model.KeyItem;
import game.model.Monster;
import game.model.Player;
import game.model.World;
import game.view.GameView;


public class PlayerTest extends AbstractModelTest {
    
    @Test
    public void testInit () {
        assertEquals("hero", myPlayer.getName());
        assertEquals(2, myPlayer.getItems().size());
        assertEquals(Direction.RIGHT, myPlayer.getDirection());
        assertEquals(4, myPlayer.getParty().size());
        assertEquals("razor", myPlayer.getKeyItems().get(0).getName());
        assertEquals(2, myPlayer.getLoc().getY());
        assertEquals(4, myPlayer.getLoc().getX());
    }

    @Test
    public void testSetLoc () {
        myPlayer.setLoc(new Loc(0, 0), myWorld);
        assertEquals(0, myPlayer.getLoc().getX());
        assertEquals(0, myPlayer.getLoc().getY());
        myPlayer.setLoc(new Loc(10, 11), myWorld);
        assertEquals(10, myPlayer.getLoc().getX());
        assertEquals(11, myPlayer.getLoc().getY());
    }

    @Test
    public void testSaveLoc () {
        myPlayer.setLoc(new Loc(10, 11), myWorld);
        myPlayer.saveThisLoc();
        myPlayer.setLoc(new Loc(9, 10), myWorld);
        myPlayer.goToLastSavedLoc();
        assertEquals(10, myPlayer.getLoc().getX());
        assertEquals(11, myPlayer.getLoc().getY());
    }

    @Test
    public void testHealAllMonsters () {
        for (Monster m : myPlayer.getParty()) {
            m.changeHealth(-50);
        }
        myPlayer.healAllMonsters();
        for (Monster m : myPlayer.getParty()) {
            assertEquals(m.getStat(Constants.STAT_CUR_HP), m.getStat(Constants.STAT_MAX_HP));
        }
    }

    @Test
    public void testSetKeyItem () {
        KeyItem item;
        try {
            item =
                    new KeyItem(myModel, myModel.getDefinitionCache().getInstance(Constants.JSON_KEYITEM,
                                                                                  "razor"));

            List<KeyItem> items = new ArrayList<KeyItem>();
            items.add(item);
            myPlayer.setKeyItems(items);
            assertEquals(1, myPlayer.getKeyItems().size());
            assertEquals("razor", myPlayer.getKeyItems().get(0).getName());
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
            fail();
        }
    }

}
