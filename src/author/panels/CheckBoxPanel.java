package author.panels;

/**
 * This is the wizardPanel that is used for results that are on/off, true/false,
 * 0/1, etc.
 * 
 * @author mray90
 * 
 */

import java.awt.AWTEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import author.listeners.CheckBoxListener;


public class CheckBoxPanel extends AbstractToggleButtonPanel{

	private static final String FALSE = "false";

	private static final String TRUE = "true";

	private static final long serialVersionUID = -257454601339596984L;
	
	private JLabel myCheckBoxLabel;
    private Map<String,String> myBoxStates;
        
    public CheckBoxPanel(String label){
        super("CheckBox", CheckBoxListener.getInstance());
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        String[] input = label.split("~");
        
        myCheckBoxLabel = new JLabel(input[0] + ":");
        myBoxStates = new HashMap<String,String>();
        
        this.add(myCheckBoxLabel);
        
        if (input.length > 1) {
        	String[] boxes = input[1].split("\\.");
        	for (String s : boxes) {
        		addBoxes(s);
            }
        }
    }
    
    public void addBoxes (String ... boxLabels) {
        for (String s : boxLabels) {
            JCheckBox cb = new JCheckBox(s);
            cb.addActionListener((ActionListener) myEventListener);
            myBoxStates.put(s, FALSE);
            this.add(cb);
        }
    }
    
    public void updateSelectionState (AWTEvent e) {
        String state = (myBoxStates.get(((JCheckBox) e.getSource()).getText()).equals(TRUE)) ? FALSE : TRUE;
        myBoxStates.put(((JCheckBox) e.getSource()).getText(), state);
    }
    
    @Override
    public Map<String, String> getUserInput () {
        Map<String, String> result = new HashMap<String, String>();
        for (String s : myBoxStates.keySet()) {
        	if (myBoxStates.get(s).equals(TRUE)) {
        		String label = myCheckBoxLabel.getText();
        		result.put(s,label.substring(0, label.length()-1));
        	}
        }
        return result;
    }
}
