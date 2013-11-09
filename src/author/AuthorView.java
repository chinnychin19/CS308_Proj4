package author;

import java.util.List;
import javax.swing.JFrame;
import author.wizardState.*;

public abstract class AuthorView extends JFrame {
    private List<AbstractWizardState> myWizardStates;

    public abstract void generateXML ();

    public abstract void nextState ();

    public abstract void prevState ();

}
