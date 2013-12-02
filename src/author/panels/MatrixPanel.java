package author.panels;

/**
 * This wizard allows the user to define a matrix-shaped input
 * 
 */

import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.json.simple.JSONObject;
import author.listeners.AddMatrixEntryListener;

@SuppressWarnings("serial")
public class MatrixPanel extends AbstractTextPanel {

    private JPanel myButtonPanel;
    private JTable myMatrixTable;
    private TableModel myDataModel;
    private final String EXPAND_TEXT = "Add another entry";
    private int STARTING_ROWS = 3;
    private int STARTING_COLUMNS = 3;

    public MatrixPanel (String label) {

        super(label);
        myTextLabel = new JLabel(label);

        // Create grid
        TableModel myDataModel = new AbstractTableModel() {

            //private String[] columnNames = new String[STARTING_COLUMNS];
            private Object[][] data = new Object[STARTING_ROWS][STARTING_COLUMNS];

            public int getColumnCount () {
                return STARTING_COLUMNS;
            }

            public int getRowCount () {
                return STARTING_ROWS;
            }

            public Object getValueAt (int row, int col) {
                return data[row][col];
            }

            @Override
            public boolean isCellEditable (int row, int col) {
                return true;
            }

            @Override
            public void setValueAt (Object value, int row, int col) {
                data[row][col] = value;
                fireTableCellUpdated(row, col);
            }

        };
        myMatrixTable = new JTable(myDataModel);
        myDataModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged (TableModelEvent e) {
                int row = e.getFirstRow();
                int col = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                /*Object data = */model.getValueAt(row, col);
            }

        });

        // Create buttons
        myButtonPanel = new JPanel();
        initButtons();
        this.add(myButtonPanel);

        // Add grid to panel
        this.add(myMatrixTable);
    }

    public TableModel getModel () {
        return myDataModel;
    }

    public void initButtons () {
        JButton expandMatrixButton = new JButton(EXPAND_TEXT);
        expandMatrixButton
                .addActionListener(new AddMatrixEntryListener(myMatrixTable, myDataModel));
        myButtonPanel.add(expandMatrixButton);
    }

    public Map<String, String> getUserInput () {

        Map<String, String> matrixRepresentation = new HashMap<String, String>();

        for (int numCol = 1; numCol < myMatrixTable.getColumnCount(); numCol++) {
            String colName = myMatrixTable.getModel().getValueAt(0, numCol).toString();
            matrixRepresentation.put(colName, getTypeRelationship(numCol).toString());
        }

        System.out.print(matrixRepresentation);
        return matrixRepresentation;
    }

    public JSONObject getTypeRelationship (int col) {
        Map<String, String> colRelationship = new HashMap<String, String>();

        for (int row = 1; row < myMatrixTable.getRowCount(); row++) {
            colRelationship.put(myMatrixTable.getModel().getValueAt(row, 0).toString(),
                                myMatrixTable.getModel().getValueAt(row, col).toString());
        }

        // We now have a map defining all of a column's relationships to its rows.

        JSONObject result = new JSONObject(colRelationship);
        return result;
    }

    public TableModel getTableDataModel () {
        return myDataModel;
    }

}
