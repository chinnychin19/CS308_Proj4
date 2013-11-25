package author.panels;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;


// import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public abstract class AbstractTextPanel extends AbstractWizardPanel {

    public AbstractTextPanel (String type) {
        // Default dimension size is 1x1
        super(type);
    }

    public AbstractTextPanel (String type, int rows, int columns) {
        // Constructor for lists/matrices that may not be 1x1 when initialized
        super(type, rows, columns);
    }

}
