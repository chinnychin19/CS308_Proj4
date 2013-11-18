package author.panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import constants.Constants;

@SuppressWarnings("serial")
public class ListPanel extends AbstractTextPanel {
    
    public ListPanel() {
    	super(Constants.LIST_PANEL);
    	myTextLabel = new JLabel(Constants.LIST_PANEL + ":");
    	
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        // TODO : Figure out how to prompt user for list length
        
        JTextArea myTextArea = new JTextArea();
        myTextArea.setPreferredSize(Constants.TEXT_AREA_SIZE);
        
        this.add(myTextLabel);
        this.add(myTextArea);
    }

}
