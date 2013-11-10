package author.wizard;

import java.awt.Color;
import javax.swing.JPanel;
import author.ImagePicker;
import author.panels.NamePanel;

@SuppressWarnings("serial")
public class PlayerWizard extends Wizard {

    public PlayerWizard () {
        super();
    }

    @Override
    public void initComponents () {
        this.myCardPanel.add(new NamePanel());
        this.myCardPanel.add(new ImagePicker());
    }

}
