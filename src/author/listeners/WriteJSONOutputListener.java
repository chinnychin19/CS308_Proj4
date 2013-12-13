package author.listeners;

/**
 * Class that finalizes a map creation and writes the relevant
 * information to a JSON file that can later be loaded into a
 * game, or loaded into the author again to be edited.
 * 
 * @author weskpga, ransel
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;

import constants.Constants;
import author.model.AuthoringCache;


public class WriteJSONOutputListener implements ActionListener {

    private AuthoringCache myAuthoringCache;

    public WriteJSONOutputListener (AuthoringCache ac) {
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
                FileWriter fw = new FileWriter(chooser.getSelectedFile() + Constants.JSON_EXTENSION);
                fw.write(sb);
                fw.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
