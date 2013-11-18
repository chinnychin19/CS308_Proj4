package author.panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import constants.Constants;

@SuppressWarnings("serial")
public class MatrixPanel extends AbstractTextPanel{
    
    public MatrixPanel() {
    	super(Constants.MATRIX_PANEL);
    	myTextLabel = new JLabel(Constants.MATRIX_PANEL + ":");
    	
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        // TODO : Figure out how to prompt user for matrix dimensions
        
        myTextArea = new JTextArea();
        
        this.add(myTextLabel);
        this.add(myTextArea);
    }
	
}
