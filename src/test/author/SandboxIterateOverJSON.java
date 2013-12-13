package test.author;

import java.util.Set;
import javax.swing.JPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONArrayJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;
import constants.Constants;

public class SandboxIterateOverJSON {

    /**
     * Iterates through all values in the key/value pairs within a JSONObject.
     * If the value is a string, passes off key/value pair to createPanel().
     * If the value is another JSONObject, passes pair off to handleJSONObject().
     * If the value is a JSONArray, passes pair off to handleJSONArray
     * @param obj the JSONObject containing one or more key/value pairs
     * @param currentPanel The current panel.
     * @return void
     */

    
    private void iterateOverJSONObject (JSONObject obj, JPanel currentPanel) { //EXAMPLE: obj = {"monsters": [ {"name":"list_radio_Monster.name"} ] } --> obj = {"name":"list_radio_Monster.name"}
        SmartJsonObject tempObject = null; //new SmartJsonObject(obj);

        Set<?> keySet = tempObject.keySet();
        System.out.println(Constants.OPENING_MESSAGE + keySet);
        for (Object s : keySet) {
            String singleKey = "";
            try{
                singleKey = (String) s;                
            } catch(Exception e0) {
                e0.printStackTrace();
                return;
            }
            try {
                JSONArray jArr = tempObject.getJSONArray(singleKey);
                System.out.println(singleKey + Constants.EQUALS_JSONARRAY);
                //handleJSONArray(singleKey, jArr, currentPanel);
            } catch(NoJSONArrayJsonException e1) {}
            try {
                JSONObject jObj = tempObject.getJSONObject(singleKey);
                System.out.println(singleKey + Constants.EQUALS_JSONOBJECT);
                //handleJSONObject(singleKey, jObj, currentPanel);
            } catch(NoJSONObjectJsonException e2) {}
            try {
                String valueStr = tempObject.getString(singleKey);
                System.out.println(singleKey + Constants.STRING_STATUS_MESSAGE + valueStr + Constants.CLOSE_PARENTHESIS);
                //currentPanel.add(createPanel(singleKey, valueStr));
            } catch(Exception e3) {
//                System.out.println(Constants.FAILED_TO_CREATE_PT1 + (String) s + Constants.FAILED_TO_CREATE_PT2 +
//                                   tempObject.getString((String) s) + Constants.FAILED_TO_CREATE_PT3);
                e3.printStackTrace();
            }
            

        }
    }
}
