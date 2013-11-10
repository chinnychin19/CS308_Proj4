package author.panels;

import javax.swing.*;


@SuppressWarnings("serial")
public class NamePanel extends JPanel {
    
    private JLabel myNameLabel = new JLabel("Name:");
    private JTextArea myNameArea;

    public NamePanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        myNameArea = new JTextArea();
        
        this.add(myNameLabel);
        this.add(myNameArea);
    }
    
    public String getName() {
        return myNameArea.getText();
    }
    
}
