package author.panels;

/**
 * This is an abstract class that is extended by WordPanel, ListPanel, NumberPanel,
 * and MatrixPanel.  It is extended by all classes that take some form of user typing
 * in order to get a result.
 * 
 * @author mray90
 * 
 */

import java.awt.AWTEvent;
import java.util.EventListener;


public abstract class AbstractToggleButtonPanel extends AbstractWizardPanel {

	private static final long serialVersionUID = -2440277590050770853L;
	
	protected EventListener myEventListener;

    public AbstractToggleButtonPanel (String type, EventListener el) {
        super(type);
        myEventListener = el;
    }

    public abstract void updateSelectionState (AWTEvent e);

}
