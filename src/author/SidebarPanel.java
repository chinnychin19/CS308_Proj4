package author;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
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
import util.FilepathReformatter;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;


/**
 * GUI component class that manages the sidebar panel of pre-existing objects.
 * 
 * @author weskpga
 * 
 */

@SuppressWarnings("rawtypes")
public class SidebarPanel extends JPanel implements ListSelectionListener {

	private static final String NEW_ATTRIBUTES = "NEW ATTRIBUTES: [";

	private static final String ATTRIBUTE1 = "attribute1: ";

	private static final String ATTRIBUTE2 = "attribute2: ";

	private static final long serialVersionUID = 93626670420635935L;

	private AuthoringCache myAuthoringCache;
	private MapCreationView myMapCreationView;
	private DefaultListModel myListModel;
	private JList mySelectionList;
	private Set<String[]> myObjectAttributes = new HashSet<String[]>();

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

			if (s != null && s.length() > 0) {
				gtw.setMyAdditionalInformation(s);
			}

			myMapCreationView.setCurrentTileImage(gtw);
			myMapCreationView.setCurrentTileName(gtw);
			myMapCreationView.setCurrentTileType(gtw);
		}
	}

	public void updateList () {

		myObjectAttributes.clear();

		JSONObject template = myAuthoringCache.toJSONObject();

		Set<String> keySet = new HashSet<String>();

		keySet.addAll(Arrays.asList(Constants.VIEWABLE_CATEGORIES));

		populateInternalList(template, keySet);

		updateListModel();
	}

	@SuppressWarnings("unchecked")
	private void updateListModel () {
		myListModel.clear();
		for (String[] sArr : myObjectAttributes) {
			myListModel.addElement(new GenericTileWrapper(sArr[0], sArr[1], sArr[2]));
		}

	}

	private void initialize (AuthoringCache ac) {
		myAuthoringCache = ac;
		this.setPreferredSize(Constants.SIDEBAR_SIZE);
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	@SuppressWarnings("unchecked")
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

	private void initListModel () {
		myListModel = new DefaultListModel();
		this.updateList();

	}

	private void populateInternalList (JSONObject template, Set<String> keySet) {
		for (Object s : keySet) {
			JSONArray locationArray = (JSONArray) template.get(s);

			for (Object con : locationArray) {
				if (con != null) {
					String[] attributes = new String[3];

					attributes[0] = (String) ((JSONObject) con).get(Constants.NAME);
					attributes[1] = (String) s;

					System.out.println(ATTRIBUTE2 + attributes[0]);
					System.out.println(ATTRIBUTE1 + attributes[1]);

					//JSONObject json = myAuthoringCache.getInstance(attributes[1], attributes[0]);
					//SmartJsonObject smartJSON;
					try {
						//smartJSON = new SmartJsonObject(json);
					        SmartJsonObject smartJSON = myAuthoringCache.getInstance(attributes[1], attributes[0]);

						Set<Object> JSONKeySet = smartJSON.keySet();

						attributes[2] = "";

						for (Object o : JSONKeySet) {
							if (((String) o).contains(Constants.IMAGE.toLowerCase())) {
								String unixStyleTruncatedFilepath = smartJSON.getString(((String) o));
								attributes[2] = convertToFullFunctionalFilepath(unixStyleTruncatedFilepath);
							}
						}
					}
					catch (SmartJsonException e1) {
						e1.printStackTrace();
					}
					System.out.println(NEW_ATTRIBUTES + attributes[0] + " " +
							attributes[1] + " " + attributes[2]);
					myObjectAttributes.add(attributes);
				}
			}
		}
	}

	private String convertToFullFunctionalFilepath (String unixStyleTruncatedFilepath) {

		FilepathReformatter fr = FilepathReformatter.getInstance();
		String correctedSeparators = fr.formatForCurrentSystem(unixStyleTruncatedFilepath);
		return System.getProperty(Constants.USER_DIR) + File.separator + correctedSeparators;

	}


}
