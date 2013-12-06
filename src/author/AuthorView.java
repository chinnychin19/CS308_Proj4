package author;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import author.listeners.OutputJSONListener;
import author.mapCreation.MapCreationView;
import author.listeners.WriteJSONOutputListener;
import author.model.AuthoringCache;
import author.wizardState.AbstractWizardState;
import constants.Constants;


@SuppressWarnings("serial")
public class AuthorView extends JFrame {
    private List<AbstractWizardState> myWizardStates;

    private AuthorView av = this;

    public static final String TITLE = "Authoring View";
    public static final String LAUNCH_WIZARD = "Launch Wizard";

    public AuthorView () {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setPreferredSize(Constants.FRAME_SIZE);
        this.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        AuthoringCache ac = new AuthoringCache(this);

        JMenu fileMenu = new JMenu(Constants.FILE_MENU);
        fileMenu.add(new NewEntitySubMenu(Constants.NEW_ENTITY_SUBMENU, ac));
        fileMenu.add(new JMenuItem(Constants.CHOOSE_ALTERNATE_TEMPLATE));
        fileMenu.add(new JMenuItem(Constants.LOAD_EXISTING_GAME));
        fileMenu.add(new JMenuItem(Constants.CREATE_NEW_MAP));

        JMenu editMenu = new JMenu(Constants.EDIT_MENU);
        editMenu.add(new EditEntitySubMenu(Constants.EDIT_ENTITY_SUBMENU, ac));

        JMenu viewMenu = new JMenu(Constants.VIEW_MENU);
        JMenuItem item = new JMenuItem(Constants.SHOW_GENERATED_OUTPUT);
        item.addActionListener(new OutputJSONListener(ac));
        viewMenu.add(item);

        JMenuItem writeJSON = new JMenuItem(Constants.WRITE_JSON_TO_FILE);
        writeJSON.addActionListener(new WriteJSONOutputListener(ac));
        viewMenu.add(writeJSON);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        // Set the menu bar to the frame.
        this.setJMenuBar(menuBar);

        this.add(new MapCreationView());

        pack();
        this.setVisible(true);
    }

    public List<AbstractWizardState> getWizardStates () {
        return myWizardStates;
    }

    public AuthorView getAuthorView () {
        if (av == null) {
            return new AuthorView();
        }
        else {
            return av;
        }
    }

    public void update () {
        ((EditEntitySubMenu) av.getJMenuBar().getMenu(1).getItem(0)).refreshMenu();
    }

}
