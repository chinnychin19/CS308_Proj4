package author.panels;

/**
 * This class verifies that a NumberPanel input is a proper input
 * 
 * @author mray90
 * 
 */

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;


public class NumberVerifier extends InputVerifier {

    @Override
    public boolean verify (JComponent arg0) {
        JTextField text = (JTextField) arg0;
        return text.getText().matches("-?\\d+(\\.\\d+)?");
    }

}
