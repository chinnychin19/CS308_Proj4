package author;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import author.mapCreation.MapCreationView;
import author.menuItems.AuthorViewEditMenu;
import author.menuItems.AuthorViewFileMenu;
import author.menuItems.AuthorViewViewMenu;
import author.model.AuthoringCache;
import constants.Constants;


/***
 * GUI superclass that handles the majority of the activity
 * in the authoring engine. Consists of a
 * 
 * @author Michael Marion, Wes Koorbusch, Robert Ansel
 * 
 */
@SuppressWarnings("serial")
public class AuthorView extends JFrame {

    private AuthorView av = this;
    private AuthoringCache ac;
    private MapCreationView mapCreationView;
    private SidebarPanel sidebarPanel;
    public static final String TITLE = "Game Editor";
    public static final String LAUNCH_WIZARD = "Launch Wizard";

    /**
     * Main constructor.
     */
    public AuthorView () {
        setFrameAttributes();
        makeMenuBar();
        initializeMainView();
        // Pack and set GUI to true
        pack();
        this.setVisible(true);
    }

    private void initializeMainView () {
        JPanel mainView = new JPanel();

        mainView.setPreferredSize(Constants.FRAME_SIZE);
        
        mapCreationView = new MapCreationView();
        mainView.add(mapCreationView);
        
        sidebarPanel = new SidebarPanel(ac);
        mainView.add(sidebarPanel);

        this.add(mainView);
    }

    /**
     * Initialize JFrame attributes.
     */
    private void setFrameAttributes () {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setPreferredSize(Constants.FRAME_SIZE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
    }

    /**
     * Make the menu bar.
     */
    public void makeMenuBar () {
        JMenuBar menuBar = new JMenuBar();
        ac = new AuthoringCache(this);

        menuBar.add(new AuthorViewFileMenu(ac)); // File
        menuBar.add(new AuthorViewEditMenu(ac)); // Edit
        menuBar.add(new AuthorViewViewMenu(ac)); // View

        // Set the menu bar to the frame.
        this.setJMenuBar(menuBar);
    }

    /**
     * Returns a singleton instance of this author view.
     * 
     * @return the AuthorView
     */
    public AuthorView getAuthorView () {
        if (av == null) {
            return new AuthorView();
        }
        else {
            return av;
        }
    }

}
