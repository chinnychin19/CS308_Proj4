package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import jsoncache.JSONCache;
import location.Direction;
import location.Loc;
import org.json.simple.JSONObject;
import constants.Constants;

/**
 * NPC Class that creates a new of a non-player character and initializes all of its variables based on reading in the correct
 * JSON information.
 * @author rtoussaint
 *
 */

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
    
    public NPC (JSONObject definition, JSONObject objInWorld){
        super(definition, objInWorld);   
        String imageUpURL = definition.get(Constants.JSON_IMAGE_UP).toString();
        String imageDownURL = definition.get(Constants.JSON_IMAGE_DOWN).toString();
        String imageLeftURL = definition.get(Constants.JSON_IMAGE_LEFT).toString();
        String imageRightURL = definition.get(Constants.JSON_IMAGE_RIGHT).toString();
        myImageUp = new ImageIcon(imageUpURL).getImage();
        myImageLeft = new ImageIcon(imageLeftURL).getImage();
        myImageDown = new ImageIcon(imageDownURL).getImage();
        myImageRight = new ImageIcon(imageRightURL).getImage();
        
        myDirection = (Direction) definition.get(Constants.JSON_ORIENTATION);
        myLineOfSightDistance = Integer.parseInt(definition.get(Constants.JSON_LINE_OF_SIGHT_DISTANCE).toString());
        myDialogue = definition.get(Constants.JSON_DIALOGUE).toString();
        
        myFight = (JSONObject) definition.get(Constants.JSON_FIGHT);
        if(myFight != null) {
            myJSONCache = new JSONCache(myFight);
            setUpFightable();
        }
    }
    
    /**
     * Returns the location of the NPC
     * @return myLoc
     */
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
    
    /**
     * Returns the direction that the NPC is facing
     * @return myDirection
     */
    public Direction getDirection(){
    	return myDirection;
    }
    
    /**
     * Returns the dialogue of the NPC.  This is the speech that is displayed when the main player interacts with it
     * @return myDialogue
     */
    public String getDialogue(){
    	return myDialogue;
    }
    
    /**
     * Returns the NPC's line of sight distance.  Essentially, how far ahead the NPC can see -- used for detecting when the main
     * player is within range to interact with
     * @return myLineOfSightDistance
     */
    public int getLineOfSightDistance(){
    	return myLineOfSightDistance;
    }
    
    /**
     * Returns the NPC's fight.  This may be null if the NPC does not battle the main player.  If it does battle the main player,
     * then this handles all the information for the NPC to go into battle mode.
     * @return myFight
     */
    public JSONObject getFight(){
    	return myFight;
    }
    
    public void setUpFightable(){
    	//TODO: Implement Method
    }
    
    /**
     * Returns the NPC's key items.  These are items that can be given to the main player as it progresses through the game
     * @return myKeyItems
     */
    public List<KeyItem> getKeyItems(){
        return myKeyItems;
    } 
    
    /**
     * Returns the NPC's party, containing the monsters that it has
     * @return myParty
     */
    public List<Monster> getParty(){
    	return myParty;
    } 
}
