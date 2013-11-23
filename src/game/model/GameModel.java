package game.model;

import game.controller.AbstractMode;
import game.controller.GameController;
import java.io.IOException;
import java.util.Map;
import constants.Constants;
import jsoncache.JSONCache;
import jsoncache.JSONReader;
import location.Direction;
import location.Loc;


public class GameModel {
    private GameController myController;
    private Player myPlayer;
    private World myWorld;
    private StateSaver myStateSaver;
    private JSONCache myDefinitionCache;

    public GameModel (String nameOfGame, GameController controller) throws Exception {
        myController = controller;
        String definitionJSONFilepath =
                Constants.FOLDERPATH_GAMES + "/" + nameOfGame + "/" +
                        Constants.FILENAME_DEFINITION;
        myDefinitionCache = new JSONCache(JSONReader.getJSON(definitionJSONFilepath));
        myWorld = new World(nameOfGame, this);
        myPlayer = myWorld.getPlayer();
        myStateSaver = new StateSaver(this, myWorld, nameOfGame);
    }
    
    public JSONCache getDefinitionCache() {
        return myDefinitionCache;
    }
    
    public GameController getController() {
        return myController;
    }

    public Player getPlayer () {
        return myPlayer;
    }
    
    public void loadState() {
        try {
            myStateSaver.load();
        }
        catch (Exception e) {
            // Save state file was not found. This will happen the first time, so do nothing.
        }
    }
    
    public void saveState() {
        try {
            myStateSaver.save();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AbstractViewableObject getViewableObject (Loc loc) {
        return myWorld.getViewableObject(loc);
    }
    
    public AbstractGround getGroundObject (Loc loc) {
        return myWorld.getGroundObject(loc);
    }
    
//    public Map<Loc, AbstractViewableObject> getViewableObjects () {
//        return myWorld.getViewableObjects();
//    }
    
    public World getWorld() {
        return myWorld;
    }
}
