package author.panels;

/**
 * This allows the user to choose an option from a pre-defined list of options.
 * 
 * @author mray90
 * 
 */

import java.awt.AWTEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import author.listeners.RadioButtonListener;


@SuppressWarnings("serial")
public class RadioButtonsPanel extends AbstractToggleButtonPanel {

    private JLabel myRadioButtonsLabel;
    private ButtonGroup myButtonGroup;
    private String mySelectedButton;

    public RadioButtonsPanel (String label) {
        super("RadioButtons", RadioButtonListener.getInstance());
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        String[] input = label.split("~");
        
        myRadioButtonsLabel = new JLabel(input[0] + ":");
        myButtonGroup = new ButtonGroup();
        
        this.add(myRadioButtonsLabel);
        
        if (input.length > 1) {
        	String[] buttons = input[1].split("\\.");
        	for (String s : buttons) {
        		addButtons(s);
            }
        }
        
    }

    public void addButtons (String ... buttonLabels) {
        for (String s : buttonLabels) {
            JRadioButton rb = new JRadioButton(s);
            myButtonGroup.add(rb);
            rb.addActionListener((ActionListener) myEventListener);
            this.add(rb);
        }
    }

    public void updateSelectionState (AWTEvent e) {
        mySelectedButton = ((JRadioButton) e.getSource()).getText();
        // System.out.println("Selected button is now " + mySelectedButton);
    }

    public Map<String, String> getUserInput () {
    	Map<String, String> result = new HashMap<String, String>();
        String label = myRadioButtonsLabel.getText();
        result.put(label.substring(0, label.length()-1), mySelectedButton);
        return result;
    }

}
