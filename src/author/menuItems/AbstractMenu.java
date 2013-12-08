package author.menuItems;

import javax.swing.JMenu;

@SuppressWarnings("serial")
public abstract class AbstractMenu extends JMenu /*implements ActionListener*/ {

	// This class currently serves no purpose, but may prove useful
	// when refactoring the other JMenus.
	
	public AbstractMenu(String myText){
		super(myText);
	}
	
	//@Override
	//public abstract void actionPerformed(ActionEvent arg0);

}
