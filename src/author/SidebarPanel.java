package author;

import java.awt.Color;
import java.awt.Dimension;
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
import author.mapCreation.MapCreationView;
import author.model.AuthoringCache;
import author.model.GenericTileWrapperFactory;
import author.model.TileWrapper;
import constants.Constants;


/**
 * GUI component class that manages the sidebar panel of pre-existing objects.
 * 
 * @author weskpga
 * 
 */

@SuppressWarnings("rawtypes")
public class SidebarPanel extends JPanel implements ListSelectionListener {

    private static final long serialVersionUID = 3720178376864090021L;
    private AuthoringCache myAuthoringCache;
	private MapCreationView myMapCreationView;
	private DefaultListModel<TileWrapper> myListModel;
	private JList mySelectionList;

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

			TileWrapper tile = (TileWrapper) mySelectionList.getSelectedValue();

			if (s != null && s.length() > 0) {
				tile.setMyAdditionalInformation(s);
			}

			myMapCreationView.setCurrentTile(tile);
		}
	}

	public void updateList () {
	    myListModel.clear();
	    GenericTileWrapperFactory factory = new GenericTileWrapperFactory();
	    for (TileWrapper tile : factory.generateTiles(myAuthoringCache)) {
	        myListModel.addElement(tile);
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
		myListModel = new DefaultListModel<TileWrapper>();
		this.updateList();

	}

}
