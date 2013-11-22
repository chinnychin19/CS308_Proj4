package author.listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import author.wizard.PlayerWizard;
import author.wizard.Wizard;
import author.wizard.WizardConverter;
import author.model.AuthoringCache;
import author.panels.AbstractWizardPanel;


public class FinishListener implements ActionListener {

	Wizard myParentWizard;
	AuthoringCache myCache;
	
	public FinishListener(Wizard parentWizard, AuthoringCache ac) {
		myParentWizard = parentWizard;
		myCache = ac;
	}
	
    @Override
    public void actionPerformed (ActionEvent e) {
    	WizardConverter c = new WizardConverter(myParentWizard, myCache);
    }

}
