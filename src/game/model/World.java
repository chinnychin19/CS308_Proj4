package game.model;

import java.util.HashMap;
import java.util.Map;
import jsoncache.JSONCache;
import jsoncache.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import reflection.Reflection;
import constants.Constants;
import location.Direction;
import location.Loc;


public class World {
    private HashMap<Loc, AbstractViewableObject> myViewableObjects;

    private Player myPlayer;
    private JSONCache myDefinitionCache;
    private JSONObject myWorldJSON;
    private String myNameOfGame;

    public World (String nameOfGame) throws Exception {
        myNameOfGame = nameOfGame;
        myViewableObjects = new HashMap<Loc, AbstractViewableObject>();
        String definitionJSONFilepath =
                Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                        Constants.FILENAME_DEFINITION;
        String worldJSONFilepath = Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                Constants.FILENAME_WORLD;
        myDefinitionCache = new JSONCache(JSONReader.getJSON(definitionJSONFilepath));
        myWorldJSON = JSONReader.getJSON(worldJSONFilepath);
        setUpWorld();
    }

    protected Player getPlayer () {
        return myPlayer;
    }

    private void addViewableObject (AbstractViewableObject obj) {
        myViewableObjects.put(obj.getLoc(), obj);
    }

    protected Map<Loc, AbstractViewableObject> getViewableObjects () {
        return myViewableObjects;
    }

    protected void movePlayer (Direction d) {
        myPlayer.setDirection(d);
        Loc targetLoc = myPlayer.getLoc().adjacentLoc(d);
        if (!myViewableObjects.containsKey(targetLoc)) {
            myPlayer.getLoc().setAdjacentLoc(d); //does not change the reference of the player's Loc
        }
    }
    
    protected void setUpWorld () throws Exception {
        for (String viewableCategory : Constants.VIEWABLE_CATEGORIES) {
            JSONArray objectArray = (JSONArray) myWorldJSON.get(viewableCategory);
//            debug("Category: "+viewableCategory);
            for (Object obj : objectArray) {
                JSONObject objInWorld = (JSONObject) obj;
//                debug("Name: " + objInWorld.get(Constants.JSON_NAME));
                JSONObject definition =
                        myDefinitionCache
                                .getInstance(viewableCategory, objInWorld.get(Constants.JSON_NAME).toString());
                String classPath = Constants.CLASSPATH_GAME_MODEL + "." + viewableCategory; 
                AbstractViewableObject newViewableObject = 
                        (AbstractViewableObject) Reflection.createInstance(classPath,
                                                                           this,
                                                                           definition,
                                                                           objInWorld);
                addViewableObject(newViewableObject);
                if (viewableCategory.equals(Constants.JSON_PLAYER)) {
                    myPlayer = (Player) newViewableObject;
                }
            }
        }
    }
    
    private void debug(Object o) {
        System.out.println(o.toString());
    }

    protected void doInteraction () {
        Loc locInFrontOfPlayer = myPlayer.getLoc().adjacentLoc(myPlayer.getDirection());
        if (myViewableObjects.containsKey(locInFrontOfPlayer)) {
            myViewableObjects.get(locInFrontOfPlayer).doInteraction(myPlayer);
        }
    }
    
    protected void removeObject(Loc loc) {
        myViewableObjects.remove(loc);
    }
}
