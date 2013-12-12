package author.menuItems;

import java.awt.event.ActionEvent;

import constants.Constants;

/**
 * ChooseTemplateMenuItem extends AbstractMenuItem and is added to the File
 * menu in our AuthorView.  When clicked, it allows the user to pick an
 * alternate template from a JSON file that they can dynamically create things
 * from.
 * 
 * @author weskpga
 *
 */

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
		
		
		System.out.println(Constants.CLICKED_ALT_TEMPLATE);
	}
	
}
