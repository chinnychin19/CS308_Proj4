package author;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import constants.Constants;

public class SidebarPanel extends JPanel {

    public SidebarPanel() {
        this.setPreferredSize(Constants.SIDEBAR_SIZE);
        this.setBackground(Color.white);
    }
    
}
