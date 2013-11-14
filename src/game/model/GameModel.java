package game.model;

import java.util.Map;
import location.Direction;
import location.Loc;


public class GameModel {
    private Player myPlayer;
    private World myWorld;

    public GameModel (String nameOfGame) throws Exception {
        myWorld = new World(nameOfGame);
        myPlayer = myWorld.getPlayer();
    }

    public Player getPlayer () {
        return myPlayer; // TODO: return a copy of the player? immutable player? etc...
    }

    public void movePlayer (Direction d) {
        myWorld.movePlayer(d);
    }

    public Map<Loc, AbstractViewableObject> getViewableObjects () {
        return myWorld.getViewableObjects();
    }

    public void doInteraction () {
        myWorld.doInteraction();
    }
}
