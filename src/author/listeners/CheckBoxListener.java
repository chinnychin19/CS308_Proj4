package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

import author.panels.CheckBoxPanel;

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
