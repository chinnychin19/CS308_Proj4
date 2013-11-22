package game.model;

import java.awt.Image;
import javax.swing.ImageIcon;
import location.Direction;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

public class AbstractCharacter extends AbstractInteractableObject {
    private Direction myDirection;
    private Image myImageUp, myImageDown, myImageRight, myImageLeft;

    public AbstractCharacter (World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(world, definition, objInWorld);
        try{
            myDirection = Direction.constructFromString(objInWorld.getString(Constants.JSON_ORIENTATION));
            String imageUpURL = definition.getString(Constants.JSON_IMAGE_UP);
            String imageDownURL = definition.getString(Constants.JSON_IMAGE_DOWN);
            String imageLeftURL = definition.getString(Constants.JSON_IMAGE_LEFT);
            String imageRightURL = definition.getString(Constants.JSON_IMAGE_RIGHT);
            myImageUp = new ImageIcon(imageUpURL).getImage();
            myImageLeft = new ImageIcon(imageLeftURL).getImage();
            myImageDown = new ImageIcon(imageDownURL).getImage();
            myImageRight = new ImageIcon(imageRightURL).getImage();
        }catch(SmartJsonException e){
            e.printStackTrace();
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
    
    public Direction getDirection () {
        return myDirection;
    }

    public void setDirection (Direction d) {
        myDirection = d;
    }
}
