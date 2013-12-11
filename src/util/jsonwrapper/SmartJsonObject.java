package util.jsonwrapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.jsonwrapper.jsonexceptions.NoBooleanValueJsonException;
import util.jsonwrapper.jsonexceptions.NoDoubleValueJsonException;
import util.jsonwrapper.jsonexceptions.NoIntValueJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONArrayJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;

/**
 * Wrapper around the SimpleJSON library to retrieve specific types from the JSON
 * Throws SmartJsonExceptions when appropriate
 * @author tylernisonoff
 *
 */
public class SmartJsonObject {
    JSONObject myJson;
    public SmartJsonObject(JSONObject obj) throws NoJSONObjectJsonException {
        if (obj == null) {
            throw new NoJSONObjectJsonException();
        }
        myJson = obj;
    }
    
    public SmartJsonObject(String objString) throws NoJSONObjectJsonException {
        if (objString == null) {
            throw new NoJSONObjectJsonException();
        }
        JSONObject json;
        JSONParser parser = new JSONParser();
        try {
            json = (JSONObject) parser.parse(objString);
            myJson = json;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
   /**
    * Finds a String value given a key
    * @param key - key in the json object
    * @return - The String value for the given key
    * @throws NoStringValueJsonException
    */
    public String getString(String key) throws NoStringValueJsonException{
        try{
            return myJson.get(key).toString();
        } catch(Exception e){
            throw new NoStringValueJsonException();
        }
    }
    
    /**
     * Finds a Double value given a key
     * @param key - key in the json object
     * @return - The double value for the given key
     * @throws NoDoubleValueJsonException
     */
    public double getDouble(String key) throws NoDoubleValueJsonException{
        try{
            return Double.parseDouble(myJson.get(key).toString());
        } catch (Exception e) {
            throw new NoDoubleValueJsonException();
        }       
    }
    
    /**
     * Finds a int value given a key
     * @param key - key in the json object
     * @return - The int value for the given key
     * @throws NoIntValueJsonException
     */
    public int getInt(String key) throws NoIntValueJsonException{
        try{
            return Integer.parseInt(myJson.get(key).toString());
        } catch(Exception e){
            throw new NoIntValueJsonException();
        }
    }
    
    /**
     * Finds a int value given a key
     * @param key - key in the json object
     * @return - The int value for the given key
     * @throws NoIntValueJsonException
     * @throws NoBooleanValueJsonException 
     */
    public boolean getBoolean(String key) throws NoBooleanValueJsonException{
        try{
            return Boolean.parseBoolean(myJson.get(key).toString());
        } catch(Exception e){
            throw new NoBooleanValueJsonException();
        }
    }
    
    /**
     * Finds a JSONObject (value) for a given key
     * Does NOT return myJson
     * @param key - key in the json object
     * @return - a JSONObject for the key
     * @throws NoJSONObjectJsonException
     */
    public JSONObject getJSONObject(String key) throws NoJSONObjectJsonException{
        try{
            return (JSONObject) myJson.get(key);
        } catch(Exception e){
            throw new NoJSONObjectJsonException();
        }
    }
    
    /**
     * Finds a JsonObject for a given key
     * Turns it into a SmartJSON object
     * @param key - key in the json object
     * @return - a SmartJsonObject for the key
     * @throws NoJSONObjectJsonException
     */
    public SmartJsonObject getSmartJsonObject(String key) throws NoJSONObjectJsonException{
        try{
            return new SmartJsonObject((JSONObject) myJson.get(key));
        } catch(Exception e){
            throw new NoJSONObjectJsonException();
        }
    }
    
    /**
     * Finds a JSONArray for a given key
     * @param key - key in the json object
     * @return - a JSONArray for the key
     * @throws NoJSONArrayJsonException
     */
    public JSONArray getJSONArray(String key) throws NoJSONArrayJsonException{
        try{
            return (JSONArray) myJson.get(key);
        } catch(Exception e){
            throw new NoJSONArrayJsonException();
        }
    }
    /**
     * @return - The Keys of the JSON Object
     */
    public Set<Object> keySet(){
        return myJson.keySet();
    }
    
    public String toString(){
        return myJson.toJSONString();
    }

//    public boolean isNull () {
//        return myJson == null;
//    }
}
