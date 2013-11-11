package game.model;

import java.util.HashMap;
import java.util.Map;
import util.Direction;
import util.Loc;


public class World {
    private HashMap<Loc, AbstractViewableObject> myViewableObjects;

    private Player myPlayer;

    public World (Player p) {
        myPlayer = p;
        myViewableObjects = new HashMap<Loc, AbstractViewableObject>();
    }

    public void addViewableObject (Loc loc, AbstractViewableObject obj) {
        myViewableObjects.put(loc, obj);
    }

    public Map<Loc, AbstractViewableObject> getViewableObjects () {
        return myViewableObjects;
    }

    public void movePlayer (Direction d) {
        myPlayer.setDirection(d);
        Loc targetLoc = myPlayer.getLoc().adjacentLoc(d);
        if (!myViewableObjects.containsKey(targetLoc)) {
            myPlayer.setLoc(targetLoc);
        }
    }
}
