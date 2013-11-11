package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import location.Direction;
import location.Loc;


public class Player extends AbstractViewableObject {
    private Direction myDirection;
    private List<Monster> myParty;
    private List<Item> myItems;
    private List<KeyItem> myKeyItems;
    private Image myImageUp, myImageDown, myImageRight, myImageLeft;
    private Loc myLoc;

    public Player (int x, int y) {
        // TODO
        myLoc = new Loc(x, y);
        myDirection = Direction.DOWN;
        myImageUp = new ImageIcon("images/players/mainPlayer1/up.png").getImage();
        myImageLeft = new ImageIcon("images/players/mainPlayer1/left.png").getImage();
        myImageDown = new ImageIcon("images/players/mainPlayer1/down.png").getImage();
        myImageRight = new ImageIcon("images/players/mainPlayer1/right.png").getImage();
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