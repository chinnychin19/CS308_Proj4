package author.menuItems;

import java.util.Set;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import constants.Constants;
import author.model.AuthoringCache;

/**
 * EditEntitySubMenu extends AbstractMenu and contains all of the entities
 * that have already have templates created for them.
 * 
 * @author weskpga
 *
 */

public class ListEntitySubMenu extends JMenu {

	private static final long serialVersionUID = -1394146545730632997L;
	private AuthoringCache myCache;
    private JMenu me = this;

    public ListEntitySubMenu (AuthoringCache cache) {
        super(Constants.LIST_ENTITY_SUBMENU);

        myCache = cache;
        refreshMenu();

    }

    @SuppressWarnings("unchecked")
	public void refreshMenu () {
        JSONObject template = myCache.toJSONObject();
		Set<String> keySet = template.keySet();
        me.removeAll();
        System.out.println(Constants.MENU_POPULATED_MESSAGE + keySet);
        for (Object s : keySet) {
            JMenu menu = new JMenu((String) s);
            this.add(menu);
            JSONArray locationArray = (JSONArray) template.get(s);
            for (Object con : locationArray) {
                String tempString = (String) ((JSONObject) con).get(Constants.NAME);
                JMenuItem item = new JMenuItem(tempString);
                menu.add(item);
            }

        }
    }

}
