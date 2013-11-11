package author.panels;

import javax.swing.*;
import author.listeners.FinishListener;

public class FinishPanel extends JPanel {

    private JLabel FINISH_TEXT = new JLabel("Click 'finish' to create this object.");
    private JButton FINISH_BUTTON = new JButton("Finish");
    
    public FinishPanel() {
        this.add(FINISH_TEXT);
        FINISH_BUTTON.addActionListener(new FinishListener());
        this.add(FINISH_BUTTON);
    }
    
}
