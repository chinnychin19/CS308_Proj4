package author.panels;

import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NumberPanel extends AbstractWizardPanel {

    private JLabel myNumberLabel = new JLabel("Number:");
    private JTextField myNumberField;
    
    public NumberPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        myNumberField = new JTextField();
        myNumberField.setInputVerifier(new NumberVerifier());
        
        this.add(myNumberLabel);
        this.add(myNumberField);
    }
    
    @Override
    public Map<String, String> getUserInput () {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Number", myNumberField.getText());
        return map;
    }

}
