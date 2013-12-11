package author;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.HashSet;
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

import author.model.AuthoringCache;
import constants.Constants;


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
    private static int exampleNumber = 0;

    public SidebarPanel(AuthoringCache ac) {
        initialize(ac);
        initListModel();
        initSelectionList();
        finalizeSidebar();
    }

    @Override
    public void valueChanged(ListSelectionEvent arg0) {
        // TODO: Make this change what type of tile you are adding to the map

        if (arg0.getValueIsAdjusting()){ // to ensure this is only printed once
            String str = mySelectionList.getSelectedValue().toString();
            System.out.println(str + Constants.SELECTED_MESSAGE);
        }
    }

    public void updateList(){
        myListModel.clear();
        //myAuthoringCache.mjrTest();   -DO NOT USE AGAIN-       
        JSONObject template = myAuthoringCache.getRawJSON();
        //Set<String> keySet = template.keySet();   -OLD METHOD-         
        Set<String> keySet = new HashSet<String>(); //TESTED 
        keySet.addAll(Arrays.asList(Constants.VIEWABLE_CATEGORIES)); //TESTED
        
        createSelectionList(template, keySet);
    }

    private void initialize(AuthoringCache ac) {
        myAuthoringCache = ac;
        this.setPreferredSize(Constants.SIDEBAR_SIZE);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void initSelectionList(){
        mySelectionList = new JList(myListModel);
        mySelectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mySelectionList.setSelectedIndex(0);
        mySelectionList.addListSelectionListener(this);
        mySelectionList.setVisibleRowCount(20);
    }

    private void finalizeSidebar() {
        JScrollPane listScrollPane = new JScrollPane(mySelectionList);
        listScrollPane.setPreferredSize(Constants.SIDEBAR_JLIST_SIZE);
        JTextArea listText = createSidebarPrompt();
        this.add(listText);
        this.add(listScrollPane);
    }

    private JTextArea createSidebarPrompt() {
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

    private void createSelectionList(JSONObject template, Set<String> keySet) {
        for (Object s : keySet) {
            JSONArray locationArray = (JSONArray) template.get(s);
            //createNewListItem("Example"); // TODO: Get rid of this when done testing
            
        /*  for (String str : myAuthoringCache.getAllInstanceNamesInCategory("Things")){
                createNewListItem(str);
            }   TEST COMPLETE*/
            
            for (Object con : locationArray) {
                if (con != null){
                    createNewListItem(con);
                }
            }
        }
    }

    // For testing
    private void createNewListItem(String s){
        myListModel.addElement(s + exampleNumber);
        exampleNumber++;
    }

    private void createNewListItem(Object con) {
        String tempString = (String) ((JSONObject) con).get(Constants.NAME);
        myListModel.addElement(tempString);
    }
}
