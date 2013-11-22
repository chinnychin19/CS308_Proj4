package author.panels;

import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.Map;

import javax.swing.*;

import constants.Constants;
import author.listeners.FinishListener;
import author.wizard.Wizard;

@SuppressWarnings("serial")
public class ContainerPanel extends JPanel {
	private JLabel  myLabel;
	private String myType;
	
	public ContainerPanel (String label, String type) {
		myLabel = new JLabel(label);
		myType = type;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(myLabel);
    }
	
	public String getLabel() {
		return myLabel.getText();
	}
	
	public String getType() {
		return myType;
	}
}
