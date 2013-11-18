package author.panels;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextArea;

public class NumberVerifier extends InputVerifier {

    @Override
    public boolean verify (JComponent arg0) {
    	JTextArea text = (JTextArea) arg0;
        return text.getText().matches("-?\\d+(\\.\\d+)?");
    }

}
