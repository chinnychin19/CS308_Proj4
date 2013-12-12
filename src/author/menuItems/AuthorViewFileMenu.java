package author.menuItems;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import author.model.AuthoringCache;
import constants.Constants;

/**
 * AuthorViewFileMenu extends JMenu and contains all of the MenuItems
 * that can be clicked under the 'File' menu within our AuthorView.
 * 
 * @author weskpga
 *
 */

public class AuthorViewFileMenu extends JMenu {
	
	private static final long serialVersionUID = -3001299406612769184L;

	public AuthorViewFileMenu(AuthoringCache ac){
		super(Constants.FILE_MENU);
		
		addNewEntitySubMenu(ac);
		
		this.add(new CreateMapMenuItem());
		this.add(new LoadGameMenuItem(ac));
		this.add(new ChooseTemplateMenuItem());
		
	}

	private void addNewEntitySubMenu(AuthoringCache ac) {
		JMenuItem newEntitySubMenu = new NewEntitySubMenu(ac);
        this.add(newEntitySubMenu);
	}
}