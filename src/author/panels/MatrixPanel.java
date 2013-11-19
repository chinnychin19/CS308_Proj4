package author.panels;

import java.awt.GridLayout;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import constants.Constants;


@SuppressWarnings("serial")
public class MatrixPanel extends AbstractTextPanel {

    private JPanel myButtonPanel;
    private JPanel myGridPanel;

    public MatrixPanel () {

        super(Constants.MATRIX_PANEL);
        myTextLabel = new JLabel(Constants.MATRIX_PANEL + ":");

        // Create buttons
        myButtonPanel = new JPanel();
        initButtons();
        this.add(myButtonPanel);

        // Create grid
        myGridPanel = new JPanel();
        myGridPanel.setLayout(new GridLayout(myDimensions.getRowDimension(), myDimensions.getColumnDimension()));
        initGrid();
        this.add(myGridPanel);
    }

    public void initGrid () {
        JTextField a = new JTextField();
        JTextField b = new JTextField();
        JTextField c = new JTextField();
        JTextField d = new JTextField();
        JTextField e = new JTextField();
        JTextField f = new JTextField();
        JTextField g = new JTextField();
        JTextField h = new JTextField();
        JTextField i = new JTextField();
        JTextField j = new JTextField();
        JTextField k = new JTextField();

        myGridPanel.add(myTextLabel);
        myGridPanel.add(a);
        myGridPanel.add(b);
        myGridPanel.add(c);
        myGridPanel.add(d);
        myGridPanel.add(e);
        myGridPanel.add(f);
        myGridPanel.add(g);
        myGridPanel.add(h);
        myGridPanel.add(i);
        myGridPanel.add(j);
        myGridPanel.add(k);
    }

    public void initButtons () {
        JButton addRowButton = new JButton("Add Row");
        JButton addColumnButton = new JButton("Add Column");
        myButtonPanel.add(addRowButton);
        myButtonPanel.add(addColumnButton);
    }
    
    public Map<String, String> getUserInput () {       
        return null;
    }

}
