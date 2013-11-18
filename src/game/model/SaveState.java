package game.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import constants.Constants;
import jsoncache.JSONReader;
import location.Direction;
import location.Loc;


public class SaveState {

    private World myWorld;
    private String myNameOfGame;
    private Player myPlayer;
    private JSONObject myJSON;

    public SaveState (World world, String nameOfGame) {
        myWorld = world;
        myNameOfGame = nameOfGame;
        myPlayer = myWorld.getPlayer();
    }

    public void load () {

        String worldJSONFilepath = "games/" + myNameOfGame + "/saveState.json";
        myJSON = JSONReader.getJSON(worldJSONFilepath);
        JSONObject playerJSON = (JSONObject) myJSON.get("Player");

        int x = Integer.parseInt(playerJSON.get("x").toString());
        int y = Integer.parseInt(playerJSON.get("y").toString());
        myPlayer.setLoc(new Loc(x, y));
        
        String directionStr = playerJSON.get(Constants.JSON_ORIENTATION).toString();
        myPlayer.setDirection( Direction.constructFromString(directionStr) );
        
        JSONArray playerKeyItems = (JSONArray) playerJSON.get(Constants.JSON_KEYITEMS);
        Collection<KeyItem> keyItems = new ArrayList<KeyItem>();
        for (Object o : playerKeyItems) {
            JSONObject jObj = (JSONObject) o;
            keyItems.add(new KeyItem(jObj.toString()));
        }
        myPlayer.setKeyItems(keyItems);
    }

    @SuppressWarnings("unchecked")
    public void save () throws IOException {
        JSONObject state = new JSONObject();
        JSONObject player = new JSONObject();
        state.put("Player", player);
        
        Loc lastLoc = myPlayer.getLoc();
        player.put("x", lastLoc.getX());
        player.put("y", lastLoc.getY());
        
        String lastDir = myPlayer.getDirection().toString();
        player.put(Constants.JSON_ORIENTATION, lastDir);
        
        String outFile = Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" + Constants.FILENAME_SAVESTATE;
        Writer out = new PrintWriter(new File(outFile));
        JSONValue.writeJSONString(state, out);
    }
}
