package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import author.panels.AbstractToggleButtonPanel;
import author.panels.CheckBoxPanel;
import author.panels.RadioButtonsPanel;

public class CheckBoxListener implements ActionListener {
    
    private static CheckBoxListener instance;
    
    private CheckBoxListener(){}

    public static synchronized CheckBoxListener getInstance()
    {
            if (instance == null)
                    instance = new CheckBoxListener();
            return instance;
    }
    
    @Override
    public void actionPerformed (ActionEvent ae) {
        try {
            JCheckBox cb = (JCheckBox) ae.getSource();
            ((CheckBoxPanel) cb.getParent()).updateSelectionState(ae);
        } 
        catch (ClassCastException e) {}
    }

}
