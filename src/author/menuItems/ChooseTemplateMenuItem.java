package author.menuItems;

import java.awt.event.ActionEvent;

import constants.Constants;

@SuppressWarnings("serial")
public class ChooseTemplateMenuItem extends AbstractMenuItem {

	public ChooseTemplateMenuItem() {
		super(Constants.CHOOSE_ALTERNATE_TEMPLATE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		handleChooseAltTemplate();
	}

	private void handleChooseAltTemplate(){
		System.out.println("Clicked 'Choose Alternate Template'");
	}
	
}
