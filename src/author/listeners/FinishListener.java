package author.listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import author.wizard.PlayerWizard;
import author.wizard.Wizard;


public class FinishListener implements ActionListener {

    Wizard myParentWizard;

    public FinishListener (Wizard parentWizard) {
        myParentWizard = parentWizard;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        for (Component c : myParentWizard.getComponents()) {

        }
    }

}
