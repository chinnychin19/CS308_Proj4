package game.model;

import game.model.attack.Attack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jsoncache.JSONCache;
import jsoncache.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import reflection.Reflection;
import util.jsonwrapper.SmartJsonObject;
import constants.Constants;
import location.Direction;
import location.Loc;

/**
 * Represents the World in wandering Mode
 * @author tylernisonoff
 *
 */
public class World {
    
    private HashMap<Loc, AbstractViewableObject> myViewableObjects;
    private HashMap<Loc, AbstractGround> myGroundObjects;
    private Player myPlayer;
    private GameModel myModel;
    private JSONObject myWorldJSON;
    private String myNameOfGame;
    private String mySession;
    public World (String nameOfGame, String session, GameModel model) throws Exception {
        myNameOfGame = nameOfGame;
        mySession = session;
        myViewableObjects = new HashMap<Loc, AbstractViewableObject>();
        myGroundObjects = new HashMap<Loc, AbstractGround>();
        String worldJSONFilepath = Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                mySession;
        myModel = model;
        myWorldJSON = JSONReader.getJSON(worldJSONFilepath);
        setUpWorld(myWorldJSON);
    }
    
    /**
     * 
     * @return the main Player
     */
    protected Player getPlayer () {
        return myPlayer;
    }
    
    /**
     * Adds a viewable entity to the world.
     * @param obj - object to be added
     */
    public void addViewable (AbstractViewable obj) {
        if (obj.canStepOn()) {
            myGroundObjects.put(obj.getLoc(), (AbstractGround) obj);
        } else {
            myViewableObjects.put(obj.getLoc(), (AbstractViewableObject) obj);            
        }
    }
    
    /**
     *  
     * @param Location of the object you want to get
     * @return - a Viewable Object for a given location
     */
    protected AbstractViewableObject getViewableObject (Loc loc) {
        return myViewableObjects.get(loc);
    }
    
    /**
     *  
     * @param loc - Location of the object you want to get
     * @return - Returns the round object for the given location
     */
    protected AbstractGround getGroundObject (Loc loc) {
        return myGroundObjects.get(loc);
    }
    
    /**
     * 
     * @param loc - Location you are querying on
     * @return true if occupied by a viewable object, else false
     */
    protected boolean isLocOccupied(Loc loc) {
        return null != myViewableObjects.get(loc);
    }
    
    /**
     * 
     * @return The Location in front of the player based on his orientation
     */
    protected Loc locInFrontOfPlayer() {
        return myPlayer.getLoc().adjacentLoc(myPlayer.getDirection());
    }
    
    private void resetWorld() {
        myViewableObjects.clear();
        myGroundObjects.clear();
    }
    
    /**
     * Creates the world from JSON
     * Uses reflection to figure out which classes to instantiate
     * @throws Exception - if file not found
     */
    protected void setUpWorld (JSONObject worldJSON) throws Exception {
        resetWorld();
        for (String viewableCategory : Constants.VIEWABLE_CATEGORIES) {
            JSONArray objectArray = (JSONArray) (worldJSON.get(viewableCategory));
//            debug("Category: "+viewableCategory);
            for (Object obj : objectArray) {
                SmartJsonObject objInWorld = new SmartJsonObject((JSONObject) obj);
                debug("Name: " + objInWorld.getString(Constants.JSON_NAME));
                SmartJsonObject definition =
                        myModel.getDefinitionCache().
                                getInstance(viewableCategory, objInWorld.getString(Constants.JSON_NAME));
                String classPath = Constants.CLASSPATH_GAME_MODEL + "." + viewableCategory; 
                AbstractViewable newViewable =
                        (AbstractViewable) Reflection.createInstance(classPath, myModel, this,
                                                                     definition, objInWorld);
                addViewable(newViewable);
                if (viewableCategory.equals(Constants.JSON_PLAYER)) {
                    if(myPlayer != null){
                        myViewableObjects.remove(myPlayer.getLoc());
                        addViewable((Player)newViewable);
                    }
                    myPlayer = (Player) newViewable;
                }
            }
        }
    }
    
    private void debug(Object o) {
        System.out.println(o.toString());
    }

    protected void removeObject(Loc loc) {
        myViewableObjects.remove(loc);
    }
    
    public List<Saveable> getAllNPCs(){
        List<Saveable> NPCs = new ArrayList<Saveable>();
        for(Loc l : myViewableObjects.keySet()){
            AbstractViewableObject viewable = myViewableObjects.get(l);
            if((viewable != null) && (viewable instanceof NPC) && !(viewable instanceof FightingNPC)){
                NPCs.add((NPC)viewable);
            }
        }
        return NPCs;
    }
    
    public List<Saveable> getAllFightingNPCs(){
        List<Saveable> fightingNPCs = new ArrayList<Saveable>();
        for(Loc l : myViewableObjects.keySet()){
            AbstractViewableObject viewable = myViewableObjects.get(l);
            if((viewable != null) && (viewable instanceof FightingNPC) ){
                fightingNPCs.add((FightingNPC)viewable);
            }
        }
        return fightingNPCs;
    }
    
    public List<Saveable> getAllObstacles(){
        List<Saveable> obstacles = new ArrayList<Saveable>();
        for(Loc l : myViewableObjects.keySet()){
            AbstractViewableObject viewable = myViewableObjects.get(l);
            if((viewable != null) && (viewable instanceof Obstacle) ){
                obstacles.add((Obstacle)viewable);
            }
        }
        return obstacles;
    }
    
    public List<Saveable> getAllHealItems(){
        List<Saveable> healItems = new ArrayList<Saveable>();
        for(Loc l : myViewableObjects.keySet()){
            AbstractViewableObject viewable = myViewableObjects.get(l);
            if((viewable != null) && (viewable instanceof HealItem) ){
                healItems.add((HealItem)viewable);
            }
        }
        return healItems;
    }
    
    public List<Saveable> getAllWildRegions(){
        List<Saveable> healItems = new ArrayList<Saveable>();
        for(Loc l : myGroundObjects.keySet()){
            AbstractGround viewable = myGroundObjects.get(l);
            if((viewable != null) && (viewable instanceof WildRegion) ){
                healItems.add((WildRegion)viewable);
            }
        }
        return healItems;
    }
}
