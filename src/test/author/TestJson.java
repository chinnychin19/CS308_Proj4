package test.author;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;


public class TestJson {

    @Test
    public void testSampleJson1 () {
        String file = "json/test_json_1.json";
        JSONObject json = getJSON(file);
        assertNotNull(json);
    }

    @Test
    public void testItem () {
        String file = "json/test_json_1.json";
        JSONObject json = getJSON(file);
        JSONArray items = (JSONArray) json.get("item");
        JSONObject item = (JSONObject) items.get(0);

        String nameValue = (String) item.get("name");
<<<<<<< HEAD
        assertEquals("item0", nameValue);
        
        JSONObject effect = (JSONObject)item.get("effect");
        String valueValue = (String) effect.get("value");
        assertEquals("0.4", valueValue);
    }
    
    /**
     * Read the correct value when 'number' is specified as the effect type.
     */
    @Test
    public void testAttackNumber(){
        String file = "json/test_json_1.json";
        JSONObject json = getJSON(file);
        
        JSONArray items = (JSONArray)json.get("item");
        JSONObject firstItem = (JSONObject)items.get(1);
        
        JSONObject effect = (JSONObject)firstItem.get("effect");
        
        String nameValue = (String) effect.get("type");
        assertEquals("number", nameValue);
        
        String effectValue = (String) effect.get("value");
        assertEquals("3", effectValue);
    }
    
    
    
    private JSONObject getJSON(String filepath){
=======
        assertEquals("item1", nameValue);

        JSONObject effect = (JSONObject) item.get("effect");
        String valueValue = (String) effect.get("value");
        assertEquals("0.4", valueValue);
    }

    private JSONObject getJSON (String filepath) {
>>>>>>> austin
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
