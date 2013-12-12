package author.menuItems;

import javax.swing.JMenu;
//import javax.swing.JMenuItem;

//import author.listeners.OutputJSONListener;
//import author.listeners.WriteJSONOutputListener;
import author.model.AuthoringCache;

import constants.Constants;

/**
 * AuthorViewViewMenu extends JMenu and contains all of the MenuItems
 * that can be clicked under the 'View' menu within our AuthorView.
 * 
 * @author weskpga
 *
 */

public class AuthorViewViewMenu extends JMenu {
	
	private static final long serialVersionUID = 1211467952685798069L;

	public AuthorViewViewMenu(AuthoringCache ac){
		super(Constants.VIEW_MENU);
		
		this.add(new ShowOutputMenuItem(ac));
		this.add(new WriteJSONOutputMenuItem(ac));
	}
}
