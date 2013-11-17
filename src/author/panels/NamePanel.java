package author.panels;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import org.json.simple.JSONObject;


@SuppressWarnings("serial")
public class NamePanel extends AbstractWizardPanel {
    
    private JLabel myNameLabel = new JLabel("Name:");
    private JTextArea myNameArea;

    public NamePanel() {
        super("Name");
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        myNameArea = new JTextArea();
        
        this.add(myNameLabel);
        this.add(myNameArea);
    }
    
    public Map<String, String> getUserInput() {
        //JSONObject obj = new JSONObject();
        //obj.put("Name", myNameArea.getText());
        //return obj;
        Map<String, String> map = new HashMap<String, String>();
        map.put("Name", myNameArea.getText());
        return map;
    }
    
}
