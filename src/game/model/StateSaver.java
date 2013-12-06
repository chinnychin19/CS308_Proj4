package game.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import util.jsonwrapper.SmartJsonObject;
import constants.Constants;
import jsoncache.JSONReader;
import location.Direction;
import location.Loc;

/**
 * Class to assist with the Save state - loading and saving a given state of the game
 * @author tylernisonoff, chinmay, austinness
 *
 */
public class StateSaver {
    private GameModel myModel;
    private World myWorld;
    private String myNameOfGame;
    private Player myPlayer;
    private JSONObject myJSON;

    public StateSaver (GameModel model, World world, String nameOfGame) {
        myModel = model;
        myWorld = world;
        myNameOfGame = nameOfGame;
        myPlayer = myWorld.getPlayer();
    }

    /**
     * Attempts to load the saveState file from the game's directory
     * @throws Exception - throws if no file is found
     */
    public void load () throws Exception {
        String worldJSONFilepath =
                Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                        Constants.FILENAME_SAVESTATE;
        myJSON = JSONReader.getJSON(worldJSONFilepath);
        if (myJSON == null) { throw new Exception(Constants.SAVE_FILE_NOT_FOUND); }
        try {
            SmartJsonObject playerJSON =
                    new SmartJsonObject((JSONObject) myJSON.get(Constants.JSON_PLAYER));

            myPlayer.readSaveState(playerJSON);

//            JSONArray defeatedArray = (JSONArray) myJSON.get("Defeated");
//            for (Object o : defeatedArray) {
//                SmartJsonObject npc = new SmartJsonObject((JSONObject) o);
//                AbstractViewableObject obj = myWorld.getViewableObject(new Loc(npc.getInt("x"), npc.getInt("y")));
//                ((FightingNPC) obj).setDefeated(true);
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the state 
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void save () throws IOException {
        JSONObject state = new JSONObject();
        JSONObject player = new JSONObject();
        state.put(Constants.JSON_PLAYER, player);

        Loc lastLoc = myPlayer.getLoc();
        player.put(Constants.JSON_X, lastLoc.getX());
        player.put(Constants.JSON_Y, lastLoc.getY());

        String lastDir = myPlayer.getDirection().toString();
        player.put(Constants.JSON_ORIENTATION, lastDir);

        String outFile =
                Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                        Constants.FILENAME_SAVESTATE;
        Writer out = new PrintWriter(new File(outFile));
        JSONValue.writeJSONString(state, out);
    }
}
