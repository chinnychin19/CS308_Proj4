package author.menuItems;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import author.model.AuthoringCache;
import constants.Constants;

@SuppressWarnings("serial")
public class AuthorViewFileMenu extends JMenu {
	
	public AuthorViewFileMenu(AuthoringCache ac){
		super(Constants.FILE_MENU);
		
		addNewEntitySubMenu(ac);
		
		this.add(new CreateMapMenuItem());
		this.add(new LoadGameMenuItem());
		this.add(new ChooseTemplateMenuItem());
		
	}

	private void addNewEntitySubMenu(AuthoringCache ac) {
		JMenuItem newEntitySubMenu = new NewEntitySubMenu(ac);
        this.add(newEntitySubMenu);
	}
}