package author.model;

import java.util.LinkedList;
import java.util.List;
import jsoncache.JSONCache;
import jsoncache.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import util.jsonwrapper.jsonexceptions.SmartJsonException;

import author.AuthorView;
import constants.Constants;


public class AuthoringCache {
	
	//private JSONObject myJSON;
    private JSONCache myCache;
    
    private AuthorView myView;

    public AuthoringCache (AuthorView av) {
        //myJSON = new JSONObject();
        myCache = null;
        myView = av;
        initCategories();
    }
    
    public AuthoringCache () {
        //myJSON = new JSONObject();
        myCache = null;
        myView = null;
        initCategories();
    }



    @SuppressWarnings("unchecked")
    private void initCategories () {
/*      for (String category : Constants.CATEGORIES) {
            myJSON.put(category, new JSONArray());
        }       */
        myCache = new JSONCache(Constants.CATEGORIES);
        
    }

    @SuppressWarnings("unchecked")
    public void add (String category, JSONObject data) {
        //JSONArray cache = (JSONArray) myJSON.get(category);
        //System.out.println(Constants.ADDING + cache.toString());
        //cache.add(data);
        myCache.add(category, data);
        if (myView != null) {
        	myView.updateMenuAndSidebar();
        }
    }

    public void delete (String category, String name) {
/*      JSONArray cache = (JSONArray) myJSON.get(category);
        for (int i = 0; i < cache.size(); i++) {
            JSONObject jobject = (JSONObject) cache.get(i);
            if (jobject.get(Constants.NAME).equals(name)) {
                cache.remove(i);
                break;
            }
        }       */
        try {
            myCache.delete(category, name);
        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //TODO: possibly rewrite to return SmartJsomObject, fix problems this causes elsewhere
    public JSONObject getInstance (String category, String name) {
/*      JSONArray cache = (JSONArray) myJSON.get(category);
        for (Object object : cache) {
            JSONObject jObject = (JSONObject) object;
            if (jObject.get(Constants.NAME).equals(name)) { return copy(jObject); }
        }
        return null;    */
        try {
            return myCache.getInstance(category, name).toJSONObject();
        }
        catch (SmartJsonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

/*  public List<String> getAllInstanceNamesInCategory(String category){
        List<String> allNames = new LinkedList<String>();
        JSONArray cache = (JSONArray) myJSON.get(category);        
        for (Object object : cache) {
            JSONObject jObject = (JSONObject) object;
            allNames.add((String) jObject.get(Constants.NAME));
        }
        return allNames;
    }   */

/*  private JSONObject copy (JSONObject object) {
    	// get string representation
        String asString = JSONValue.toJSONString(object);
        
     // return a new json object with same data
        return (JSONObject) JSONValue.parse(asString);
    }   */

    public boolean contains (String category, String name) {
/*      JSONArray cache = (JSONArray) myJSON.get(category);
        for (Object object : cache) {
            JSONObject jobject = (JSONObject) object;
            if (jobject.get(Constants.NAME).equals(name)) { return true; }
        }
        return false;   */
        return myCache.contains(category, name);
    }

    public void update (String category, JSONObject data) {
        this.delete(category, (String) data.get(Constants.NAME));
        this.add(category, data);
    }
    
    public void reset() {
        //myJSON = new JSONObject();
        myCache = null; 
        initCategories();
    }

    public JSONObject getRawJSON() {
        //return myJSON;
        return myCache.toJSONObject();
    }
    
    public AuthorView getAuthorView() {
        return myView;
    }
    
}
