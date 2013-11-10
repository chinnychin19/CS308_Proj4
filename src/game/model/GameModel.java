package game.model;

import java.util.Collection;
import java.util.HashMap;
import util.Loc;


public class GameModel {
    private Player myPlayer;
    private HashMap<Loc, AbstractViewableObject> myViewableObjects;

    public GameModel (String nameOfGame) {
        // TODO: load the definition.json and world.json and saveState.json files to render the
        // world
        myPlayer = new Player(0, 0);
        myViewableObjects = new HashMap<Loc, AbstractViewableObject>();
    }

    public Player getPlayer () {
        return myPlayer; // TODO: return a copy of the player? immutable player? etc...
    }

    public void addViewableObject (Loc loc, AbstractViewableObject obj) {
        myViewableObjects.put(loc, obj);
    }
}
