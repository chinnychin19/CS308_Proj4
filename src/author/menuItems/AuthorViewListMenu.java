package author.menuItems;

import javax.swing.JMenu;

import author.model.AuthoringCache;

import constants.Constants;

/**
 * AuthorViewEditMenu extends JMenu and contains all of the MenuItems
 * that can be clicked under the 'Edit' menu within our AuthorView.
 * 
 * @author weskpga
 *
 */

@SuppressWarnings("serial")
public class AuthorViewListMenu extends JMenu {

	public AuthorViewListMenu(AuthoringCache ac){
		super(Constants.LIST_MENU);
		this.addEditEntitySubMenu(ac);
	}
	
	private void addEditEntitySubMenu(AuthoringCache ac){
		ListEntitySubMenu item = new ListEntitySubMenu(ac);
		this.add(item);
	}
	
}
