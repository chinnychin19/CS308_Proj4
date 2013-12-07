package author.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import author.model.AuthoringCache;
import constants.Constants;

@SuppressWarnings("serial")
public class AuthorViewFileMenu extends JMenu {
	
	public AuthorViewFileMenu(AuthoringCache ac){
		super(Constants.FILE_MENU);
		
		addNewEntitySubMenu(ac);
		addCreateNewMap();
		addLoadExistingGame();
		addChooseAltTemplate();
	}
	
	// Called when 'Load Existing Game' is clicked
	private void handleLoadExistingGame() {
		System.out.println("Clicked 'Load Existing Game'");
	}
	
	// Called when 'Create New Map' is clicked
	private void handleCreateNewMap() {
		System.out.println("Clicked 'Create New Map'");
	}
	
	// Called when 'Choose Alternate Template' is clicked
	private void handleChooseAltTemplate() {
		System.out.println("Clicked 'Choose Alternate Template'");
	}

	private void addNewEntitySubMenu(AuthoringCache ac) {
		JMenuItem newEntitySubMenu = new NewEntitySubMenu(Constants.NEW_ENTITY_SUBMENU, ac);
        this.add(newEntitySubMenu);
	}

    
	private void addCreateNewMap() {
		JMenuItem createNewMapItem = new JMenuItem(Constants.CREATE_NEW_MAP);
        addCreateNewMapItemListener(createNewMapItem);
        this.add(createNewMapItem);
	}

	private void addLoadExistingGame() {
		JMenuItem loadExistingGameItem = new JMenuItem(Constants.LOAD_EXISTING_GAME);
        addLoadExistingGameListener(loadExistingGameItem);
        this.add(loadExistingGameItem);
	}

	private void addChooseAltTemplate() {
		JMenuItem chooseAltTemplateItem = new JMenuItem(Constants.CHOOSE_ALTERNATE_TEMPLATE);
        addChooseAltTemplateListener(chooseAltTemplateItem);
        this.add(chooseAltTemplateItem);
	}
	
	private void addCreateNewMapItemListener(JMenuItem createNewMapItem) {
		createNewMapItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				handleCreateNewMap();
			}
        });
	}
	
	private void addLoadExistingGameListener(JMenuItem loadExistingGameItem) {
		loadExistingGameItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				handleLoadExistingGame();
			}
        });
	}

	private void addChooseAltTemplateListener(JMenuItem chooseAltTemplateItem) {
		chooseAltTemplateItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				handleChooseAltTemplate();
			}
        });
	}
}