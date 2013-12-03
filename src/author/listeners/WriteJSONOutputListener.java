package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import javax.swing.JFileChooser;
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
