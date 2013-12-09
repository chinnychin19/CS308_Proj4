package author.menuItems;

import java.awt.event.ActionEvent;

import constants.Constants;

/**
 * CreateMapMenuItem extends AbstractMenuItem and is added to the File
 * menu in our AuthorView.  When clicked, it allows the user to load
 * a previously created map, which can then be edited and re-saved.
 * 
 * @author weskpga
 *
 */

@SuppressWarnings("serial")
public class LoadGameMenuItem extends AbstractMenuItem {

	public LoadGameMenuItem() {
		super(Constants.LOAD_EXISTING_GAME);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		handleLoadExistingGame();
	}

	private void handleLoadExistingGame(){
		System.out.println("Clicked 'Load Existing Game'");
	}
	
	
}
