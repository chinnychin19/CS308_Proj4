package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import author.model.AuthoringCache;
import author.wizard.WizardBuilder;


public class LaunchPlayerWizardListener implements ActionListener {

	private AuthoringCache myCache;
	
    @Override
    public void actionPerformed (ActionEvent e) {
    	new WizardBuilder("player.json",myCache);
    }
}
