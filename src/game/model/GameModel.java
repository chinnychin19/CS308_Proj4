package game.model;

import game.controller.AbstractMode;
import java.io.IOException;
import java.util.Map;
import constants.Constants;
import jsoncache.JSONCache;
import jsoncache.JSONReader;
import location.Direction;
import location.Loc;


public class GameModel {
    private Player myPlayer;
    private World myWorld;
    private StateSaver myStateSaver;
    private JSONCache myDefinitionCache;

    public GameModel (String nameOfGame) throws Exception {
        String definitionJSONFilepath =
                Constants.FOLDERPATH_GAMES + "/" + nameOfGame + "/" +
                        Constants.FILENAME_DEFINITION;
        myDefinitionCache = new JSONCache(JSONReader.getJSON(definitionJSONFilepath));
        myWorld = new World(nameOfGame, this);
        myPlayer = myWorld.getPlayer();
        myStateSaver = new StateSaver(myWorld, nameOfGame);
    }
    
    public JSONCache getDefinitionCache() {
        return myDefinitionCache;
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

    public Map<Loc, AbstractViewableObject> getViewableObjects () {
        return myWorld.getViewableObjects();
    }
    
    public World getWorld() {
        return myWorld;
    }

//    public void doInteraction () {
//        myWorld.doInteraction();
//    }
//    
//    public void doMove (Direction d) {
//        myWorld.movePlayer(d);
//    }
}
