package author.panels;

/**
 * Once the finish button is clicked on the FinishPanel, the engine creates the
 * JSON file that represents the game that was just made.  This panel must always
 * be called at the end of a game creation session.
 * 
 */

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import constants.Constants;

import author.listeners.FinishListener;
import author.model.AuthoringCache;
import author.wizard.Wizard;


public class FinishPanel extends JPanel {

	private static final long serialVersionUID = -6488636969169881653L;
	
	private JLabel FINISH_TEXT = new JLabel(Constants.FINISH_PROJECT_PROMPT);
    private JButton FINISH_BUTTON = new JButton(Constants.FINISH_STRING);
    private AuthoringCache myCache;
    
    public JButton getButton() {
    	return FINISH_BUTTON;
    }
    
    public FinishPanel(AuthoringCache cache) {
        this.add(FINISH_TEXT);
        myCache = cache;
    }
    
    private void initialize() {
    	FINISH_BUTTON.addActionListener(
    			new FinishListener((Wizard)SwingUtilities.getAncestorNamed(Constants.WIZARD_STRING,this),myCache));
        this.add(FINISH_BUTTON);
    }
    
    public void init() {
    	initialize();
    }
}
