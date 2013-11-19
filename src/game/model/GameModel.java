package game.model;

import java.io.IOException;
import java.util.Map;
import location.Direction;
import location.Loc;


public class GameModel {
    private Player myPlayer;
    private World myWorld;
    private StateSaver myStateSaver;

    public GameModel (String nameOfGame) throws Exception {
        myWorld = new World(nameOfGame);
        myPlayer = myWorld.getPlayer();
        myStateSaver = new StateSaver(myWorld, nameOfGame);
    }

    public Player getPlayer () {
        return myPlayer;
    }
    
    public void loadState() {
        try {
            myStateSaver.load();
        }
        catch (Exception e) {
            // Save state file was not found. This will happen the first time, so do nothing.
        }
    }
    
    public void saveState() {
        try {
            myStateSaver.save();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
