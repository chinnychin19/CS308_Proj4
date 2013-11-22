package game.model;

import game.model.attack.Attack;
import java.util.HashMap;
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


public class World {
    
    private HashMap<Loc, AbstractViewableObject> myViewableObjects;
    private HashMap<Loc, AbstractGround> myGroundObjects;
    private Player myPlayer;
    private GameModel myModel;
    private JSONObject myWorldJSON;
    private String myNameOfGame;

    public World (String nameOfGame, GameModel model) throws Exception {
        myNameOfGame = nameOfGame;
        myViewableObjects = new HashMap<Loc, AbstractViewableObject>();
        myGroundObjects = new HashMap<Loc, AbstractGround>();
        String worldJSONFilepath = Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                Constants.FILENAME_WORLD;
        myModel = model;
        myWorldJSON = JSONReader.getJSON(worldJSONFilepath);
        setUpWorld();
    }

    protected Player getPlayer () {
        return myPlayer;
    }

    public void addViewable (AbstractViewable obj) {
        if (obj.canStepOn()) {
            myGroundObjects.put(obj.getLoc(), (AbstractGround) obj);
        } else {
            myViewableObjects.put(obj.getLoc(), (AbstractViewableObject) obj);            
        }
    }

    protected AbstractViewableObject getViewableObject (Loc loc) {
        return myViewableObjects.get(loc);
    }
    
    protected AbstractGround getGroundObject (Loc loc) {
        return myGroundObjects.get(loc);
    }
    
    protected boolean isLocOccupied(Loc loc) {
        return null != myViewableObjects.get(loc);
    }
    
    protected Loc locInFrontOfPlayer() {
        return myPlayer.getLoc().adjacentLoc(myPlayer.getDirection());
    }
    
    protected void setUpWorld () throws Exception {
        for (String viewableCategory : Constants.VIEWABLE_CATEGORIES) {
            JSONArray objectArray = (JSONArray) myWorldJSON.get(viewableCategory);
//            debug("Category: "+viewableCategory);
            for (Object obj : objectArray) {
                SmartJsonObject objInWorld = new SmartJsonObject((JSONObject) obj);
//                debug("Name: " + objInWorld.get(Constants.JSON_NAME));
                SmartJsonObject definition =
                        myModel.getDefinitionCache().
                                getInstance(viewableCategory, objInWorld.getString(Constants.JSON_NAME));
                String classPath = Constants.CLASSPATH_GAME_MODEL + "." + viewableCategory; 
                AbstractViewableObject newViewableObject = 
                        (AbstractViewableObject) Reflection.createInstance(classPath,
                                                                           this,
                                                                           definition,
                                                                           objInWorld);
                addViewable(newViewableObject);
                if (viewableCategory.equals(Constants.JSON_PLAYER)) {
                    myPlayer = (Player) newViewableObject;
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
}
