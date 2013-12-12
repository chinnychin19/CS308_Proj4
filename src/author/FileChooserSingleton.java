package author;

import javax.swing.JFileChooser;


@SuppressWarnings("serial")
public class FileChooserSingleton extends JFileChooser {

    private static FileChooserSingleton instance;

    private FileChooserSingleton () {
        super(System.getProperty("user.dir"));
    }

    public static synchronized FileChooserSingleton getInstance ()
    {
        if (instance == null)
            instance = new FileChooserSingleton();

        resetSelectionSettings();

        return instance;
    }

    private static void resetSelectionSettings () {
        instance.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        instance.setMultiSelectionEnabled(false);
        instance.resetChoosableFileFilters();
    }
}
