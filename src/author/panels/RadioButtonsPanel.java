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

import constants.Constants;
import author.listeners.RadioButtonListener;


public class RadioButtonsPanel extends AbstractToggleButtonPanel {

	private static final long serialVersionUID = 5998228945580381950L;
	
	private JLabel myRadioButtonsLabel;
    private ButtonGroup myButtonGroup;
    private String mySelectedButton;

    public RadioButtonsPanel (String label) {
        super(Constants.RADIO_BUTTONS_KEYWORD, RadioButtonListener.getInstance());
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
        } else {
            this.add(new JLabel(Constants.NONE_CREATED));
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
    }

    public Map<String, String> getUserInput () {
    	Map<String, String> result = new HashMap<String, String>();
        String label = myRadioButtonsLabel.getText();
        result.put(label.substring(0, label.length()-1), mySelectedButton);
        return result;
    }

}
