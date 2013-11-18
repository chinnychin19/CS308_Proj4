package author;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
        
        menuBar.add(new Menu("File"));
        
        this.setJMenuBar(menuBar);

        JButton launchWizardButton = new JButton(LAUNCH_WIZARD);
        launchWizardButton.addActionListener(new LaunchPlayerWizardListener());
        launchWizardButton.setPreferredSize(Constants.BUTTON_SIZE);
        this.add(launchWizardButton);

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
