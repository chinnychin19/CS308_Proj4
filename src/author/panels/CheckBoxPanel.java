package author.panels;

import java.awt.AWTEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import author.listeners.CheckBoxListener;

@SuppressWarnings("serial")
public class CheckBoxPanel extends AbstractToggleButtonPanel{

    private JLabel myCheckBoxLabel;
    private Map<String,String> myBoxStates;
    private ButtonGroup myButtonGroup;
        
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
            myBoxStates.put(s, "false");
            this.add(cb);
        }
    }
    
    public void updateSelectionState (AWTEvent e) {
        String state = (myBoxStates.get(((JCheckBox) e.getSource()).getText()).equals("true")) ? "false" : "true";
        myBoxStates.put(((JCheckBox) e.getSource()).getText(), state);
        // System.out.println("Selected button is now " + mySelectedButton);
    }
    
    @Override
    public Map<String, String> getUserInput () {
        Map<String, String> result = new HashMap<String, String>();
        result.putAll(myBoxStates);
        return result;
    }
}
