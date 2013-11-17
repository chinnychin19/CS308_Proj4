package author.panels;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractWizardPanel extends JPanel {

	protected JLabel myTextLabel;
	private String myType;
	
	public AbstractWizardPanel(String type){
		myType = type;
	}
	
    public abstract Map<String, String> getUserInput();
    
	public String getMyType(){
		return myType;
	}
}
