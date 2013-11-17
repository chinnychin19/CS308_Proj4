package author.wizard;

//import java.awt.Color;
//import javax.swing.JPanel;
import author.ImagePicker;
import author.panels.FinishPanel;
//import author.panels.TextPanel;


@SuppressWarnings("serial")
public class PlayerWizard extends Wizard {

    public PlayerWizard () {
        super();
    }

    @Override
    public void initComponents () {
        //this.myCardPanel.add(new TextPanel());
        this.myCardPanel.add(new ImagePicker());
        this.myCardPanel.add(new FinishPanel());
    }

}
