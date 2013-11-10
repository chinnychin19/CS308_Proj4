package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import author.wizard.PlayerWizard;


public class LaunchPlayerWizardListener implements ActionListener {

    @Override
    public void actionPerformed (ActionEvent e) {
        new PlayerWizard();
    }

}
