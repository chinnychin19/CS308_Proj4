package author;

import java.awt.Color;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import author.model.AuthoringCache;
import constants.Constants;

@SuppressWarnings("serial")
public class SidebarPanel extends JPanel {

	private AuthoringCache myAuthoringCache;
	private ButtonGroup myButtonGroup;
	
    public SidebarPanel(AuthoringCache ac) {
    	myAuthoringCache = ac;
    	myButtonGroup = new ButtonGroup();
        this.setPreferredSize(Constants.SIDEBAR_SIZE);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        initRadioButtons();
    }
    
	@SuppressWarnings("unchecked")
	public void initRadioButtons () {
        JSONObject template = myAuthoringCache.getRawJSON();
		Set<String> keySet = template.keySet();
        this.removeAll();
        System.out.println("Menu Populated with " + keySet);
        createRadioButtons(template, keySet);
        //this.add(myButtonGroup);
    }

	private void createRadioButtons(JSONObject template, Set<String> keySet) {
		for (Object s : keySet) {
            JSONArray locationArray = (JSONArray) template.get(s);
        	createNewRadioButton("Example");
            for (Object con : locationArray) {
            	if (con != null){
            		createNewRadioButton(con);
            	}
            }
        }
	}
	
	// For testing
	private void createNewRadioButton(String s){
		String temp = (String) s;
		JRadioButton button = new JRadioButton(temp);
		myButtonGroup.add(button);
		this.add(button);
	}

	private void createNewRadioButton(Object con) {
		String tempString = (String) ((JSONObject) con).get("name");
		JRadioButton button = new JRadioButton(tempString);
		this.add(button);
	}
    
}
