package author.wizard;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import constants.Constants;


/**
 * Class that defines a generic Wizard object. These
 * are dynamically created via a user-defined JSON template
 * that they may load in.
 * 
 * The Wizard uses a CardLayout to present each JSON
 * object requiring definition as a separate set of panels. The
 * user may define these attributes in a JSON template file.
 * 
 * These Wizards are actually constructed by the WizardBuilder
 * class.
 * 
 * @author Michael Marion
 * 
 */
@SuppressWarnings("serial")
public class Wizard extends JDialog {

    CardLayout myCardLayout;
    JPanel myCardPanel;
    JPanel myButtonPanel;

    JButton myNextButton;
    JButton myBackButton;

    // private String myTitle;
    private String myObjectName;

    private final static String NEXT = "Next";
    private final static String PREVIOUS = "Back";

    private final Dimension DIALOG_DIMENSION = new Dimension(800, 600);
    private final Dimension BUTTON_SIZE = new Dimension(128, 32);

    public Wizard (String type) {
        this.setPreferredSize(DIALOG_DIMENSION);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setName(Constants.WIZARD_STRING);

        myObjectName = type;

        init();
        addButtons();

        pack();
        this.setVisible(true);
    }

    /**
     * I
     */
    public void init () {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        myCardLayout = new CardLayout();
        myCardPanel = new JPanel();
        JScrollPane jsp = new JScrollPane(myCardPanel);
        

        this.add(jsp);

        myCardPanel.setLayout(myCardLayout);
    }

    /**
     * Add a "next" and "back" button to the
     * wizard, allowing the user to transition
     * between CardPanels. Specifically, this
     * method adds the two buttons to a JPanel,
     * and then adds the JPanel to each 
     * wizard CardPanel.
     */
    public void addButtons () {

        myButtonPanel = new JPanel(new FlowLayout());

        // Add the "next" button
        myNextButton = new JButton(NEXT);
        myNextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                myCardLayout.next(myCardPanel);
            }

        });
        myNextButton.setSize(BUTTON_SIZE);
        myButtonPanel.add(myNextButton);

        // Add the "back" button
        myBackButton = new JButton(PREVIOUS);
        myBackButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                myCardLayout.previous(myCardPanel);
            }

        });
        myBackButton.setSize(BUTTON_SIZE);
        myButtonPanel.add(myBackButton);

        this.add(myButtonPanel);
    }

    public JPanel getCardPanel () {
        return myCardPanel;
    }

    public String getObjectName () {
        return myObjectName;
    }

}
