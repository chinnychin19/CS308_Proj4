package author.panels;

/**
 * Wizard panel that gives the user the ability to define a list.
 * 
 * @author weskpga
 * 
 */

import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import constants.Constants;


public class ListPanel extends AbstractTextPanel {

	private static final long serialVersionUID = 7313377313875680872L;

	public ListPanel () {
        super(Constants.LIST_PANEL);
        myTextLabel = new JLabel(Constants.LIST_PANEL + ":");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JTextArea myTextArea = new JTextArea();
        myTextArea.setPreferredSize(Constants.TEXT_AREA_SIZE);

        this.add(myTextLabel);
        this.add(myTextArea);
    }

    @Override
    public Map<String, String> getUserInput () {
        return null;
    }

}
