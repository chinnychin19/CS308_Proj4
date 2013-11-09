package test.author;

import static org.junit.Assert.*;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import author.model.AuthoringCache;


public class TestCache {

    @Test
    public void testAdd () {
        AuthoringCache cache = new AuthoringCache();
        assertFalse(cache.contains("item", "item1"));

        String file = "json/test_json_1.json";
        JSONObject json = getJSON(file);
        JSONArray items = (JSONArray) json.get("item");
        JSONObject item = (JSONObject) items.get(0);

        cache.add("item", item);
        assertTrue(cache.contains("item", "item1"));
    }

    @Test
    public void testDelete () {
        AuthoringCache cache = new AuthoringCache();
        assertFalse(cache.contains("item", "item1"));

        String file = "json/test_json_1.json";
        JSONObject json = getJSON(file);
        JSONArray items = (JSONArray) json.get("item");
        JSONObject item = (JSONObject) items.get(0);

        cache.add("item", item);
        assertTrue(cache.contains("item", "item1"));

        cache.delete("item", "item1");
        assertFalse(cache.contains("item", "item1"));
    }

    @Test
    public void testUpdate () {
        AuthoringCache cache = new AuthoringCache();
        assertFalse(cache.contains("item", "item1"));

        String file = "json/test_json_1.json";
        JSONObject json = getJSON(file);
        JSONArray items = (JSONArray) json.get("item");
        JSONObject item = (JSONObject) items.get(0);

        String file2 = "json/test_json_2.json";
        JSONObject json2 = getJSON(file2);
        JSONArray items2 = (JSONArray) json2.get("item");
        JSONObject item2 = (JSONObject) items2.get(0);

        cache.add("item", item);
        String mode = cache.getInstance("item", "item1").get("mode").toString();
        assertEquals("wandering", mode);

        cache.update("item", item2);
        String mode2 = cache.getInstance("item", "item1").get("mode").toString();
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
