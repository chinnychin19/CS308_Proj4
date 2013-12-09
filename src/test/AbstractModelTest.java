package test;

import game.controller.GameController;
import game.model.GameModel;
import game.model.Player;
import game.model.World;
import game.view.GameView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import constants.Constants;
import jsoncache.JSONReader;
import junit.framework.TestCase;


public class AbstractModelTest extends TestCase {
    protected GameModel myModel;
    protected World myWorld;
    protected GameController myController;
    protected GameView myView;
    protected JSONObject myWorldJSON;
    protected final static String GAME_NAME = "testGame";
    protected Player myPlayer;
    @Override
    protected void setUp () {
        try {

            myView = new GameView(GAME_NAME);
            myController = new GameController(GAME_NAME, myView);

            myModel = new GameModel(GAME_NAME, myController);
            myWorld = new World(GAME_NAME, myModel);
            myWorldJSON =
                    JSONReader.getJSON(Constants.FOLDERPATH_GAMES + "/" + GAME_NAME + "/" +
                                       "saveState2.json");
            SmartJsonObject playerJSON =
                    new SmartJsonObject((JSONObject) ((JSONArray) myWorldJSON.get(Constants.JSON_PLAYER)).get(0));
            myPlayer = myModel.getPlayer();
                   // new Player(myModel, myWorld, myModel.getDefinitionCache().getInstance("Player",
                           //                                                               "hero"),
                             //  playerJSON);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
