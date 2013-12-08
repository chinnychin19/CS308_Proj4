package author.menuItems;

import java.util.Set;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import constants.Constants;
import author.listeners.LaunchWizardListener;
import author.model.AuthoringCache;

@SuppressWarnings("serial")
public class EditEntitySubMenu extends AbstractMenu {

    private AuthoringCache myCache;
    private JMenu me = this;

    public EditEntitySubMenu (AuthoringCache cache) {
        super(Constants.EDIT_ENTITY_SUBMENU);

        myCache = cache;
        refreshMenu();

    }

    @SuppressWarnings("unchecked")
	public void refreshMenu () {
        JSONObject template = myCache.getRawJSON();
		Set<String> keySet = template.keySet();
        me.removeAll();
        System.out.println("Menu Populated with " + keySet);
        for (Object s : keySet) {
            JMenu menu = new JMenu((String) s);
            this.add(menu);
            JSONArray locationArray = (JSONArray) template.get(s);
            for (Object con : locationArray) {
                String tempString = (String) ((JSONObject) con).get("name");
                JMenuItem item = new JMenuItem(tempString);
                item.addActionListener(new LaunchWizardListener((String) s,myCache));
                menu.add(item);
            }

        }
    }

}
