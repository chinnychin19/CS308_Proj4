package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import constants.Constants;
import author.model.AuthoringCache;
import author.wizard.WizardBuilder;


/**
 * Listener that launches a new, dynamically-defined wizard
 * based on a given JSON template predefined by the user.
 * 
 * @author Michael Marion
 * 
 */

public class LaunchWizardListener implements ActionListener {

    private String myType;
    private AuthoringCache myCache;

    public LaunchWizardListener (String type, AuthoringCache cache) {
        myType = type;
        myCache = cache;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        new WizardBuilder(myType, Constants.PLAYER_JSON, myCache);
    }

}
