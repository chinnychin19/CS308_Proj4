package author.panels;

/**
 * This is an abstract class that is extended by AbstractTextPanel, AbstractToggleButtonPanel, 
 * and ImagePanel.  It represents every type of input that our engine can write to a JSON file
 * including text, checkboxes, radio buttons, lists, matrices, Images, and numbers.
 * 
 * @author weskpga
 * 
 */

import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public abstract class AbstractWizardPanel extends JPanel {

	private static final long serialVersionUID = -7974954435721491712L;
	
	protected UserInputDimension myDimensions;
    protected JLabel myTextLabel;
    private String myType;

    public AbstractWizardPanel (String type) {
        // Default dimension size is 1x1
        myDimensions = new UserInputDimension(3, 3);
        myType = type;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public AbstractWizardPanel (String type, int rows, int columns) {
        myDimensions = new UserInputDimension(rows, columns);
        myType = type;
    }

    public String getMyType () {
        return myType;
    }

    public void addRow () {
        myDimensions.addRow();
    }

    public void addColumn () {
        myDimensions.addColumn();
    }

    public void expandSquareMatrix () {
        addRow();
        addColumn();
    }

    public abstract Map<String, String> getUserInput ();

    protected class UserInputDimension {

        private int rowDimension;
        private int columnDimension;

        public UserInputDimension (int rows, int columns) {
            rowDimension = rows;
            columnDimension = columns;
        }

        public void addRow () {
            rowDimension += 1;
        }

        public void addColumn () {
            columnDimension += 1;
        }

        public int getRowDimension () {
            return rowDimension;
        }

        public int getColumnDimension () {
            return columnDimension;
        }

    }

}
