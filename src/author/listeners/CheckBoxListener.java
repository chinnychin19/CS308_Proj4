package author.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import author.panels.AbstractToggleButtonPanel;
import author.panels.CheckBoxPanel;
import author.panels.RadioButtonsPanel;

public class CheckBoxListener implements ItemListener {
    
    private static CheckBoxListener instance;
    
    private CheckBoxListener(){}

    public static synchronized CheckBoxListener getInstance()
    {
            if (instance == null)
                    instance = new CheckBoxListener();

            return instance;
    }
    
    @Override
    public void itemStateChanged (ItemEvent ie) {
        try {
            JCheckBox chk = (JCheckBox) ie.getSource();
            ((CheckBoxPanel) chk.getParent()).updateSelectionState(ie);
        } 
        catch (ClassCastException e) { System.out.println("ClassCastException error!"); }        
    }

}
