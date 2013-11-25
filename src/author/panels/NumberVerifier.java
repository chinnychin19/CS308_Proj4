package author.panels;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class NumberVerifier extends InputVerifier {

    @Override
    public boolean verify (JComponent arg0) {
        JTextField text = (JTextField) arg0;
        return text.getText().matches("-?\\d+(\\.\\d+)?");
    }

}
