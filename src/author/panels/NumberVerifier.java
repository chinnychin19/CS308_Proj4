package author.panels;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class NumberVerifier extends InputVerifier {

    @Override
    public boolean verify (JComponent arg0) {
        JTextField tf = (JTextField) arg0;
        return tf.getText().matches("-?\\d+(\\.\\d+)?");
    }

}
