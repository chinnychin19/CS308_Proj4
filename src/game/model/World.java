package game.model;

import java.util.HashMap;
import java.util.Map;
import util.Loc;


public class World {
    private HashMap<Loc, AbstractViewableObject> myViewableObjects;

    public void addViewableObject (Loc loc, AbstractViewableObject obj) {
        myViewableObjects.put(loc, obj);
    }

    public Map<Loc, AbstractViewableObject> getViewableObjects () {
        return myViewableObjects;
    }
}
