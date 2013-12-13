package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import author.wizard.Wizard;
import author.wizard.WizardConverter;
import author.model.AuthoringCache;


/**
 * Listener that allows the user to write game data out to
 * JSON. Instantiates a new WizardConverter to accomplish this.
 * 
 * @author Robert Ansel
 * 
 */

public class FinishListener implements ActionListener {

    Wizard myParentWizard;
    AuthoringCache myCache;

    public FinishListener (Wizard parentWizard, AuthoringCache ac) {
        myParentWizard = parentWizard;
        myCache = ac;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        new WizardConverter(myParentWizard, myCache);
    }

}
