package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import author.wizard.Wizard;
import author.wizard.WizardConverter;
import author.model.AuthoringCache;


public class FinishListener implements ActionListener {

	Wizard myParentWizard;
	AuthoringCache myCache;
	
	public FinishListener(Wizard parentWizard, AuthoringCache ac) {
		myParentWizard = parentWizard;
		myCache = ac;
	}
	
    @Override
    public void actionPerformed (ActionEvent e) {
    	/*WizardConverter c = */new WizardConverter(myParentWizard, myCache);
    }

}
