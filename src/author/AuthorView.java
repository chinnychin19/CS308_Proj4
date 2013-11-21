package author;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import author.Menu;
import author.listeners.LaunchPlayerWizardListener;
import author.listeners.LaunchWizardListener;
import author.wizard.Wizard;
import author.wizardState.*;
import constants.Constants;


public class AuthorView extends JFrame {
    private List<AbstractWizardState> myWizardStates;

    private AuthorView av;
    

    public static final String TITLE = "Authoring View";
    public static final String LAUNCH_WIZARD = "Launch Wizard";

    public AuthorView () {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setPreferredSize(Constants.FRAME_SIZE);
        this.setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new EntitySubMenu("New Entity"));
        fileMenu.add(new JMenuItem("Choose Alternate Template (JSON)"));
        fileMenu.add(new JMenuItem("Load Existing Game (JSON)"));
        
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenu("Edit Existing Entity"));
        
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        //Set the menu bar to the frame.
        this.setJMenuBar(menuBar);

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

}
