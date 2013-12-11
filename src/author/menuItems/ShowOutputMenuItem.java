package author.menuItems;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

import author.model.AuthoringCache;

import constants.Constants;

/**
 * ShowOutputMenuItem extends AbstractMenuItem and is added to the View
 * menu in our AuthorView.  When clicked, it allows the user to see the
 * JSON output that their activity has created.
 * 
 * @author weskpga
 *
 */

@SuppressWarnings("serial")
public class ShowOutputMenuItem extends AbstractMenuItem {

	private AuthoringCache myAuthoringCache;
	
	public ShowOutputMenuItem(AuthoringCache ac) {
		super(Constants.SHOW_GENERATED_OUTPUT);
		myAuthoringCache = ac;
	}

    @Override
    public void actionPerformed (ActionEvent arg0) {
        JFrame frame = new JFrame();
        JTextField text = new JTextField(myAuthoringCache.getRawJSON().toString());
        text.setEditable(false);
        frame.add(text);
        frame.pack();
        frame.setVisible(true);
    }

	
	
}
