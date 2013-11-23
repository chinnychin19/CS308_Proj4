package game.model;

import game.controller.AbstractMode;
import game.controller.GameController;
import java.io.IOException;
import java.util.Map;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
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
    private TypeMatrix myTypeMatrix;

    public GameModel (String nameOfGame, GameController controller) throws Exception {
        myController = controller;
        String definitionJSONFilepath =
                Constants.FOLDERPATH_GAMES + "/" + nameOfGame + "/" +
                        Constants.FILENAME_DEFINITION;
        myDefinitionCache = new JSONCache(JSONReader.getJSON(definitionJSONFilepath));
        loadTypeMatrix();
        myWorld = new World(nameOfGame, this);
        myPlayer = myWorld.getPlayer();
        myStateSaver = new StateSaver(this, myWorld, nameOfGame);
    }
    
    private void loadTypeMatrix() {
        try {
            SmartJsonObject matrixDefinition = myDefinitionCache.getInstance(Constants.TYPE_MATRIX, Constants.MATRIX);
            myTypeMatrix = new TypeMatrix(this, matrixDefinition);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    public TypeMatrix getTypeMatrix() {
        return myTypeMatrix;
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
        	//TODO: handle exception
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
    
    //TODO: delete this?
//    public Map<Loc, AbstractViewableObject> getViewableObjects () {
//        return myWorld.getViewableObjects();
//    }
    
    public World getWorld() {
        return myWorld;
    }
}
