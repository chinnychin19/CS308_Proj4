package author;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
// import javax.swing.border.Border;
import constants.Constants;


/**
 * GUI component class that manages the sidebar panel of pre-existing objects.
 * 
 * @author Michael Marion
 * 
 */

@SuppressWarnings("serial")
public class SidebarPanel extends JPanel {

    public SidebarPanel () {
        this.setPreferredSize(Constants.SIDEBAR_SIZE);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

}
