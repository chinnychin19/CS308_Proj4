package author.wizard;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import author.panels.AbstractWizardPanel;

public class WizardConverter {
	
	public WizardConverter(Wizard parent) {
		System.out.println(wizardToJSON(parent));
	}
	
	private String wizardToJSON(Wizard wizard) {
		//JSONObject gameObject = new JSONObject();
		JSONArray wizardArray = new JSONArray();
		
		Map<String,String> wizardAsMap = wizardToMap(wizard);
		
	    JSONObject tempObject = new JSONObject(wizardAsMap);
	    
	    wizardArray.add(tempObject);
	    
	    //gameObject.put("Player",wizardArray);
	    
	    return wizardArray.toString();
	}
	
	private Map<String,String> wizardToMap(Wizard wizard) {
		Map<String,String> outputMap = new HashMap<String,String>();
		
		for (Component c : wizard.getCardPanel().getComponents()) {
	    	if (c instanceof AbstractWizardPanel) {
	    		outputMap.putAll(((AbstractWizardPanel) c).getUserInput()); 
	    	}
	    }
		return outputMap;
	}
}
