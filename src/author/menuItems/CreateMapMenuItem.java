package author.menuItems;

import java.awt.event.ActionEvent;
import constants.Constants;

/**
 * CreateMapMenuItem extends AbstractMenuItem and is added to the File
 * menu in our AuthorView.  When clicked, it allows the user to create
 * a new map that can be played using the game engine.
 * 
 * @author weskpga
 *
 */

@SuppressWarnings("serial")
public class CreateMapMenuItem extends AbstractMenuItem {

	public CreateMapMenuItem() {
		super(Constants.CREATE_NEW_MAP);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		handleCreateNewMap();
	}

	// Called when 'Create New Map' is clicked
	private void handleCreateNewMap(){
		System.out.println("Clicked 'Create New Map'");
	}
	
	
}
