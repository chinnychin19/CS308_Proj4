package author.listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMatrixEntryListener implements ActionListener {

    @Override
    public void actionPerformed (ActionEvent e) {
        ((Component) e.getSource()).getParent();
    }

}
