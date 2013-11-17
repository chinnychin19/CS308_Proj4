package author.wizard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import author.ImagePicker;


@SuppressWarnings("serial")
public class Wizard extends JDialog {

    CardLayout myCardLayout;
    JPanel myCardPanel;
    JPanel myButtonPanel;

    JButton myNextButton;
    JButton myBackButton;

    private final static String NEXT = "Next";
    private final static String PREVIOUS = "Back";

    private final Dimension DIALOG_DIMENSION = new Dimension(800, 600);
    private final Dimension BUTTON_SIZE = new Dimension(128, 32);

    public Wizard () {
        this.setPreferredSize(DIALOG_DIMENSION);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        init();
        addButtons();

        pack();
        this.setVisible(true);
    }

    public void init () {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        myCardLayout = new CardLayout();
        myCardPanel = new JPanel();

        this.add(myCardPanel);

        myCardPanel.setLayout(myCardLayout);
        
        initComponents();
    }

    public void initComponents () {
        JPanel imagePicker = new ImagePicker();

        JPanel firstCard = new JPanel();
        firstCard.setBackground(Color.GREEN);

        JPanel secondCard = new JPanel();
        secondCard.setBackground(Color.BLUE);

        JPanel thirdCard = new JPanel();
        thirdCard.setBackground(Color.RED);

        myCardPanel.add(imagePicker, "Image Picker");
        myCardPanel.add(firstCard, "Green");
        myCardPanel.add(secondCard, "Blue");
        myCardPanel.add(thirdCard, "Red");
    }

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
    
    public JPanel getMyCardPanel() {
        return myCardPanel;
    }

}
