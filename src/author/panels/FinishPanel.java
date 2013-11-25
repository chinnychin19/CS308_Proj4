package author.panels;

import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.*;
import author.listeners.FinishListener;
import author.model.AuthoringCache;
import author.wizard.Wizard;

@SuppressWarnings("serial")
public class FinishPanel extends JPanel {

    private JLabel FINISH_TEXT = new JLabel("Click 'finish' to create this object.");
    private JButton FINISH_BUTTON = new JButton("Finish");
    private AuthoringCache myCache;
    
    public JButton getButton() {
    	return FINISH_BUTTON;
    }
    
    public FinishPanel(AuthoringCache cache) {
        this.add(FINISH_TEXT);
        myCache = cache;
    }
    
    private void initialize() {
    	FINISH_BUTTON.addActionListener(new FinishListener((Wizard)SwingUtilities.getAncestorNamed("wizard",this),myCache));
        this.add(FINISH_BUTTON);
    }
    
    public void init() {
    	initialize();
    }
}
