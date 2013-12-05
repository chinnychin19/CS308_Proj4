package author;

import javax.swing.JFileChooser;


@SuppressWarnings("serial")
public class FileChooserSingleton extends JFileChooser {

    private static FileChooserSingleton instance;

    private FileChooserSingleton () {
        super("./");
    }

    public static synchronized FileChooserSingleton getInstance ()
    {
        if (instance == null)
            instance = new FileChooserSingleton();

        return instance;
    }
}
