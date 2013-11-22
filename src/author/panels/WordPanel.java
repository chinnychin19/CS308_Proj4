package author.panels;

import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import constants.Constants;


@SuppressWarnings("serial")
public class WordPanel extends AbstractTextPanel {
    
    private JTextField myTextField;

    public WordPanel (String label) {
        super(Constants.WORD_PANEL);
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTextLabel = new JLabel(label + ":");

        myTextField = new JTextField();
        myTextField.setPreferredSize(Constants.TEXT_AREA_SIZE);

        this.add(myTextLabel);
        this.add(myTextField);
    }
    
    public Map<String, String> getUserInput () {       
        Map<String, String> result = new HashMap<String, String>();
        String label = myTextLabel.getText();
        result.put(label.substring(0, label.length()-1), myTextField.getText());
        return result;
    }

}
