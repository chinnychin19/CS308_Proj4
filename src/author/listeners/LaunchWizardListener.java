package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import author.wizard.Wizard;
import author.wizard.WizardBuilder;


public class LaunchWizardListener implements ActionListener {

	private String myType;
	
	public LaunchWizardListener(String type) {
		myType = type;
	}
    @Override
    public void actionPerformed (ActionEvent e) {
    	new WizardBuilder(myType,"player.json");
    	}

}
