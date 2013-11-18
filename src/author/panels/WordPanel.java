package author.panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import constants.Constants;


@SuppressWarnings("serial")
public class WordPanel extends AbstractTextPanel {

    public WordPanel () {
        super(Constants.WORD_PANEL);
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTextLabel = new JLabel(Constants.WORD_PANEL + ":");

        JTextField myTextField = new JTextField();
        myTextField.setPreferredSize(Constants.TEXT_AREA_SIZE);

        this.add(myTextLabel);
        this.add(myTextField);
    }

}
