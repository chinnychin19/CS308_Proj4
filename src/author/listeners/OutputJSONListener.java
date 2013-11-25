package author.listeners;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import author.model.AuthoringCache;


public class OutputJSONListener implements ActionListener {

    private AuthoringCache myCache;

    public OutputJSONListener (AuthoringCache av) {
        myCache = av;
    }

    @Override
    public void actionPerformed (ActionEvent arg0) {
        JFrame frame = new JFrame();
        JTextField text = new JTextField(myCache.getRawJSON().toString());
        text.setEditable(false);
        frame.add(text);
        frame.pack();
        frame.setVisible(true);
    }

}
