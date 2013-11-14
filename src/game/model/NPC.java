package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import jsoncache.JSONCache;
import location.Direction;
import location.Loc;
import org.json.simple.JSONObject;
import constants.Constants;


public abstract class NPC extends AbstractViewableObject {

    private Loc myLoc;
    private Image myImageUp, myImageDown, myImageRight, myImageLeft;
    private Direction myDirection;
    private String myDialogue;
    private int myLineOfSightDistance;
    private JSONCache myJSONCache;    
    private JSONObject myFight;
    private List<Monster> myParty;
    private List<KeyItem> myKeyItems;
    
    public NPC (int x, int y, JSONObject definition){
        super(x, y, definition);   
        String imageUpURL = definition.get(Constants.IMAGE_UP).toString();
        String imageDownURL = definition.get(Constants.IMAGE_DOWN).toString();
        String imageLeftURL = definition.get(Constants.IMAGE_LEFT).toString();
        String imageRightURL = definition.get(Constants.IMAGE_RIGHT).toString();
        myImageUp = new ImageIcon(imageUpURL).getImage();
        myImageLeft = new ImageIcon(imageLeftURL).getImage();
        myImageDown = new ImageIcon(imageDownURL).getImage();
        myImageRight = new ImageIcon(imageRightURL).getImage();
        
        myLoc = new Loc(x, y);
        myDirection = (Direction) definition.get(Constants.ORIENTATION);
        myLineOfSightDistance = Integer.parseInt(definition.get(Constants.LINE_OF_SIGHT_DISTANCE).toString());
        myDialogue = definition.get(Constants.DIALOGUE).toString();
        
        myFight = (JSONObject) definition.get(Constants.FIGHT);
        if(myFight != null) {
            myJSONCache = new JSONCache(myFight);
            setUpFightable();
        }
    }
    
   
    
    public Loc getLoc(){
        return myLoc;
    }
    
    @Override
    public Image getImage () {
        switch (myDirection) {
            case UP:
                return myImageUp;
            case DOWN:
                return myImageDown;
            case LEFT:
                return myImageLeft;
            case RIGHT:
                return myImageRight;
        }
        return null;
    }
    
    public Direction getDirection(){
    	return myDirection;
    }
    
    public String getDialogue(){
    	return myDialogue;
    }
    
    public int getLineOfSightDistance(){
    	return myLineOfSightDistance;
    }
    
    public JSONObject getFight(){
    	return myFight;
    }
    
    public void setUpFightable(){
    	//TODO: Implement Method
    }
    
    public List<KeyItem> getKeyItems(){
        return myKeyItems;
    } 
    
    public List<Monster> getParty(){
    	return myParty;
    }
    
    
    
}
