package author.wizard;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import author.model.AuthoringCache;
import author.panels.AbstractWizardPanel;
import author.panels.CheckBoxPanel;
import author.panels.ContainerPanel;

public class WizardConverter {
	
	private Wizard myWizard;
	private AuthoringCache myCache;
	
	public WizardConverter(Wizard parent, AuthoringCache cache) {
		myWizard = parent;
		myCache = cache;
		JSONObject temp = wizardToJSON();
		myCache.add(myWizard.getObjectName(),temp);
		SwingUtilities.getWindowAncestor(myWizard).dispose();
	}
	
	private JSONObject wizardToJSON() {
//		JSONObject gameObject = new JSONObject();
		//JSONArray wizardArray = new JSONArray();	    
	    
	    JSONObject tempObject = panelToJSONObject(myWizard.getCardPanel());
	    
	    //wizardArray.add(tempObject);
	    
//	    gameObject.put(myWizard.getObjectName(),wizardArray);
//	    System.out.println(gameObject.toString());
	    
	    return tempObject;
	}
	
	private JSONObject panelToJSONObject(JPanel panel) {
		
		JSONObject outputJSONObject = new JSONObject();
		
		for (Component c : panel.getComponents()) {
			
	    	if (c instanceof AbstractWizardPanel) {
	    			
	    		outputJSONObject.putAll(((AbstractWizardPanel)c).getUserInput());
	    		
	    	} else if (c instanceof ContainerPanel) {
	    		
	    		ContainerPanel container = (ContainerPanel) c;
	    		
	    		if (container.getType() == "array") {
	    			outputJSONObject.put(
	    					container.getLabel(), 
	    					panelToJSONArray(container)
	    					);
	    		} else {
	    			outputJSONObject.put(
	    					container.getLabel(), 
	    					panelToJSONObject(container)
	    					);
	    		}
	    	}
	    }
		
		return outputJSONObject;
	}
	
	private JSONArray panelToJSONArray(JPanel panel) {
		JSONArray outputJSONArray = new JSONArray();
		for (Component c : panel.getComponents()) {
	    	if (c instanceof AbstractWizardPanel) {
	    		
	    		outputJSONArray.add(new JSONObject(((AbstractWizardPanel) c).getUserInput()));
	    		
	    	} else if (c instanceof ContainerPanel) {

	    		ContainerPanel container = (ContainerPanel) c;
	    		
	    		if (container.getType() == "array") {
	    			outputJSONArray.add(panelToJSONArray(container));
	    		} else {
	    			outputJSONArray.add(panelToJSONObject(container));
	    		}
	    	}
	    }
		return outputJSONArray;
	}

}
