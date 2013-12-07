package author.menuItems;

import javax.swing.JMenu;

import author.model.AuthoringCache;

import constants.Constants;

@SuppressWarnings("serial")
public class AuthorViewEditMenu extends JMenu {

	public AuthorViewEditMenu(AuthoringCache ac){
		super(Constants.EDIT_MENU);
		this.addEditEntitySubMenu(ac);
	}
	
	private void addEditEntitySubMenu(AuthoringCache ac){
		EditEntitySubMenu item = new EditEntitySubMenu(Constants.EDIT_ENTITY_SUBMENU, ac);
		this.add(item);
	}
	
}
