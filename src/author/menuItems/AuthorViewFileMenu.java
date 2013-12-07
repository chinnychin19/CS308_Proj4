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
		JMenuItem newEntitySubMenu = new NewEntitySubMenu(Constants.NEW_ENTITY_SUBMENU, ac);
        this.add(newEntitySubMenu);
	}

    /*
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
	}*/
}