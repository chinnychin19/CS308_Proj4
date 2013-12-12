package author.listeners;

/**
 * Listener for the SidePanel that lists all available items that can
 * be added to the map creator view.
 * 
 * @author weskpga
 */

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import author.PlaceableObjectsList;


public class PlaceableObjectsListListener implements ListSelectionListener {

    private PlaceableObjectsList myPlaceableObjectsList;

    public PlaceableObjectsListListener (PlaceableObjectsList pol) {
        myPlaceableObjectsList = pol;
    }

    @Override
    public void valueChanged (ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

        int firstIndex = e.getFirstIndex();
        boolean isAdjusting = e.getValueIsAdjusting();
        boolean selectionEmpty = lsm.isSelectionEmpty();

        if (!isAdjusting && !selectionEmpty) {
            myPlaceableObjectsList.chooseListElement(firstIndex);
        }
    }

}
