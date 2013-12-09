package test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import util.jsonwrapper.SmartJsonObject;
import constants.Constants;
import jsoncache.JSONReader;
import junit.framework.TestCase;
import game.controller.GameController;
import game.model.GameModel;
import game.model.Player;
import game.model.World;
import game.view.GameView;


public class PlayerTest extends AbstractModelTest {
    
    protected Player myPlayer;
    @Override
    protected void setUp () {
        try {
            super.setUp();
            SmartJsonObject playerJSON =
                    new SmartJsonObject((JSONObject) ((JSONArray) myWorldJSON.get("Player")).get(0));
            myPlayer =
                    new Player(myModel, myWorld, myModel.getDefinitionCache().getInstance("Player",
                                                                                          "hero"),
                               playerJSON);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testInit () {
        assertEquals("hero", myPlayer.getName());
        assertEquals("hero", myPlayer.getName());

    }
}
