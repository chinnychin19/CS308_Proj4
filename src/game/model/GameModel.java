package game.model;

import java.util.Map;
import util.Loc;


public class GameModel {
    private Player myPlayer;
    private World myWorld;

    public GameModel (String nameOfGame) {
        // TODO: load the definition.json and world.json and saveState.json files to render the
        // world
        myPlayer = new Player(0, 0);
        myWorld = new World();
        initWorldForDemoPurposesThisMethodSucks();
    }

    private void initWorldForDemoPurposesThisMethodSucks () {
//        myWorld.addViewableObject(new Loc(1,1), new Obstacle());
    }

    public Player getPlayer () {
        return myPlayer; // TODO: return a copy of the player? immutable player? etc...
    }

    public Map<Loc, AbstractViewableObject> getViewableObjects () {
        return myWorld.getViewableObjects();
    }
}
