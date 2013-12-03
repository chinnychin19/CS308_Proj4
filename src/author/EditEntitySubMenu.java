package author;

//import java.awt.Component;
//import java.io.FileReader;
import java.util.Set;
//import javax.sound.midi.MetaEventListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import author.listeners.LaunchPlayerWizardListener;
//import author.listeners.LaunchWizardListener;
import author.model.AuthoringCache;
//import constants.Constants;


@SuppressWarnings("serial")
public class EditEntitySubMenu extends JMenu {

    private AuthoringCache myCache;
    private JMenu me = this;

    public EditEntitySubMenu (String title, AuthoringCache cache) {
        super(title);

        myCache = cache;
        refreshMenu();

    }

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
                // item.addActionListener(new LaunchWizardListener(s,myCache));
                menu.add(item);
            }

        }
    }

}
