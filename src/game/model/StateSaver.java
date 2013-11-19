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


public class StateSaver {

    private World myWorld;
    private String myNameOfGame;
    private Player myPlayer;
    private JSONObject myJSON;

    public StateSaver (World world, String nameOfGame) {
        myWorld = world;
        myNameOfGame = nameOfGame;
        myPlayer = myWorld.getPlayer();
    }

    public void load () throws Exception {
        String worldJSONFilepath =
                Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" +
                        Constants.FILENAME_SAVESTATE;
        myJSON = JSONReader.getJSON(worldJSONFilepath);
        if (myJSON == null) {
            throw new Exception("Save file not found");
        }
        JSONObject playerJSON = (JSONObject) myJSON.get(Constants.JSON_PLAYER);

        int x = Integer.parseInt(playerJSON.get(Constants.JSON_X).toString());
        int y = Integer.parseInt(playerJSON.get(Constants.JSON_Y).toString());
        myPlayer.getLoc().setX(x);
        myPlayer.getLoc().setY(y);
        
        String directionStr = playerJSON.get(Constants.JSON_ORIENTATION).toString();
        myPlayer.setDirection(Direction.constructFromString(directionStr));

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
        state.put(Constants.JSON_PLAYER, player);

        Loc lastLoc = myPlayer.getLoc();
        player.put(Constants.JSON_X, lastLoc.getX());
        player.put(Constants.JSON_Y, lastLoc.getY());

        String lastDir = myPlayer.getDirection().toString();
        player.put(Constants.JSON_ORIENTATION, lastDir);
        
        String outFile = Constants.FOLDERPATH_GAMES + "/" + myNameOfGame + "/" + Constants.FILENAME_SAVESTATE;
        Writer out = new PrintWriter(new File(outFile));
        JSONValue.writeJSONString(state, out);
    }
}
