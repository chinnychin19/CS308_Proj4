package author.model;

import java.util.LinkedList;
import java.util.List;
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
    
    public AuthoringCache () {
        myJSON = new JSONObject();
        myView = null;
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
        System.out.println(Constants.ADDING + cache.toString());
        cache.add(data);
        if (myView != null) {
        	myView.updateMenuAndSidebar();
        }
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
    	// get string representation
        String asString = JSONValue.toJSONString(object);
        
     // return a new json object with same data
        return (JSONObject) JSONValue.parse(asString);
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
    
    public void reset() {
        myJSON = new JSONObject();
        initCategories();
    }

    public JSONObject getRawJSON() {
        return myJSON;
    }
    
    public AuthorView getAuthorView() {
        return myView;
    }
    
}
