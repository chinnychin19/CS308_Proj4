package author.panels;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
//import org.json.simple.JSONObject;


@SuppressWarnings("serial")
public abstract class AbstractTextPanel extends AbstractWizardPanel {
    
    //private JLabel myTextLabel = new JLabel("Text:");
    protected JTextArea myTextArea;

    public AbstractTextPanel(String type) {
    	super(type);
        /*this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        myTextArea = new JTextArea();
        
        this.add(myTextLabel);
        this.add(myTextArea);*/
    }
    
    public Map<String, String> getUserInput() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(getMyType(), myTextArea.getText());
        return map;
    }
    
}
