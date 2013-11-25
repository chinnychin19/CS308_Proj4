package author;

import game.model.AbstractViewableObject;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import author.listeners.PlaceableObjectsListListener;

public class PlaceableObjectsList extends JPanel {
    
    private JList myList;
    private DefaultListModel myListModel;
    private JScrollPane myListScroller;
    private AbstractViewableObject myCurrentPlaceableObject;
    
    public PlaceableObjectsList() {
        myListModel = new DefaultListModel();
        addListElements();
        myList = new JList(myListModel);
        myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myList.setLayoutOrientation(JList.VERTICAL);
        myList.setVisibleRowCount(-1);
        
        ListSelectionModel lsm = myList.getSelectionModel();
        lsm.addListSelectionListener(
                                new PlaceableObjectsListListener(this));
        
        myListScroller = new JScrollPane(myList);
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(myListScroller);
    }

    private void addListElements () {
        // TODO Auto-generated method stub
        
    }

    public void chooseListElement (int index) {
        // TODO Auto-generated method stub
        myCurrentPlaceableObject = (AbstractViewableObject) myListModel.get(index);
    }



}
