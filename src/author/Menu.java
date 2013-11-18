package author;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class Menu extends JMenu {
    private JMenuItem NEW;
    private JMenuItem LOAD;
    private JMenuItem SAVE;
    private JMenuItem HELP;

    public Menu (String title) {
        // Call to JMenu super constructor
        super(title);

        // Add the "new" menu item        
        this.add(new WizardSubMenu("New"));

        // Add the "load" menu item
        LOAD = new JMenuItem("Load wizard from file");
        // LOAD.addActionListener(new LoadListener());
        this.add(LOAD);
    }
}
