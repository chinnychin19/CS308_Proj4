package author.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import author.panels.AbstractToggleButtonPanel;

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
            AbstractToggleButtonPanel tbp = (AbstractToggleButtonPanel) ie.getSource();
            tbp.updateSelectionState(ie);
        } 
        catch (ClassCastException e) {}        
    }

}
