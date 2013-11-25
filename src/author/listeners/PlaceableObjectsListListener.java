package author.listeners;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import author.PlaceableObjectsList;

public class PlaceableObjectsListListener implements ListSelectionListener{
    
    private PlaceableObjectsList myPlaceableObjectsList;
    
    public PlaceableObjectsListListener(PlaceableObjectsList pol){
        myPlaceableObjectsList=pol;
    }

    @Override
    public void valueChanged (ListSelectionEvent e) {
        // TODO Auto-generated method stub
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        
        
        int firstIndex = e.getFirstIndex();
        boolean isAdjusting = e.getValueIsAdjusting();
        boolean selectionEmpty = lsm.isSelectionEmpty();
        
        if (!isAdjusting && !selectionEmpty) {

            myPlaceableObjectsList.chooseListElement(firstIndex);
        }
    }

}
