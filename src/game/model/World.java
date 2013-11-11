package game.model;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import constants.Constants;
import util.Direction;
import util.Loc;


public class World {
    private HashMap<Loc, AbstractViewableObject> myViewableObjects;

    private Player myPlayer;
    private JSONObject myDefinitionJSON;
    private JSONObject myWorldJSON;

    public World (Player p) {
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

    public void setUpWorld (String definitionJSONFilepath, String worldJSONFilepath)
                                                                                    throws Exception {

        myDefinitionJSON = getJSON(definitionJSONFilepath);
        myWorldJSON = getJSON(worldJSONFilepath);
        for (String c : Constants.WORLD_CATEGORIES) {
            JSONArray objectArray = (JSONArray) myWorldJSON.get("game.model." + c);
            for (Object obj : objectArray) {
                JSONObject ob = (JSONObject) obj;
                Constructor categoryConstructor =
                        Class.forName(c).getConstructor(int.class, int.class, JSONObject.class);
                int x = (Integer) ob.get("x");
                int y = (Integer) ob.get("y");
                addViewableObject(new Loc(x, y),
                                  (AbstractViewableObject) categoryConstructor
                                          .newInstance(x, y, getInstance(c, (String) ob.get("name"))));
            }
        }
    }

    public JSONObject getInstance (String category, String name) {
        JSONArray definedObjects = (JSONArray) myDefinitionJSON.get(category);
        for (Object object : definedObjects) {
            JSONObject jObject = (JSONObject) object;
            if (jObject.get("name").equals(name)) { return copy(jObject); }
        }
        return null;
    }

    private JSONObject copy (JSONObject object) {
        String asString = JSONValue.toJSONString(object); // get string representation
        return (JSONObject) JSONValue.parse(asString); // return a new json object with same data
    }

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
