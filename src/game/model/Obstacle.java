package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import location.Loc;
import org.json.simple.JSONObject;

public abstract class Obstacle extends AbstractViewableObject {
    
    private Loc myLoc;
    private Image myImage;
    private List<KeyItem> myKeyItems;
    
    public Obstacle (int x, int y, JSONObject myJSON){
        myLoc = new Loc(x, y);
        String imageURL = myJSON.get("image").toString();
        myImage = new ImageIcon(imageURL).getImage();
        myKeyItems = (List) myJSON.get("key items");
    }
    
    public Loc getLoc(){
        return myLoc;
    }
    
    public Image getImage(){
        return myImage;
    }
    
    public List<KeyItem> getKeyItems(){
        return myKeyItems;
    }
}
