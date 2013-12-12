package author.model;

//import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONArrayJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;

import author.AuthorView;
import constants.Constants;


public class AuthoringCache {
    private JSONObject myJSON;
    private AuthorView myView;

    public AuthoringCache (AuthorView av) {
        myJSON = new JSONObject();
        myView = av;
        initCategories();
        //mjrTest();
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
        myView.updateMenuAndSidebar();
    }

    public void delete (String category, String name) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (int i = 0; i < cache.size(); i++) {
            JSONObject jobject = (JSONObject) cache.get(i);
            if (jobject.get(Constants.NAME).equals(name)) {
                cache.remove(i);
                break;
            }
        }
    }

    public JSONObject getInstance (String category, String name) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (Object object : cache) {
            JSONObject jObject = (JSONObject) object;
            if (jObject.get(Constants.NAME).equals(name)) { return copy(jObject); }
        }
        return null;
    }

    public List<String> getAllInstanceNamesInCategory(String category){
        List<String> allNames = new LinkedList<String>();
        JSONArray cache = (JSONArray) myJSON.get(category);        
        for (Object object : cache) {
            JSONObject jObject = (JSONObject) object;
            allNames.add((String) jObject.get(Constants.NAME));
        }
        return allNames;
    }

    private JSONObject copy (JSONObject object) {
        String asString = JSONValue.toJSONString(object); // get string representation
        return (JSONObject) JSONValue.parse(asString); // return a new json object with same data
    }

    public boolean contains (String category, String name) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (Object object : cache) {
            JSONObject jobject = (JSONObject) object;
            if (jobject.get(Constants.NAME).equals(name)) { return true; }
        }
        return false;
    }

    public void update (String category, JSONObject data) {
        delete(category, (String) data.get(Constants.NAME));
        add(category, data);
    }

    public JSONObject getRawJSON() {
        return myJSON;
    }
    
    public AuthorView getAuthorView() {
        return myView;
    }

    
}
