package author.listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import author.wizard.PlayerWizard;
import author.wizard.Wizard;
import author.panels.AbstractWizardPanel;


public class FinishListener implements ActionListener {

	Wizard myParentWizard;
	
	public FinishListener(Wizard parentWizard) {
		myParentWizard = parentWizard;
	}
	
    @Override
    public void actionPerformed (ActionEvent e) {
        for (Component c : myParentWizard.getMyCardPanel().getComponents()) {
        	JSONObject outputObject = new JSONObject();
        	if (c instanceof AbstractWizardPanel) {
        		Map<String,String> test = ((AbstractWizardPanel) c).getUserInput();
        		JSONObject jsonoutput = new JSONObject(test);
        		outputObject.append(jsonoutput);
        		System.out.println(test);
        	}
        }
    }

}
