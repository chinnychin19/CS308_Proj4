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


/**
 * Functionality associated with the model of a game
 * @author tylernisonoff, rtoussaint
 *
 */
public class GameModel {
    private GameController myController;
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
        myStateSaver = new StateSaver(this, myWorld, nameOfGame);
    }
    
    /**
     * Read in the type matrix that was created and saved by the authoring enviornment
     */
    private void loadTypeMatrix() {
        try {
            SmartJsonObject matrixDefinition = myDefinitionCache.getInstance(Constants.TYPE_MATRIX, Constants.MATRIX);
            myTypeMatrix = new TypeMatrix(this, matrixDefinition);
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the type matrix for this model
     * @return type matrix for this model
     */
    public TypeMatrix getTypeMatrix() {
        return myTypeMatrix;
    }
    
    /**
     * Gets the definition cache for this model
     * @return the defintion cache 
     */
    public JSONCache getDefinitionCache() {
        return myDefinitionCache;
    }
    /**
     * Gets the controller for this model
     * @return controller for this model
     */
    public GameController getController() {
        return myController;
    }
    /**
     * Gets the player for this model
     * @return the player
     */
    public Player getPlayer () {
        return myWorld.getPlayer();
    }
    /**
     * load the state of the game
     */
    public void loadState() {
        try {
            myWorld = myStateSaver.load();
        }
        catch (Exception e) {
        	//TODO: handle exception
            // Save state file was not found. This will happen the first time, so do nothing.
        }
    }
    /**
     * Save the state of the game
     */
    public void saveState() {
        try {
            myStateSaver.save();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Get the viewable object for this model
     * @param loc the location of the viewable object
     * @return the viewable object a specific location
     */
    public AbstractViewableObject getViewableObject (Loc loc) {
        return myWorld.getViewableObject(loc);
    }
    /**
     * Get the ground for this model
     * @param loc the location of the ground
     * @return the ground for this location
     */
    public AbstractGround getGroundObject (Loc loc) {
        return myWorld.getGroundObject(loc);
    }
    
    //TODO: delete this?
//    public Map<Loc, AbstractViewableObject> getViewableObjects () {
//        return myWorld.getViewableObjects();
//    }
    /**
     * Get the world of this model
     * @return the world for this model
     */
    public World getWorld() {
        return myWorld;
    }
}
