package author.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public abstract class AbstractMenuItem extends JMenuItem implements ActionListener {
	
	public AbstractMenuItem(String myText){
		super(myText);
		addActionListener(this);
	}

	@Override
	public abstract void actionPerformed(ActionEvent arg0);
	
}
