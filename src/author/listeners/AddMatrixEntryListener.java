package author.listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


public class AddMatrixEntryListener implements ActionListener {

    private JTable myMatrixTable;
    private TableModel myDataModel;

    public AddMatrixEntryListener (JTable table, TableModel tableModel) {
        myMatrixTable = table;
        myDataModel = tableModel;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        myMatrixTable.addColumn(new TableColumn());
       // myDataModel.addRow();
    }

}
