package author.menuItems;

import java.io.FileReader;
import java.util.Set;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import constants.Constants;
import author.listeners.LaunchWizardListener;
import author.model.AuthoringCache;

/**
 * EditEntitySubMenu extends AbstractMenu and allows the user to create a
 * new type of entity on the map, which can then be added or edited.
 * 
 * @author weskpga
 *
 */


public class NewEntitySubMenu extends JMenu {

	private static final long serialVersionUID = -5684982220499439703L;
	private AuthoringCache myAuthoringCache;

	@SuppressWarnings("unchecked")
	public NewEntitySubMenu (AuthoringCache cache) {
        super(Constants.NEW_ENTITY_SUBMENU);
        myAuthoringCache = cache;

        JSONObject template = getJSON(Constants.PLAYER_JSON);
        Set<String> keySet = template.keySet();
        System.out.println(Constants.MENU_POPULATED_MESSAGE + keySet);
        
        for (String s : keySet) {
            JMenuItem item = new JMenuItem(s);
            item.addActionListener(new LaunchWizardListener(s, myAuthoringCache));
            this.add(item);
        }
    }

    private JSONObject getJSON (String filepath) {
        JSONObject json;
        JSONParser parser = new JSONParser();
        try {
            json = (JSONObject) parser.parse(new FileReader(filepath));
            return json;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
