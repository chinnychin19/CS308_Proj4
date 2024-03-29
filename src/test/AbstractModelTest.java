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


public abstract class AbstractModelTest extends TestCase {
	protected GameModel myModel;
	protected World myWorld;
	protected GameController myController;
	protected GameView myView;
	protected JSONObject myWorldJSON;
	protected final static String GAME_NAME = "bogusNameOfGame";
	protected final static String SESSION  = "saveState_tyler.json";
	protected Player myPlayer;
	@Override
	protected void setUp () {
		try {
		
            myView = new GameView(GAME_NAME,SESSION );
            myController = new GameController(GAME_NAME,SESSION, myView);

            myModel = new GameModel(GAME_NAME,SESSION, myController);
            myWorld = new World(GAME_NAME, SESSION, myModel);
            myWorldJSON =
                    JSONReader.getJSON(Constants.FOLDERPATH_GAMES + "/" + GAME_NAME + "/" +
                                       SESSION);
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
