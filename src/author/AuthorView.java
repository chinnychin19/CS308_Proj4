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


@SuppressWarnings("serial")
public class AuthorView extends JFrame {
	
	private AuthorView av = this;
	private AuthoringCache ac;
	public static final String TITLE = "Authoring View";
    public static final String LAUNCH_WIZARD = "Launch Wizard";

    public AuthorView () {
        initialize();
        makeMenuBar();
        initializeMainView();
        pack();
        this.setVisible(true);
    }

	private void initializeMainView() {
		JPanel mainView = new JPanel();
        
        mainView.setPreferredSize(Constants.FRAME_SIZE);
        mainView.add(new MapCreationView());
        mainView.add(new SidebarPanel(ac));
        
        this.add(mainView);
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setPreferredSize(Constants.FRAME_SIZE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
	}

    public void makeMenuBar () {
        JMenuBar menuBar = new JMenuBar();
        ac = new AuthoringCache(this);

        menuBar.add(new AuthorViewFileMenu(ac)); // File
        menuBar.add(new AuthorViewEditMenu(ac)); // Edit
        menuBar.add(new AuthorViewViewMenu(ac)); // View

        // Set the menu bar to the frame.
        this.setJMenuBar(menuBar);
    }

	public AuthorView getAuthorView(){
		if (av == null){
			return new AuthorView();
		}
		else {
			return av;
		}
	}

}
