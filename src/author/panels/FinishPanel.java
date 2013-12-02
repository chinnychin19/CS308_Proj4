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

import author.listeners.FinishListener;
import author.model.AuthoringCache;
import author.wizard.Wizard;

@SuppressWarnings("serial")
public class FinishPanel extends JPanel {

    private JLabel FINISH_TEXT = new JLabel("Click 'finish' to create this object.");
    private JButton FINISH_BUTTON = new JButton("Finish");
    private AuthoringCache myCache;
    
    public JButton getButton() {
    	return FINISH_BUTTON;
    }
    
    public FinishPanel(AuthoringCache cache) {
        this.add(FINISH_TEXT);
        myCache = cache;
    }
    
    private void initialize() {
    	FINISH_BUTTON.addActionListener(new FinishListener((Wizard)SwingUtilities.getAncestorNamed("wizard",this),myCache));
        this.add(FINISH_BUTTON);
    }
    
    public void init() {
    	initialize();
    }
}
