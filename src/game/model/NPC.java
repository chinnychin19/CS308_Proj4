package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import jsoncache.JSONCache;
import location.Direction;
import location.Loc;
import org.json.simple.JSONObject;

public abstract class NPC extends AbstractViewableObject {

    private Loc myLoc;
    private Image myImage;
    private Direction myDirection;
    private String myDialogue;
    private int myLineOfSightDistance;
   
    private JSONCache myJSONCache;    
    private JSONObject myFight;
    private List<Monster> myParty;
    private List<KeyItem> myKeyItems;
    
    public NPC (int x, int y, JSONObject definition){
        super(x, y, definition);
        String imageURL = definition.get("image").toString();
        myImage = new ImageIcon(imageURL).getImage();
        myLoc = new Loc(x, y);
        myDirection = (Direction) definition.get("orientation");
        myLineOfSightDistance = (Integer) definition.get("lineOfSightDistance");
        myDialogue = definition.get("dialogue").toString();
        
        myFight = (JSONObject) definition.get("fight");
        if(myFight != null) {
            myJSONCache = new JSONCache(myFight);
            setUpFightable();
        }
    }
    
    public void setUpFightable(){
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
