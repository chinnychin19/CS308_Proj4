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

public class AuthorViewListMenu extends JMenu {

	private static final long serialVersionUID = 7761261408475568495L;

	public AuthorViewListMenu(AuthoringCache ac){
		super(Constants.LIST_MENU);
		this.addEditEntitySubMenu(ac);
	}
	
	private void addEditEntitySubMenu(AuthoringCache ac){
		ListEntitySubMenu item = new ListEntitySubMenu(ac);
		this.add(item);
	}
	
}
