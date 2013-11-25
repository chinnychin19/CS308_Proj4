package util.jsonwrapper;

import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.jsonexceptions.NoDoubleValueJsonException;
import util.jsonwrapper.jsonexceptions.NoIntValueJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONArrayJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;

public class SmartJsonObject extends JSONObject {
    JSONObject myJson;
    public SmartJsonObject(JSONObject obj){
        myJson = obj;
    }
    
    public String getString(String key) throws NoStringValueJsonException{
        try{
            return myJson.get(key).toString();
        } catch(Exception e){
            throw new NoStringValueJsonException();
        }
    }
    
    public double getDouble(String key) throws NoDoubleValueJsonException{
        try{
            return Double.parseDouble(myJson.get(key).toString());
        } catch (Exception e) {
            throw new NoDoubleValueJsonException();
        }       
    }
    
    public int getInt(String key) throws NoIntValueJsonException{
        try{
            return Integer.parseInt(myJson.get(key).toString());
        } catch(Exception e){
            throw new NoIntValueJsonException();
        }
    }
    
    public SmartJsonObject getSmartJsonObject(String key) throws NoJSONObjectJsonException{
        try{
            return new SmartJsonObject((JSONObject) myJson.get(key));
        } catch(Exception e){
            throw new NoJSONObjectJsonException();
        }
    }
    
    public JSONArray getJSONArray(String key) throws NoJSONArrayJsonException{
        try{
            return (JSONArray) myJson.get(key);
        } catch(Exception e){
            throw new NoJSONArrayJsonException();
        }
    }
    
    @Override
    public Set<Object> keySet(){
        return myJson.keySet();
    }
}
