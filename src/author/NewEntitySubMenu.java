package author;

import java.io.FileReader;
import java.util.Set;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import constants.Constants;
import author.listeners.LaunchWizardListener;
import author.model.AuthoringCache;


@SuppressWarnings("serial")
public class NewEntitySubMenu extends JMenu {

    private AuthoringCache myCache;

    @SuppressWarnings("unchecked")
	public NewEntitySubMenu (String title, AuthoringCache cache) {
        super(title);
        myCache = cache;

        JSONObject template = getJSON(Constants.PLAYER_JSON);
        Set<String> keySet = template.keySet();
        System.out.println("Menu Populated with " + keySet);
        for (String s : keySet) {
            JMenuItem item = new JMenuItem(s);
            item.addActionListener(new LaunchWizardListener(s, myCache));
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
