package author.model;

import java.io.StringWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class AuthoringCache {
    private JSONObject myJSON;
    private static final String[] CATEGORIES =
    { "Type", "TypeMatrix", "Statistic", "Status", "Monster", "Attack", "Item", "NPC", "KeyItem",
     "Obstacle", "WildRegion" };

    public AuthoringCache () {
        myJSON = new JSONObject();
        initCategories();
    }

    private void initCategories () {
        for (String category : CATEGORIES) {
            myJSON.put(category.toLowerCase(), new JSONArray());
        }
    }

    public void add (String category, JSONObject data) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        cache.add(data);
    }

    public void delete (String category, String name) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (int i = 0; i < cache.size(); i++) {
            JSONObject jobject = (JSONObject) cache.get(i);
            if (jobject.get("name").equals(name)) {
                cache.remove(i);
                break;
            }
        }
    }

    public JSONObject getInstance (String category, String name) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (Object object : cache) {
            JSONObject jObject = (JSONObject) object;
            if (jObject.get("name").equals(name)) { return copy(jObject); }
        }
        return null;
    }

    private JSONObject copy (JSONObject object) {
        String asString = JSONValue.toJSONString(object); // get string representation
        return (JSONObject) JSONValue.parse(asString); // return a new json object with same data
    }

    public boolean contains (String category, String name) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (Object object : cache) {
            JSONObject jobject = (JSONObject) object;
            if (jobject.get("name").equals(name)) { return true; }
        }
        return false;
    }

    public void update (String category, JSONObject data) {
        delete(category, (String) data.get("name"));
        add(category, data);
    }
}