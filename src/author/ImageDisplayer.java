package author;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class ImageDisplayer extends JLabel {

    private Icon myIcon;
    // private String myImageFilepath;
    private String myCaption;
    public static final int MIN_X_SIZE = 100;
    public static final int MIN_Y_SIZE = 100;
    public static final int MAX_X_SIZE = 800;
    public static final int MAX_Y_SIZE = 500;

    public ImageDisplayer () {
        init();
    }

    public ImageDisplayer (String filename) {
        init();
        this.setImageAndCaption(filename);
    }

    public ImageDisplayer (File file) {
        init();
        this.setImageAndCaption(file);
    }

    private void init () {
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setHorizontalTextPosition(CENTER);
        this.setVerticalTextPosition(BOTTOM);
        this.setMinimumSize(new Dimension(MIN_X_SIZE, MIN_Y_SIZE));
        this.setMaximumSize(new Dimension(MAX_X_SIZE, MAX_Y_SIZE));
    }

    public void setImageAndCaption (File file) {
        String filepath = file.getPath();
        setImageAndCaption(filepath);
    }

    public void setImageAndCaption (String filepath) {
        myCaption = filepath;
        try {
            setIcon(filepath);
        }
        catch (FileNotFoundException e) {
            myIcon = null;
            // myImageFilepath = "";
            myCaption = "Error: File not found.";
        }
        this.setIcon(myIcon);
        this.setText(myCaption);
    }

    private void setIcon (String filepath) throws FileNotFoundException {
        if (!new File(filepath).isFile()) {
            // System.out.println("is an error");
            throw new FileNotFoundException();
        }
        myIcon = new ImageIcon(filepath);
        // myImageFilepath = filename;
        // System.out.println("set icon");
        return;
    }

    public int getImageWidth () {
        return myIcon.getIconWidth();
    }

    public int getImageHeight () {
        return myIcon.getIconHeight();
    }

    // public String getImageFilepath(){
    // return myImageFilepath;
    // }
}
