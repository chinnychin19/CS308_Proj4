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
    private String myNameOfGame;
    private JSONObject myJSON;

    public StateSaver (GameModel model, World world, String nameOfGame) {
        myModel = model;
        myNameOfGame = nameOfGame;
    }

    /**
     * Attempts to load the saveState file from the game's directory
     * @throws Exception - throws if no file is found
     */
    public World load () throws Exception {
        String worldJSONFilepath =
                Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                       "saveState2.json";// Constants.FILENAME_SAVESTATE;
        myJSON = JSONReader.getJSON(worldJSONFilepath);
        if (myJSON == null) { throw new Exception(Constants.SAVE_FILE_NOT_FOUND); }
        try {
            return new World(myNameOfGame, myModel);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Saves the state 
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void save () throws IOException {
        myModel.getPlayer().saveThisLoc();
        JSONObject state = new JSONObject();
        JSONArray playerArray = new JSONArray();
        playerArray.add(myModel.getPlayer().getSavedJson());
        state.put(Constants.JSON_PLAYER, playerArray);
        state.put(Constants.JSON_NPC, getJSONArray(myModel.getWorld().getAllNPCs()));
        state.put(Constants.JSON_FIGHTING_NPC, getJSONArray(myModel.getWorld().getAllFightingNPCs()));
        state.put(Constants.JSON_OBSTACLE, getJSONArray(myModel.getWorld().getAllObstacles()));
        state.put(Constants.JSON_HEAL_ITEM, getJSONArray(myModel.getWorld().getAllHealItems()));
        state.put(Constants.JSON_WILD_REGION, getJSONArray(myModel.getWorld().getAllWildRegions()));
        System.out.println(state.toString());
        String outFile =
                Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                        "saveState2.json";
        Writer out = new PrintWriter(new File(outFile));
        JSONValue.writeJSONString(state, out);
        out.close();
    }
    
    private JSONArray getJSONArray(List<Saveable> list){
        JSONArray array = new JSONArray();
        for(Saveable obj : list){
            array.add(obj.getSavedJson());
        }
        return array;
    }
}
