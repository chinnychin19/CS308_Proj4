package author.panels;

import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.*;
import author.listeners.FinishListener;
import author.wizard.Wizard;

@SuppressWarnings("serial")
public class FinishPanel extends JPanel {

    private JLabel FINISH_TEXT = new JLabel("Click 'finish' to create this object.");
    private JButton FINISH_BUTTON = new JButton("Finish");
    
    public FinishPanel() {
        this.add(FINISH_TEXT);
        this.add(FINISH_BUTTON);
    }
    
    public void init() {
    	FINISH_BUTTON.addActionListener(new FinishListener((Wizard)SwingUtilities.getAncestorNamed("wizard", this)));
    }
}
