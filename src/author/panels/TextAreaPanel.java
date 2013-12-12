package author.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextAreaPanel extends JPanel {
    
    private JLabel myJLabel;
    private JTextArea myJTextArea;
    
    private String TEXT_LABEL = "Add any additional JSON information here: ";

    public TextAreaPanel() {
        myJLabel = new JLabel(TEXT_LABEL);
        myJTextArea =  new JTextArea();
    }
    
}
