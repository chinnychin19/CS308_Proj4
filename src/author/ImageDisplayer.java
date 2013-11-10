package author;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import util.MissingImageException;

public class ImageDisplayer extends JLabel {
    
    private Icon myIcon;
    private String myCaption;
    
    public ImageDisplayer(){
        init();
    }
    
    public ImageDisplayer(String filename){
        init();
        this.setImage(filename);
    }
    
    private void init(){
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setHorizontalTextPosition(CENTER);
        this.setVerticalTextPosition(BOTTOM);
    }
    
    public void setImage(String filename) {
        myCaption = filename;
        try{
            setIcon(filename);
        } catch (FileNotFoundException e) {
            myIcon = null;
            myCaption = "Error: File not found.";
        }
        this.setIcon(myIcon);
        this.setText(myCaption);
    }
    
    private void setIcon(String filename) throws FileNotFoundException{
        if (!new File(filename).isFile()){
            //System.out.println("is an error");
            throw new FileNotFoundException();
        }    
        myIcon = new ImageIcon(filename);
        //System.out.println("set icon");
        return;
    }
    
    public int getImageWidth(){
        return myIcon.getIconWidth();
    }
    
    public int getImageHeight(){
        return myIcon.getIconHeight();
    }
}
