package game.model;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import jsoncache.JSONCache;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import reflection.Reflection;
import constants.Constants;
import location.Direction;
import location.Loc;


public class World {
    private HashMap<Loc, AbstractViewableObject> myViewableObjects;

    private Player myPlayer;
    private JSONObject myDefinitionJSON;
    private JSONObject myWorldJSON;
    private String myNameOfGame;

    public World (Player p, String nameOfGame) {
        myNameOfGame = nameOfGame;
        myPlayer = p;
        myViewableObjects = new HashMap<Loc, AbstractViewableObject>();
    }

    public void addViewableObject (Loc loc, AbstractViewableObject obj) {
        myViewableObjects.put(loc, obj);
    }

    public Map<Loc, AbstractViewableObject> getViewableObjects () {
        return myViewableObjects;
    }

    public void movePlayer (Direction d) {
        myPlayer.setDirection(d);
        Loc targetLoc = myPlayer.getLoc().adjacentLoc(d);
        if (!myViewableObjects.containsKey(targetLoc)) {
            myPlayer.setLoc(targetLoc);
        }
    }

    public void setUpWorld () throws Exception {
        String definitionJSONFilepath = "games/" + myNameOfGame + "/definition.json";
        String worldJSONFilepath = "games/" + myNameOfGame + "/world.json";
        myDefinitionJSON = new JSONCache(getJSON(definitionJSONFilepath));
        myWorldJSON = getJSON(worldJSONFilepath);
        for (String viewableCategory : Constants.VIEWABLE_CATEGORIES) {
            JSONArray objectArray = (JSONArray) myWorldJSON.get(viewableCategory);
            for (Object obj : objectArray) {
                JSONObject jObj = (JSONObject) obj;
                int x = (Integer) jObj.get("x");
                int y = (Integer) jObj.get("y");
                JSONObject definition =
                        myDefinitionJSON.getInstance(viewableCategory, (String) jObj.get("name"));
                String classPath = "game.model." + viewableCategory;
                // TODO: capitalization error possible in classPath?
                AbstractViewableObject newObject =
                        (AbstractViewableObject) Reflection.createInstance(classPath, x, y,
                                                                           definition);
                addViewableObject(new Loc(x, y), newObject);
            }
        }
    }

//    public JSONObject getInstance (String category, String name) {
//        JSONArray definedObjects = (JSONArray) myDefinitionJSON.get(category);
//        for (Object object : definedObjects) {
//            JSONObject jObject = (JSONObject) object;
//            if (jObject.get("name").equals(name)) { return copy(jObject); }
//        }
//        return null;
//    }
//
//    private JSONObject copy (JSONObject object) {
//        String asString = JSONValue.toJSONString(object); // get string representation
//        return (JSONObject) JSONValue.parse(asString); // return a new json object with same data
//    }

    private JSONObject getJSON (String filepath) {
        JSONObject json;
        JSONParser parser = new JSONParser();
        try {
            json = (JSONObject) parser.parse(new FileReader(filepath));
            return json;
        }
        catch (Exception e) {
            return null;
        }
    }
}
