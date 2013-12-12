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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import author.mapCreation.GenericTileWrapper;
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
    private DefaultListModel myListModel;
    private JList mySelectionList;
    private Set<String[]> myNamesAndCategories = new HashSet<String[]>();
    private String[] myCurrentSelection;
    private static int exampleNumber = 0;

    public SidebarPanel (AuthoringCache ac) {
        initialize(ac);
        initListModel();
        initSelectionList();
        finalizeSidebar();
    }

    @Override
    public void valueChanged (ListSelectionEvent arg0) {
        // TODO: Make this change what type of tile you are adding to the map

        if (arg0.getValueIsAdjusting()) { // to ensure this is only printed once
            String str = mySelectionList.getSelectedValue().toString();
            System.out.println(str + Constants.SELECTED_MESSAGE);
        }

    }

    public void updateList () {
        // myListModel.clear();
        myNamesAndCategories.clear();
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
        for (String[] sArr : myNamesAndCategories) {
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
                    String[] namecat = new String[3];

                    namecat[0] = (String) ((JSONObject) con).get(Constants.NAME);
                    namecat[1] = (String) s;
                    
                    System.out.println("namecat0: " + namecat[0]);
                    System.out.println("namecat1: " + namecat[1]);

                    JSONObject json = myAuthoringCache.getInstance(namecat[1], namecat[0]);
                    SmartJsonObject smartJSON;
                    try {
                        smartJSON = new SmartJsonObject(json);

                        Set<Object> JSONKeySet = smartJSON.keySet();

                        namecat[2] = "";

                        for (Object o : JSONKeySet) {
                            if (((String) o).contains("image")) {
                                namecat[2] = smartJSON.getString(((String) o));
                            }
                        }
                    }
                    catch (NoJSONObjectJsonException | NoStringValueJsonException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    myNamesAndCategories.add(namecat);
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
