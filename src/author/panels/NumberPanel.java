package author.panels;

import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import constants.Constants;


@SuppressWarnings("serial")
public class NumberPanel extends AbstractTextPanel {
    
    JTextArea myTextArea;

    public NumberPanel () {
        super(Constants.NUMBER_PANEL);
        myTextLabel = new JLabel(Constants.NUMBER_PANEL + ":");
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTextArea = new JTextArea();
        myTextArea.setPreferredSize(Constants.TEXT_AREA_SIZE);
        myTextArea.setInputVerifier(new NumberVerifier());

        this.add(myTextLabel);
        this.add(myTextArea);
    }

    public Map<String, String> getUserInput () {       
        Map<String, String> result = new HashMap<String, String>();
        result.put(myTextLabel.toString(), myTextArea.getText());
        return result;
    }

}
