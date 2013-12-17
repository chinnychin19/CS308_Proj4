package author.menuItems;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import author.model.AuthoringCache;
import constants.Constants;


/**
 * WriteJSONOutputMenuItem extends AbstractMenuItem and is added to the View
 * menu in our AuthorView. When clicked, it writes the current created map
 * to a JSON file and allows the user to load that file later using the game
 * engine to actually play that game.
 * 
 * @author weskpga
 * 
 */


public class WriteJSONOutputMenuItem extends AbstractMenuItem {

	private static final long serialVersionUID = 2257496413171118641L;
	private AuthoringCache myAuthoringCache;

    public WriteJSONOutputMenuItem (AuthoringCache ac) {
        super(Constants.WRITE_JSON_TO_FILE);
        myAuthoringCache = ac;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        String sb = myAuthoringCache.toJSONObject().toString();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fw =
                        new FileWriter(chooser.getSelectedFile() + Constants.JSON_EXTENSION);
                fw.write(sb);
                fw.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
