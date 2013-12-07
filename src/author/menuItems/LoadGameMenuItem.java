package author.menuItems;

import java.awt.event.ActionEvent;

import constants.Constants;

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
