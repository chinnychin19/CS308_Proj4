package author.panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import constants.Constants;

@SuppressWarnings("serial")
public class NumberPanel extends AbstractTextPanel {
    
    public NumberPanel(){
        super(Constants.NUMBER_PANEL);
        myTextLabel = new JLabel(Constants.NUMBER_PANEL + ":");
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        myTextArea = new JTextArea();
        myTextArea.setInputVerifier(new NumberVerifier());
        
        this.add(myTextLabel);
        this.add(myTextArea);
    }

}
