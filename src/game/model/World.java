package game.model;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import jsoncache.JSONCache;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
        setUpWorld();
    }

    protected Player getPlayer () {
        return myPlayer;

    }

    private void addViewableObject (Loc loc, AbstractViewableObject obj) {
        myViewableObjects.put(loc, obj);
    }

    protected Map<Loc, AbstractViewableObject> getViewableObjects () {
        return myViewableObjects;
    }

    protected void movePlayer (Direction d) {
        myPlayer.setDirection(d);
        Loc targetLoc = myPlayer.getLoc().adjacentLoc(d);
        if (!myViewableObjects.containsKey(targetLoc)) {
            myPlayer.setLoc(targetLoc);
        }
    }

    protected void setUpWorld () throws Exception {
        String definitionJSONFilepath =
                Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                        Constants.FILENAME_DEFINITION;
        String worldJSONFilepath = Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                Constants.FILENAME_WORLD;
        myDefinitionCache = new JSONCache(getJSON(definitionJSONFilepath));
        myWorldJSON = getJSON(worldJSONFilepath);
        for (String viewableCategory : Constants.VIEWABLE_CATEGORIES) {
            JSONArray objectArray = (JSONArray) myWorldJSON.get(viewableCategory);
            debug("Category: "+viewableCategory);
            for (Object obj : objectArray) {
                JSONObject objInWorld = (JSONObject) obj;
                debug("Name: " + objInWorld.get(Constants.JSON_NAME));

                JSONObject definition =
                        myDefinitionCache
                                .getInstance(viewableCategory, objInWorld.get(Constants.JSON_NAME).toString());
                String classPath = Constants.CLASSPATH_GAME_MODEL + "." + viewableCategory; 
                AbstractViewableObject newViewableObject = 
                        (AbstractViewableObject) Reflection.createInstance(classPath,
                                                                           this,
                                                                           definition,
                                                                           objInWorld);
                addViewableObject(newViewableObject.getLoc(), newViewableObject);
                if (viewableCategory.equals("Player")) {
                    myPlayer = (Player) newViewableObject;
                }
            }
        }
    }
    
    private void debug(Object o) {
        System.out.println(o.toString());
    }

    private JSONObject getJSON (String filepath) {
        JSONObject json;
        JSONParser parser = new JSONParser();
        try {
            System.out.println(filepath);
            json = (JSONObject) parser.parse(new FileReader(filepath));
            return json;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
