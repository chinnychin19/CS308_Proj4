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

public class NPC extends AbstractViewableObject {

    private Image myImageUp, myImageDown, myImageRight, myImageLeft;
    private Direction myDirection;
    private String myDialogue;
        
    public NPC (World world, JSONObject definition, JSONObject objInWorld){
        super(world, definition, objInWorld);
        
        String imageUpURL = definition.get(Constants.JSON_IMAGE_UP).toString();
        String imageDownURL = definition.get(Constants.JSON_IMAGE_DOWN).toString();
        String imageLeftURL = definition.get(Constants.JSON_IMAGE_LEFT).toString();
        String imageRightURL = definition.get(Constants.JSON_IMAGE_RIGHT).toString();
        myImageUp = new ImageIcon(imageUpURL).getImage();
        myImageLeft = new ImageIcon(imageLeftURL).getImage();
        myImageDown = new ImageIcon(imageDownURL).getImage();
        myImageRight = new ImageIcon(imageRightURL).getImage();
        
        myDirection = stringToDirection(definition.get(Constants.JSON_ORIENTATION).toString());
        myDialogue = definition.get(Constants.JSON_DIALOGUE).toString();
    }
    
    private Direction stringToDirection (String s) {
        if (s.equalsIgnoreCase(Constants.UP)) {
            return Direction.UP;
        }
        else if (s.equalsIgnoreCase(Constants.LEFT)) {
            return Direction.LEFT;
        }
        else if (s.equalsIgnoreCase(Constants.DOWN)) {
            return Direction.DOWN;
        }
        else if (s.equalsIgnoreCase(Constants.RIGHT)) {
            return Direction.RIGHT;
        }
        else {
            return null;
        }
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
    
    public void setDirection(Direction d) {
        myDirection = d;
    }
    
    /**
     * Returns the dialogue of the NPC.  This is the speech that is displayed when the main player interacts with it
     * @return myDialogue
     */
    public String getDialogue(){
    	return myDialogue;
    }
    
    @Override
    public void doInteraction(Player p) {
        setDirection(Direction.opposite(p.getDirection()));
        System.out.println(myDialogue);
    }
}
