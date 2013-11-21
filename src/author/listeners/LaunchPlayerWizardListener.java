package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import author.wizard.PlayerWizard;
import author.wizard.WizardBuilder;


public class LaunchPlayerWizardListener implements ActionListener {

    @Override
    public void actionPerformed (ActionEvent e) {
    	new WizardBuilder("player.json");
    }
}
