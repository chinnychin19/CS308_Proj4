package author.menuItems;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;

import author.model.AuthoringCache;

import constants.Constants;

@SuppressWarnings("serial")
public class WriteJSONOutputMenuItem extends AbstractMenuItem {

	private AuthoringCache myAuthoringCache;
	
	public WriteJSONOutputMenuItem(AuthoringCache ac) {
		super(Constants.WRITE_JSON_TO_FILE);
		myAuthoringCache = ac;
	}

    @Override
    public void actionPerformed (ActionEvent e) {
        String sb = myAuthoringCache.getRawJSON().toString();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fw = new FileWriter(chooser.getSelectedFile() + ".json");
                fw.write(sb);
                fw.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

	
	
}
