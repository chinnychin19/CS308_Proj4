package author.panels;

import javax.swing.*;
import author.listeners.FinishListener;
import author.wizard.Wizard;

public class FinishPanel extends JPanel {

    private JLabel FINISH_TEXT = new JLabel("Click 'finish' to create this object.");
    private JButton FINISH_BUTTON = new JButton("Finish");
    
    public FinishPanel() {
        this.add(FINISH_TEXT);
        FINISH_BUTTON.addActionListener(new FinishListener((Wizard)this.getParent()));
        this.add(FINISH_BUTTON);
    }
    
}
