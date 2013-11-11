package game.model;

import java.io.FileReader;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import constants.Constants;


public class ObjectFactory {

    private JSONObject myDefinitionJSON;
    private JSONObject myWorldJSON;

    public ObjectFactory (String definitionJSONFilepath, String worldJSONFilepath) throws Exception {

        myDefinitionJSON = getJSON(definitionJSONFilepath);
        myWorldJSON = getJSON(worldJSONFilepath);
        for (String c : Constants.WORLD_CATEGORIES) {
            JSONArray objectArray = (JSONArray) myWorldJSON.get("game.model." + c);
            for (Object obj : objectArray) {
                JSONObject ob = (JSONObject) obj;
                Class.forName(c).getConstructor(int.class, int.class, JSONObject.class)
                        .newInstance(ob.get("x"), ob.get("y"),
                                     getInstance(c, (String) ob.get("name")));
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
