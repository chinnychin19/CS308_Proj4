package author.panels;

import java.awt.AWTEvent;
import java.awt.event.ItemEvent;
import java.util.EventListener;
import java.util.Map;
import author.listeners.CheckBoxListener;


public abstract class AbstractToggleButtonPanel extends AbstractWizardPanel {

    protected EventListener myEventListener;

    public AbstractToggleButtonPanel (String type, EventListener el) {
        super(type);
        myEventListener = el;
    }

    public abstract void updateSelectionState (AWTEvent e);

}
