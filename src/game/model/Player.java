package game.model;

import java.awt.Image;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONObject;

import constants.Constants;
import location.Direction;
import location.Loc;


public class Player extends AbstractCharacter {
    private List<Monster> myParty;
    private List<Item> myItems;
    private Collection<KeyItem> myKeyItems;

    public Player(World world, JSONObject definition, JSONObject objInWorld) {
        super(world, definition, objInWorld);
        myKeyItems = new HashSet<KeyItem>();
        myKeyItems.add(new KeyItem("razor"));
    }
    
    public void setKeyItems(Collection<KeyItem> keyItems){
        myKeyItems = keyItems;
    }
    
    public Collection<KeyItem> getKeyItems() {
        return myKeyItems;
    }

    public List<Monster> getParty () {
        return myParty;
    }
}
