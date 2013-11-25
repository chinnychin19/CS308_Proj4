package author.panels;

import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import constants.Constants;


@SuppressWarnings("serial")
public class NumberPanel extends AbstractTextPanel {

    JTextField myTextField;

    public NumberPanel (String label) {
        super(Constants.NUMBER_PANEL);
        myTextLabel = new JLabel(label + ":");
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTextField = new JTextField();
        myTextField.setMaximumSize(Constants.TEXT_AREA_SIZE);
        myTextField.setInputVerifier(new NumberVerifier());

        this.add(myTextLabel);
        this.add(myTextField);
    }

    public Map<String, String> getUserInput () {
        Map<String, String> result = new HashMap<String, String>();
        String label = myTextLabel.getText();
        result.put(label.substring(0, label.length() - 1), myTextField.getText());
        return result;
    }

}
