package game.model;

import java.awt.Image;
import javax.swing.ImageIcon;
import location.Direction;
import org.json.simple.JSONObject;
import constants.Constants;

public class AbstractCharacter extends AbstractViewableObject {
    private Direction myDirection;
    private Image myImageUp, myImageDown, myImageRight, myImageLeft;

    public AbstractCharacter (World world, JSONObject definition, JSONObject objInWorld) {
        super(world, definition, objInWorld);
        myDirection = Direction.constructFromString(objInWorld.get(Constants.JSON_ORIENTATION).toString());
        String imageUpURL = definition.get(Constants.JSON_IMAGE_UP).toString();
        String imageDownURL = definition.get(Constants.JSON_IMAGE_DOWN).toString();
        String imageLeftURL = definition.get(Constants.JSON_IMAGE_LEFT).toString();
        String imageRightURL = definition.get(Constants.JSON_IMAGE_RIGHT).toString();
        myImageUp = new ImageIcon(imageUpURL).getImage();
        myImageLeft = new ImageIcon(imageLeftURL).getImage();
        myImageDown = new ImageIcon(imageDownURL).getImage();
        myImageRight = new ImageIcon(imageRightURL).getImage();
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
