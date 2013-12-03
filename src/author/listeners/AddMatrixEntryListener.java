package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


public class AddMatrixEntryListener implements ActionListener {

    private JTable myMatrixTable;

    public AddMatrixEntryListener (JTable table, TableModel tableModel) {
        myMatrixTable = table;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        myMatrixTable.addColumn(new TableColumn());
    }

}
