package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONObject;

import constants.Constants;
import location.Direction;
import location.Loc;


public class Player extends AbstractViewableObject {
    private Direction myDirection;
    private List<Monster> myParty;
    private List<Item> myItems;
    private List<KeyItem> myKeyItems;
    private Image myImageUp, myImageDown, myImageRight, myImageLeft;

    public Player(World world, JSONObject definition, JSONObject objInWorld) {
        super(world, definition, objInWorld);
        myDirection = Direction.DOWN;
        String imageUpURL = definition.get(Constants.JSON_IMAGE_UP).toString();
        String imageDownURL = definition.get(Constants.JSON_IMAGE_DOWN).toString();
        String imageLeftURL = definition.get(Constants.JSON_IMAGE_LEFT).toString();
        String imageRightURL = definition.get(Constants.JSON_IMAGE_RIGHT).toString();
        myImageUp = new ImageIcon(imageUpURL).getImage();
        myImageLeft = new ImageIcon(imageLeftURL).getImage();
        myImageDown = new ImageIcon(imageDownURL).getImage();
        myImageRight = new ImageIcon(imageRightURL).getImage();
    }
    
    public List<KeyItem> getKeyItems() {
        return myKeyItems;
    }

    public Direction getDirection () {
        return myDirection;
    }

    public void setDirection (Direction d) {
        myDirection = d;
    }

    public List<Monster> getParty () {
        return myParty;
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

}
