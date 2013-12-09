package author.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * Abstract class that, when extended, automatically adds an ActionListener
 * to a menu item that can then be placed within a JMenu.
 * 
 * @author weskpga
 *
 */

@SuppressWarnings("serial")
public abstract class AbstractMenuItem extends JMenuItem implements ActionListener {
	
	// myText is the text that will be shown on the MenuItem
	public AbstractMenuItem(String myText){
		super(myText);
		addActionListener(this);
	}

	@Override
	public abstract void actionPerformed(ActionEvent arg0);
	
}
