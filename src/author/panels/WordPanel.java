package author.panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import constants.Constants;


@SuppressWarnings("serial")
public class WordPanel extends AbstractTextPanel {

    public WordPanel () {
        super(Constants.WORD_PANEL);

        myTextLabel = new JLabel(Constants.WORD_PANEL + ":");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        myTextArea = new JTextArea();
        myTextArea.setPreferredSize(Constants.TEXT_AREA_SIZE);

        this.add(myTextLabel);
        this.add(myTextArea);
    }

}
