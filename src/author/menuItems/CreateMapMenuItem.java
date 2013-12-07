package author.menuItems;

import java.awt.event.ActionEvent;

import constants.Constants;

@SuppressWarnings("serial")
public class CreateMapMenuItem extends AbstractMenuItem {

	public CreateMapMenuItem() {
		super(Constants.CREATE_NEW_MAP);
		//addActionListener(this);
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
