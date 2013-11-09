package author.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


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
            myJSON.put(category, new JSONArray());
        }
    }

    public void add (String category, JSONObject data) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        cache.add(data);
    }

    public void delete (String category, String name) {
        JSONArray cache = (JSONArray) myJSON.get(category);  
        for (Object object : cache){
            JSONObject jobject = (JSONObject) object;
            if(jobject.get("name").equals(name)){
                cache.remove(object);
            }
        }
    }

    public JSONObject getInstance (String category, String name) {
        return null;
    }

    public boolean contains (String category, String name) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (Object object : cache){
            JSONObject jobject = (JSONObject) object;
            if(jobject.get("name").equals(name)){
                return true;
            }
        }
        return false;
    }

    public void update (String category, JSONObject data) {
        delete(category, (String) data.get("name"));
        add(category, data);
    }
}
