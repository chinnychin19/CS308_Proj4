package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import util.Direction;
import util.Loc;


public class Player extends AbstractViewableObject {
    private Direction myDirection;
    private List<Monster> myParty;
    private List<Item> myItems;
    private List<KeyItem> myKeyItems;
    private Image myImageUp, myImageDown, myImageRight, myImageLeft;
    private Loc myLoc;

    public Player(int x, int y) {
        //TODO
        myLoc = new Loc(x, y);
        myImageUp = new ImageIcon("images/mainPlayer/up.png").getImage();
        myImageLeft = new ImageIcon("images/mainPlayer/left.png").getImage();
        myImageDown = new ImageIcon("images/mainPlayer/down.png").getImage();
        myImageRight = new ImageIcon("images/mainPlayer/right.png").getImage();
    }
    
    public Loc getLoc() {
        return myLoc;
    }
    
    public List<Monster> getParty () {
        return myParty;
    }

//    public abstract void move ();
//
//    public abstract boolean canMove ();

    @Override
    Image getImage () {
        switch(myDirection) {
            case UP: return myImageUp;
            case DOWN: return myImageDown;
            case LEFT: return myImageLeft;
            case RIGHT: return myImageRight;
        }
        return null;
    }

}
// view only cares about objects currently on screen
// model returns currently viewablable objects
//
