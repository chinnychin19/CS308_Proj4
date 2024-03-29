package author.listeners;

/**
 * Listener that is associated with the RadioButtonsPanel.  It updates
 * the selection and allows the Panel to get the final selection.
 * 
 * @author weskpga, mray90
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import author.panels.RadioButtonsPanel;


public class RadioButtonListener implements ActionListener {

    private static RadioButtonListener instance;

    private RadioButtonListener () {
    }

    public static synchronized RadioButtonListener getInstance ()
    {
        if (instance == null)
            instance = new RadioButtonListener();
        return instance;
    }

    @Override
    public void actionPerformed (ActionEvent ae) {
        try {
            JRadioButton rb = (JRadioButton) ae.getSource();
            ((RadioButtonsPanel) rb.getParent()).updateSelectionState(ae);
        }
        catch (ClassCastException e) {
        }
    }

}
