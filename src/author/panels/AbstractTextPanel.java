package author.panels;

/**
 * This is an abstract class that is extended by WordPanel, ListPanel, NumberPanel,
 * and MatrixPanel.  It is extended by all classes that take some form of user typing
 * in order to get a result.
 * 
 * @author weskpga
 * 
 */


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
