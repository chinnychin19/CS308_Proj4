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
	public ContainerPanel (String label) {
		myLabel = new JLabel(label);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(myLabel);
    }
}
