package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONObject;
import location.Direction;
import location.Loc;


public class Player extends AbstractViewableObject {
    private Direction myDirection;
    private List<Monster> myParty;
    private List<Item> myItems;
    private List<KeyItem> myKeyItems;
    private Image myImageUp, myImageDown, myImageRight, myImageLeft;
    private Loc myLoc;

    public Player(int x, int y, JSONObject definition) {
        super(x,y,definition);
        myLoc = new Loc(x, y);
        myDirection = Direction.DOWN;
        String imageUpURL = definition.get("image-up").toString();
        String imageDownURL = definition.get("image-down").toString();
        String imageLeftURL = definition.get("image-left").toString();
        String imageRightURL = definition.get("image-right").toString();
        myImageUp = new ImageIcon(imageUpURL).getImage();
        myImageLeft = new ImageIcon(imageLeftURL).getImage();
        myImageDown = new ImageIcon(imageDownURL).getImage();
        myImageRight = new ImageIcon(imageRightURL).getImage();
    }

    public Direction getDirection () {
        return myDirection;
    }

    public void setDirection (Direction d) {
        myDirection = d;
    }

    public Loc getLoc () {
        return myLoc;
    }

    public void setLoc (Loc loc) {
        myLoc = loc;
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
