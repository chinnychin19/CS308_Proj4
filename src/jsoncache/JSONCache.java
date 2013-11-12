package jsoncache;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/**
 * This class creates an giant JSON object that stores severals lists of other JSON objects.
 * There may be several different types of JSON objects that it stores, so it keeps track of various
 * categories.
 * Therefore, this giant object basically serves as a cache for different categories of other
 * objects.
 * For example, categories may consist of Weapons, Players, and Items. Each of these lists is a
 * JSON Array.
 * This giant JSON object also has a toString() method to represent itself as a JSON string, so it
 * can easily be written to a file or inserted within one.
 * 
 * @author Chinmay Patwardhan
 * @author Austin Ness
 * 
 */

public class JSONCache {
    private JSONObject myJSON;

    /**
     * Initializes empty JSON arrays for the provided categories. Categories may NOT be added later.
     * 
     * @param categories
     */
    public JSONCache (String[] categories) {
        myJSON = new JSONObject();
        initCategories(categories);
    }

    /**
     * Accepts a JSON object that already is formatted as a JSONCache object.
     * This is still useful because it adds functionality for deepcopies of elements through the
     * getInstance() method.
     * 
     * @param cacheObject JSON object that contains JSON Arrays of JSON objects.
     */
    public JSONCache (JSONObject cacheObject) {
        myJSON = cacheObject;
    }

    /**
     * Returns a JSON string representation of this giant JSON object.
     */
    public String toString () {
        return JSONValue.toJSONString(myJSON);
    }

    /**
     * Initializes empty JSON Array for each category that will be stored in this giant JSON object.
     * For example, if this cache will hold Weapons, Players, and Items, it will initialize each of
     * those categories at construction to be empty Arrays.
     */
    private void initCategories (String[] categories) {
        for (String category : categories) {
            myJSON.put(category, new JSONArray());
        }
    }

    /**
     * Adds a JSON object to the appropriate JSON Array. The object MUST have a field called "name"
     * which stores a unique String.
     * 
     * @param category Must be a category that appears in the original categories.
     * @param data a JSON object representing an instance of that category
     */
    public void add (String category, JSONObject data) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        cache.add(data);
    }

    /**
     * Removes an element from a given category with the given unique name.
     * 
     * @param category
     * @param name
     * @throws JSONException Throws exception if object not found
     */
    public void delete (String category, String name) throws JSONException {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (int i = 0; i < cache.size(); i++) {
            JSONObject jobject = (JSONObject) cache.get(i);
            if (jobject.get("name").equals(name)) {
                cache.remove(i);
                return;
            }
        }
        throw new JSONException();
    }

    /**
     * Returns a copy of the requested object with the given category and unique name.
     * 
     * @param category
     * @param name
     * @return
     * @throws JSONException Throws exception if object not found
     */
    public JSONObject getInstance (String category, String name) throws JSONException {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (Object object : cache) {
            JSONObject jObject = (JSONObject) object;
            if (jObject.get("name").equals(name)) { return copy(jObject); }
        }
        throw new JSONException();
    }

    /**
     * Private helper method to return a deep copy of a given JSON object by converting it to its
     * string
     * representation and re-parsing it.
     * 
     * @param object A JSON object
     * @return A deep copy of the object with the same values
     */
    private JSONObject copy (JSONObject object) {
        String asString = JSONValue.toJSONString(object);
        return (JSONObject) JSONValue.parse(asString);
    }

    /**
     * Self explanatory.
     * 
     * @param category
     * @param name
     * @return
     */
    public boolean contains (String category, String name) {
        JSONArray cache = (JSONArray) myJSON.get(category);
        for (Object object : cache) {
            JSONObject jobject = (JSONObject) object;
            if (jobject.get("name").equals(name)) { return true; }
        }
        return false;
    }

    /**
     * Edits a pre-existing object in the cache by deleting the old data and replacing it with the
     * provided data.
     * 
     * @param category
     * @param data
     * @throws JSONException Throws an exception if the object is not found.
     */
    public void update (String category, JSONObject data) throws JSONException {
        delete(category, (String) data.get("name"));
        add(category, data);
    }
}
