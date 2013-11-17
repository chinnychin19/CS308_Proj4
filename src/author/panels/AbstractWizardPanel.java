package author.panels;

import java.util.Map;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractWizardPanel extends JPanel {

    public abstract Map<String, String> getUserInput();
}
