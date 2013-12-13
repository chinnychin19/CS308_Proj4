package author.panels;

/**
 * This the panel that contains our wizardPanels when the user is creating
 * a game with the game engine.
 * 
 */

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ListContainerPanel extends JPanel {

	private static final long serialVersionUID = 3693646897152050071L;
	
	private JLabel myLabel;
    private String myType;

    public ListContainerPanel () {
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
