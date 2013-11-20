package author;

import java.io.FileReader;
import java.util.Set;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import author.listeners.LaunchPlayerWizardListener;
import author.listeners.LaunchWizardListener;
import constants.Constants;


public class WizardSubMenu extends JMenu {
    
    public WizardSubMenu (String title) {
        
        super(title);
        
        JSONObject template = getJSON("player.json");
        Set<String> keySet = template.keySet();
        System.out.println(keySet);
        for (String s : keySet){
        	JMenuItem item = new JMenuItem(s);
        	item.addActionListener(new LaunchWizardListener(s));
        	this.add(item);
        }
    }
    
    private JSONObject getJSON(String filepath) {
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
