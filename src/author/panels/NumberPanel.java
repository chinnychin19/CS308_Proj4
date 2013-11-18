package author.panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NumberPanel extends AbstractTextPanel {

    private JLabel myNumberLabel = new JLabel("Number:");
    private JTextField myNumberField;
    
    public NumberPanel(){
        super("Number");
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        myNumberField = new JTextField();
        myNumberField.setInputVerifier(new NumberVerifier());
        
        this.add(myNumberLabel);
        this.add(myNumberField);
    }

}
