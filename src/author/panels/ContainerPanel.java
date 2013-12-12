package author.panels;

/**
 * This the panel that contains our wizardPanels when the user is creating
 * a game with the game engine.
 * 
 */

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContainerPanel extends JPanel {

	private static final long serialVersionUID = 7553730168577820900L;
	
	private JLabel myLabel;
    private String myType;

    public ContainerPanel (String label, String type) {
        myLabel = new JLabel(label);
        myType = type;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(myLabel);
    }

    public String getLabel () {
        return myLabel.getText();
    }

    public String getType () {
        return myType;
    }
}
