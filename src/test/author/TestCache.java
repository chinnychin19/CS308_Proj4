package test.author;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileReader;
import jsoncache.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;

import author.AuthorView;
import author.model.AuthoringCache;


public class TestCache {
    
    public static final String testFilePrefix = System.getProperty("user.dir") + File.separator + "test-json"+ File.separator;

    @Test
    public void testAdd () {
    	AuthorView view = new AuthorView();
        AuthoringCache cache = new AuthoringCache(view);
        assertFalse(cache.contains("Item", "item0"));

        //String file = "json/test_json_1.json";
        JSONObject json = getJSON(testFilePrefix +"test_json_1.json");
        JSONArray items = (JSONArray) json.get("Item");
        JSONObject item = (JSONObject) items.get(0);
        

        cache.add("Item", item);
        assertTrue(cache.contains("Item", "item0"));
    }

    @Test
    public void testDelete () {
    	AuthorView view = new AuthorView();
        AuthoringCache cache = new AuthoringCache(view);
        assertFalse(cache.contains("Item", "item0"));

        JSONObject json = getJSON(testFilePrefix +"test_json_1.json");
        JSONArray items = (JSONArray) json.get("Item");
        JSONObject item = (JSONObject) items.get(0);

        cache.add("Item", item);
        assertTrue(cache.contains("Item", "item0"));

        try {
            cache.delete("Item", "item0");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        assertFalse(cache.contains("Item", "item0"));
    }

    @Test
    public void testGetInstance () {
    	AuthorView view = new AuthorView();
        AuthoringCache cache = new AuthoringCache(view);
        assertFalse(cache.contains("Item", "item0"));

        JSONObject json = getJSON(testFilePrefix +"test_json_1.json");
        JSONArray items = (JSONArray) json.get("Item");
        JSONObject item = (JSONObject) items.get(0);

        cache.add("Item", item);
        JSONObject itemCopy = null;
        JSONObject itemCopy2 = null;
        JSONObject itemCopy3 = null;
        try {
            itemCopy = cache.getInstance("Item", "item0").toJSONObject();
            itemCopy.put("mode", "bogus");
            itemCopy2 = cache.getInstance("Item", "item0").toJSONObject();
            itemCopy.put("mode", "bogus2");
            itemCopy3 = cache.getInstance("Item", "item0").toJSONObject();
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
        
        assertEquals("wandering", itemCopy3.get("mode").toString());

    }

    @Test
    public void testUpdate () {
    	AuthorView view = new AuthorView();
        AuthoringCache cache = new AuthoringCache(view);
        assertFalse(cache.contains("Item", "item0"));

        JSONObject json = getJSON(testFilePrefix +"test_json_1.json");
        JSONArray items = (JSONArray) json.get("Item");
        JSONObject item = (JSONObject) items.get(0);

        JSONObject json2 = getJSON(testFilePrefix +"test_json_2.json");
        JSONArray items2 = (JSONArray) json2.get("Item");
        JSONObject item2 = (JSONObject) items2.get(0);

        cache.add("Item", item);
        String mode = null;
        try {
            mode = cache.getInstance("Item", "item0").getString("mode");
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
        assertEquals("wandering", mode);

        cache.update("Item", item2);
        String mode2 = null;
        try {
            mode2 = cache.getInstance("Item", "item0").getString("mode");
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
        assertEquals("both", mode2);
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
