package author.menuItems;

import javax.swing.JMenu;
//import javax.swing.JMenuItem;

//import author.listeners.OutputJSONListener;
//import author.listeners.WriteJSONOutputListener;
import author.model.AuthoringCache;

import constants.Constants;

@SuppressWarnings("serial")
public class AuthorViewViewMenu extends JMenu {

	public AuthorViewViewMenu(AuthoringCache ac){
		super(Constants.VIEW_MENU);
		
		this.add(new ShowOutputMenuItem(ac));
		//addShowGeneratedOutput(ac);
		
		this.add(new WriteJSONOutputMenuItem(ac));
		//addWriteJSON(ac);
	}
	
	/*
	private void addShowGeneratedOutput(AuthoringCache ac){
		JMenuItem item = new JMenuItem(Constants.SHOW_GENERATED_OUTPUT);
		item.addActionListener(new OutputJSONListener(ac));
		this.add(item);
	}
	
	private void addWriteJSON(AuthoringCache ac){
		JMenuItem item = new JMenuItem(Constants.WRITE_JSON_TO_FILE);
		item.addActionListener(new WriteJSONOutputListener(ac));
		this.add(item);
	}
	*/
}
