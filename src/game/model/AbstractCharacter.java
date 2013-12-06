package game.model;

import java.awt.Image;
import javax.swing.ImageIcon;
import location.Direction;
import location.Loc;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

/**
 * An abstract class containing all the general information for a character including, the direction that it is facing and
 * the all the images that will be displayed based on this direction.
 * @author tylernisonoff, rtoussaint
 *
 */

public abstract class AbstractCharacter extends AbstractViewableObject {
    private Direction myDirection;
    private Image myImageUp, myImageDown, myImageRight, myImageLeft;

    public AbstractCharacter (GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld); 
    }

    /**
     * Set the x and y location of the character and add it to the world
     * @param loc location that the character will be set at
     * @param w world that the character will be added to
     */
    public void setLoc (Loc loc, World w) {
        getLoc().setX(loc.getX());
        getLoc().setY(loc.getY());
        
        //update hash in world's map
        destroy();
        w.addViewable(this);
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
     * Gets the direction that the character is facing
     * @return current direction of the character
     */
    public Direction getDirection () {
        return myDirection;
    }

    /**
     * Sets the direction that the character will face
     * @param d the direction that you want the character to face
     */
    public void setDirection (Direction d) {
        myDirection = d;
    }
    
    @Override
    protected void onInteract() {
        facePlayer();
    }
    
    protected void facePlayer() {
        setDirection(Direction.opposite(getWorld().getPlayer().getDirection()));
    }
    
    @Override
    protected void readDefinition (SmartJsonObject definition) throws SmartJsonException {
        super.readDefinition(definition);
        String imageUpURL = definition.getString(Constants.JSON_IMAGE_UP);
        String imageDownURL = definition.getString(Constants.JSON_IMAGE_DOWN);
        String imageLeftURL = definition.getString(Constants.JSON_IMAGE_LEFT);
        String imageRightURL = definition.getString(Constants.JSON_IMAGE_RIGHT);
        myImageUp = new ImageIcon(imageUpURL).getImage();
        myImageLeft = new ImageIcon(imageLeftURL).getImage();
        myImageDown = new ImageIcon(imageDownURL).getImage();
        myImageRight = new ImageIcon(imageRightURL).getImage();
    }
    
    @Override
    protected void readWorld(SmartJsonObject objInWorld) throws SmartJsonException {
        super.readWorld(objInWorld);
        myDirection = Direction.LEFT;//Direction.constructFromString(objInWorld.getString(Constants.JSON_ORIENTATION));
    }
}
