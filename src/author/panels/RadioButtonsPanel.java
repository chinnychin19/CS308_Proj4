package author.panels;

import java.awt.AWTEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import author.listeners.RadioButtonListener;


public class RadioButtonsPanel extends AbstractToggleButtonPanel {

    private JLabel myRadioButtonsLabel;
    // private JCheckBox myCheckBox;
    private ButtonGroup myButtonGroup;
    private String mySelectedButton;

    public RadioButtonsPanel () {
        super("RadioButtons", RadioButtonListener.getInstance());
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myRadioButtonsLabel = new JLabel("RadioButtons:");
        myButtonGroup = new ButtonGroup();

        this.add(myRadioButtonsLabel);
        // this.add(myCheckBox);
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
        return null;
    }

}
