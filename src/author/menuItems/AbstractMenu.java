package author.menuItems;

import javax.swing.JMenu;

/**
 * This class is currently no different from the native JMenu class, but it
 * was created just in case we do need to add features to our menus within
 * the AuthorView.
 * 
 * @author weskpga
 *
 */

@SuppressWarnings("serial")
public abstract class AbstractMenu extends JMenu /*implements ActionListener*/ {
	
	public AbstractMenu(String myText){
		super(myText);
	}
	
	//@Override
	//public abstract void actionPerformed(ActionEvent arg0);

}
