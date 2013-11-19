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

    public WordPanel () {
        super(Constants.WORD_PANEL);
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTextLabel = new JLabel(Constants.WORD_PANEL + ":");

        myTextField = new JTextField();
        myTextField.setPreferredSize(Constants.TEXT_AREA_SIZE);

        this.add(myTextLabel);
        this.add(myTextField);
    }
    
    public Map<String, String> getUserInput () {       
        Map<String, String> result = new HashMap<String, String>();
        result.put(myTextLabel.getText(), myTextField.getText());
        return result;
    }

}
