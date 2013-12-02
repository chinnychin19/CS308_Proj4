package author;

import java.awt.Color;
import java.awt.MenuBar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import author.listeners.LaunchPlayerWizardListener;
import author.listeners.LaunchWizardListener;
import author.listeners.OutputJSONListener;
import author.mapCreation.MapCreationView;
import author.listeners.WriteJSONOutputListener;
import author.model.AuthoringCache;
import author.wizard.Wizard;
import author.wizardState.*;
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

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new NewEntitySubMenu("New Entity", ac));
        fileMenu.add(new JMenuItem("Choose Alternate Template (JSON)"));
        fileMenu.add(new JMenuItem("Load Existing Game (JSON)"));
        fileMenu.add(new JMenuItem("Create New Map"));

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new EditEntitySubMenu("Edit Existing Entity", ac));

        JMenu viewMenu = new JMenu("View");
        JMenuItem item = new JMenuItem("Show Generated Output");
        item.addActionListener(new OutputJSONListener(ac));
        viewMenu.add(item);
        
        JMenuItem writeJSON = new JMenuItem("Write JSON to file");
        writeJSON.addActionListener(new WriteJSONOutputListener(ac));
        viewMenu.add(writeJSON);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        // Set the menu bar to the frame.
        this.setJMenuBar(menuBar);

        //this.add(new LevelEditorCanvas());
        this.add(new MapCreationView());
        //this.setBackground(Color.BLUE);
        
        pack();
        this.setVisible(true);
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
