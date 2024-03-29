package author.wizard;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import constants.Constants;
import author.model.AuthoringCache;
import author.panels.AbstractWizardPanel;
import author.panels.CheckBoxPanel;
import author.panels.ContainerPanel;


public class WizardConverter {

    private Wizard myWizard;
    private AuthoringCache myCache;

    public WizardConverter (Wizard parent, AuthoringCache cache) {
        myWizard = parent;
        myCache = cache;
        JSONObject temp = wizardToJSON();
        myCache.add(myWizard.getObjectName(), temp);
        SwingUtilities.getWindowAncestor(myWizard).dispose();
    }

    private JSONObject wizardToJSON () {
        JSONObject tempObject = panelToJSONObject(myWizard.getCardPanel());
        return tempObject;
    }

    @SuppressWarnings("unchecked")
	private void smartJSONObjectAdd (JSONObject parent, Map<String, String> data) {
        Set<String> keys = data.keySet();
        Map<String, Object> tempMap = new HashMap<String, Object>();
        for (Object s : keys) {
        	if (data.get(s) == null || data.get(s).equals("")) {
        		
        	}
        	else if (data.get(s).length() > 0 && data.get(s).charAt(0) == '{') {
                JSONParser parser = new JSONParser();
                try {
                    tempMap.put((String) s, parser.parse(data.get(s)));
                }
                catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else {
            	tempMap.put((String) s, data.get(s));
            }
        }
        if (tempMap.size() > 0) {
        	parent.putAll(tempMap);
        }
        
    }

    @SuppressWarnings("unchecked")
	private void smartJSONArrayAdd (JSONArray parent, Map<String, String> data) {
        Set<String> keys = data.keySet();
        Map<String, Object> tempMap = new HashMap<String, Object>();
        for (Object s : keys) {
        	if (data.get(s) == null || data.get(s).equals("")) {
        		
        	}
        	else if (data.get(s).length() > 0 && data.get(s).charAt(0) == '{') {
                JSONParser parser = new JSONParser();
                try {
                    tempMap.put((String) s, parser.parse(data.get(s)));
                }
                catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else {
                tempMap.put((String) s, data.get(s));
            }
        }
        if (tempMap.size() > 0) {
        	parent.add(new JSONObject(tempMap));
        }
    }

    @SuppressWarnings("unchecked")
	private JSONObject panelToJSONObject (JPanel panel) {

        JSONObject outputJSONObject = new JSONObject();

        for (Component c : panel.getComponents()) {
            if (c instanceof AbstractWizardPanel) {
                // outputJSONObject.putAll(((AbstractWizardPanel) c).getUserInput());
                smartJSONObjectAdd(outputJSONObject, ((AbstractWizardPanel) c).getUserInput());
            }
            else if (c instanceof ContainerPanel) {

                ContainerPanel container = (ContainerPanel) c;

                if (container.getType() == Constants.ARRAY_STRING) {
                    outputJSONObject.put(
                                         container.getLabel(),
                                         panelToJSONArray(container)
                            );
                }
                else {
                    outputJSONObject.put(
                                         container.getLabel(),
                                         panelToJSONObject(container)
                            );
                }
            }
        }

        return outputJSONObject;
    }

    @SuppressWarnings("unchecked")
	private JSONArray panelToJSONArray (JPanel panel) {
        JSONArray outputJSONArray = new JSONArray();
        for (Component c : panel.getComponents()) {
        	if (c instanceof CheckBoxPanel) {
            	Map<String,String> initialData = ((AbstractWizardPanel) c).getUserInput();
            	Map<String,String> invertedData = new HashMap<String,String>();
            	Set<String> values = initialData.keySet();
            	for (String value : values) {
            		invertedData.put(initialData.get(value), value);
            		smartJSONArrayAdd(outputJSONArray,invertedData);
            		invertedData.clear();            		
            	}
            }
            else if (c instanceof AbstractWizardPanel) {
                smartJSONArrayAdd(outputJSONArray, ((AbstractWizardPanel) c).getUserInput());
            }
            else if (c instanceof ContainerPanel) {

                ContainerPanel container = (ContainerPanel) c;

                if (container.getType() == Constants.ARRAY_STRING) {
                    outputJSONArray.add(panelToJSONArray(container));
                }
                else {
                    outputJSONArray.add(panelToJSONObject(container));
                }
            }
        }
        return outputJSONArray;
    }

}
