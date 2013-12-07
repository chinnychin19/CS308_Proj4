package author.model;

//import java.io.StringWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import author.AuthorView;
import constants.Constants;


public class AuthoringCache {
    private JSONObject myJSON;
    private AuthorView myView;

    public AuthoringCache (AuthorView av) {
        myJSON = new JSONObject();
        myView = av;
        initCategories();
    }

    @SuppressWarnings("unchecked")
	private void initCategories () {
        for (String category : Constants.CATEGORIES) {
            myJSON.put(category, new JSONArray());
        }
    }

    @SuppressWarnings("unchecked")
	public void add (String category, JSONObject data) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        cache.add(data);
        // TODO: Add this implementation to the JMenus myView.update();
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
    
    public JSONObject getRawJSON() {
    	return myJSON;
    }
}
