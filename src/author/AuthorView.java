
package author;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import author.listeners.OutputJSONListener;
import author.mapCreation.MapCreationView;
import author.menuItems.AuthorViewEditMenu;
import author.menuItems.AuthorViewFileMenu;
import author.menuItems.AuthorViewViewMenu;
import author.menuItems.EditEntitySubMenu;
import author.menuItems.NewEntitySubMenu;
import author.listeners.WriteJSONOutputListener;
import author.model.AuthoringCache;
import constants.Constants;


@SuppressWarnings("serial")
public class AuthorView extends JFrame {
    //private AuthorViewFileMenu data = new AuthorViewFileMenu(this);

	public static final String TITLE = "Authoring View";
    public static final String LAUNCH_WIZARD = "Launch Wizard";

    public AuthorView () {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setPreferredSize(Constants.FRAME_SIZE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());

        makeMenuBar();
        
        JPanel mainView = new JPanel();
        mainView.setPreferredSize(Constants.FRAME_SIZE);
        
        mainView.add(new MapCreationView());
        mainView.add(new SidebarPanel());
        
        this.add(mainView);

        pack();
        this.setVisible(true);
    }

    public void makeMenuBar () {
        JMenuBar menuBar = new JMenuBar();
        AuthoringCache ac = new AuthoringCache(this);

        menuBar.add(new AuthorViewFileMenu(ac));
        menuBar.add(new AuthorViewEditMenu(ac));
        menuBar.add(new AuthorViewViewMenu(ac));

        // Set the menu bar to the frame.
        this.setJMenuBar(menuBar);
    }

	private JMenu createViewMenu(AuthoringCache ac) {
		JMenu viewMenu = new JMenu(Constants.VIEW_MENU);
        
        addShowGeneratedOutput(ac, viewMenu);

        addWriteJSON(ac, viewMenu);
		return viewMenu;
	}

	private void addWriteJSON(AuthoringCache ac, JMenu viewMenu) {
		JMenuItem writeJSON = new JMenuItem(Constants.WRITE_JSON_TO_FILE);
        writeJSON.addActionListener(new WriteJSONOutputListener(ac));
        viewMenu.add(writeJSON);
	}

	private void addShowGeneratedOutput(AuthoringCache ac, JMenu viewMenu) {
		JMenuItem showOutputItem = new JMenuItem(Constants.SHOW_GENERATED_OUTPUT);
        showOutputItem.addActionListener(new OutputJSONListener(ac));
        viewMenu.add(showOutputItem);
	}

	private JMenu createEditMenu(AuthoringCache ac) {
		JMenu editMenu = new JMenu(Constants.EDIT_MENU);
        EditEntitySubMenu editEntityItem = new EditEntitySubMenu(Constants.EDIT_ENTITY_SUBMENU, ac);
        editMenu.add(editEntityItem);
		return editMenu;
	}

	private JMenu createFileMenu(AuthoringCache ac) {
		JMenu fileMenu = new JMenu(Constants.FILE_MENU);
        
        addNewEntitySubMenu(ac, fileMenu);
        addChooseAltTemplate(fileMenu);
        addLoadExistingGame(fileMenu);
        addCreateNewMap(fileMenu);
		return fileMenu;
	}

	private void addNewEntitySubMenu(AuthoringCache ac, JMenu fileMenu) {
		JMenuItem newEntitySubMenu = new NewEntitySubMenu(Constants.NEW_ENTITY_SUBMENU, ac);
        fileMenu.add(newEntitySubMenu);
	}

    
	private void addCreateNewMap(JMenu fileMenu) {
		JMenuItem createNewMapItem = new JMenuItem(Constants.CREATE_NEW_MAP);
        createNewMapItem.addActionListener(new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked 'Create New Map'");
			}
        });
        fileMenu.add(createNewMapItem);
	}

	private void addLoadExistingGame(JMenu fileMenu) {
		JMenuItem loadExistingGameItem = new JMenuItem(Constants.LOAD_EXISTING_GAME);
        loadExistingGameItem.addActionListener(new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked 'Load Existing Game'");
			}
        });
        fileMenu.add(loadExistingGameItem);
	}


	private void addChooseAltTemplate(JMenu fileMenu) {
		JMenuItem chooseAltTemplateItem = new JMenuItem(Constants.CHOOSE_ALTERNATE_TEMPLATE);
        chooseAltTemplateItem.addActionListener(new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked 'Choose Alternate Template'");
			}
        });
        fileMenu.add(chooseAltTemplateItem);
	}

}
