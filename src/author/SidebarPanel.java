package author;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import author.mapCreation.GenericTileWrapper;
import author.mapCreation.MapCreationView;
import author.model.AuthoringCache;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;


/**
 * GUI component class that manages the sidebar panel of pre-existing objects.
 * 
 * @author weskpga
 * 
 */

@SuppressWarnings("serial")
public class SidebarPanel extends JPanel implements ListSelectionListener {

    private AuthoringCache myAuthoringCache;
    private MapCreationView myMapCreationView;
    private DefaultListModel myListModel;
    private JList mySelectionList;
    private Set<String[]> myObjectAttributes = new HashSet<String[]>();
    private String[] myCurrentSelection;
    private static int exampleNumber = 0;

    public SidebarPanel (AuthoringCache ac) {
        initialize(ac);
        initListModel();
        initSelectionList();
        finalizeSidebar();
        myMapCreationView = ac.getAuthorView().getMapCreationView();
    }

    /**
     * Event listener that listens for a change in the selected value
     * in the list in the SidebarPanel. Each time this value changes,
     * a check is first performed to ensure that the new value is not
     * null, meaning that no new item was selected.
     * 
     * The listener then displays a popup asking for any additional
     * JSON information before pulling the GenericTileWrapper object
     * from the list and populating the MapCreationView's state with
     * its data.
     */
    @Override
    public void valueChanged (ListSelectionEvent arg0) {
        
        String s = JOptionPane.showInputDialog("Any additional information?");

        if (mySelectionList.getSelectedValue() != null) {

            GenericTileWrapper gtw = (GenericTileWrapper) mySelectionList.getSelectedValue();

            if (s.length() > 0 && s != null) {
                gtw.setMyAdditionalInformation(s);
            }

            myMapCreationView.setCurrentTileImage(gtw);
            myMapCreationView.setCurrentTileName(gtw);
            myMapCreationView.setCurrentTileType(gtw);
        }
    }

    public void updateList () {

        // myListModel.clear();
        myObjectAttributes.clear();

        // myAuthoringCache.mjrTest(); -DO NOT USE AGAIN-
        JSONObject template = myAuthoringCache.getRawJSON();

        // Set<String> keySet = template.keySet(); -OLD METHOD-
        Set<String> keySet = new HashSet<String>(); // TESTED
        keySet.addAll(Arrays.asList(Constants.VIEWABLE_CATEGORIES)); // TESTED

        populateInternalList(template, keySet);

        updateListModel();
    }

    private void updateListModel () {
        // TODO Auto-generated method stub
        myListModel.clear();
        for (String[] sArr : myObjectAttributes) {
            // myListModel.addElement(sArr);//[0] + " (" + sArr[1] + ")");
            // System.out.println("added that");
            myListModel.addElement(new GenericTileWrapper(sArr[0], sArr[1], sArr[2]));
        }

    }

    private void initialize (AuthoringCache ac) {
        myAuthoringCache = ac;
        this.setPreferredSize(Constants.SIDEBAR_SIZE);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void initSelectionList () {
        mySelectionList = new JList(myListModel);
        mySelectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mySelectionList.setSelectedIndex(0);
        mySelectionList.addListSelectionListener(this);
        mySelectionList.setVisibleRowCount(20);
    }

    private void finalizeSidebar () {
        JScrollPane listScrollPane = new JScrollPane(mySelectionList);
        listScrollPane.setPreferredSize(Constants.SIDEBAR_JLIST_SIZE);
        JTextArea listText = createSidebarPrompt();
        this.add(listText);
        this.add(listScrollPane);
        this.setVisible(true);
    }

    private JTextArea createSidebarPrompt () {
        JTextArea listText = new JTextArea(Constants.SIDEBAR_PROMPT_TEXT);
        listText.setLineWrap(true);
        listText.setEditable(false);
        listText.setPreferredSize(new Dimension(190, 70));
        listText.setWrapStyleWord(true);
        return listText;
    }

    @SuppressWarnings("unchecked")
    private void initListModel () {
        myListModel = new DefaultListModel();
        this.updateList();

    }

    private void populateInternalList (JSONObject template, Set<String> keySet) {
        for (Object s : keySet) {
            JSONArray locationArray = (JSONArray) template.get(s);
            // createNewListItem("Example"); // TODO: Get rid of this when done testing

            /*
             * for (String str : myAuthoringCache.getAllInstanceNamesInCategory("Things")){
             * createNewListItem(str);
             * } TEST COMPLETE
             */

            for (Object con : locationArray) {
                if (con != null) {
                    String[] attributes = new String[3];

                    attributes[0] = (String) ((JSONObject) con).get(Constants.NAME);
                    attributes[1] = (String) s;

                    System.out.println("attribute2: " + attributes[0]);
                    System.out.println("attribute1: " + attributes[1]);

                    JSONObject json = myAuthoringCache.getInstance(attributes[1], attributes[0]);
                    SmartJsonObject smartJSON;
                    try {
                        smartJSON = new SmartJsonObject(json);

                        Set<Object> JSONKeySet = smartJSON.keySet();

                        attributes[2] = "";

                        for (Object o : JSONKeySet) {
                            if (((String) o).contains("image")) {
                                attributes[2] = smartJSON.getString(((String) o));
                            }
                        }
                    }
                    catch (NoJSONObjectJsonException | NoStringValueJsonException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    System.out.println("NEW ATTRIBUTES: " + "[" + attributes[0] + " " +
                                       attributes[1] + " " + attributes[2]);
                    myObjectAttributes.add(attributes);
                }
            }
        }
    }

    /*
     * // For testing
     * private void createNewListItem(String s){
     * myListModel.addElement(s + exampleNumber);
     * exampleNumber++;
     * }
     * 
     * private void createNewListItem(Object con, String category) {
     * String tempString = (String) ((JSONObject) con).get(Constants.NAME);
     * tempString += " (" + category + ")";
     * myListModel.addElement(tempString);
     * }
     */

}
