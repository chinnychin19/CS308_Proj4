package author.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.json.simple.JSONObject;
import author.listeners.AddMatrixEntryListener;
import constants.Constants;


@SuppressWarnings("serial")
public class MatrixPanel extends AbstractTextPanel {

    private JPanel myButtonPanel;
    private JTable myMatrixTable;
    private TableModel myDataModel;
    private final String EXPAND_TEXT = "Add another entry";
    private int STARTING_ROWS = 2;
    private int STARTING_COLUMNS = 2;

    public MatrixPanel (String label) {

        super(label);
        myTextLabel = new JLabel(label);

        // Create grid
        TableModel myDataModel = new AbstractTableModel() {
            
            private Object[] data = null;
            
            public int getColumnCount () {
                return STARTING_COLUMNS;
            }

            public int getRowCount () {
                return STARTING_ROWS;
            }

            public Object getValueAt (int row, int col) {
                return 0;
            }
            
            public boolean isCellEditable(int row, int col) { 
                return true; 
            }

        };
        myMatrixTable = new JTable(myDataModel);

        // Create buttons
        myButtonPanel = new JPanel();
        initButtons();
        this.add(myButtonPanel);

        // Add grid to panel
        this.add(myMatrixTable);
    }

    public void initButtons () {
        JButton expandMatrixButton = new JButton(EXPAND_TEXT);
        expandMatrixButton.addActionListener(new AddMatrixEntryListener(myMatrixTable, myDataModel));
        myButtonPanel.add(expandMatrixButton);
    }

    public Map<String, String> getUserInput () {
        
        Map<String, String> matrixRepresentation = new HashMap<String, String>();
        
        for (int numCol = 1; numCol < myMatrixTable.getColumnCount(); numCol++) {
            String colName = myMatrixTable.getValueAt(0,numCol).toString();
            matrixRepresentation.put(colName, getTypeRelationship(numCol).toString());
        }
        
        System.out.print(matrixRepresentation);
        return matrixRepresentation;
    }
    
    public JSONObject getTypeRelationship(int col) {
        Map<String, String> colRelationship = new HashMap<String, String>();
        
        for (int row = 1; row <= myMatrixTable.getRowCount(); row++) {
            colRelationship.put(myMatrixTable.getValueAt(row, 0).toString(), myMatrixTable.getValueAt(row, col).toString());
        }
        
        // We now have a map defining all of a column's relationships to its rows.
        
        JSONObject result = new JSONObject(colRelationship);
        return result;
    }
    
    public TableModel getTableDataModel() {
        return myDataModel;
    }

}
