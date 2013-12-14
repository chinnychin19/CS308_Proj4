package author.mapCreation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import author.model.TileWrapper;

import constants.Constants;
import location.Loc;


/**
 * WorldCreationMap is a class that contains a HashMap of Locs to
 * TileWrappers, and a method that allows the user to only get the
 * tiles within a certain range, which helps with the scalability of our
 * world size.
 * 
 * @author weskpga
 * 
 */

public class WorldCreationMap {
	
	private Map<Loc, TileWrapper> myTileMap;

    public WorldCreationMap () {
        myTileMap = new HashMap<Loc, TileWrapper>();
    }

    public void put (Loc location, TileWrapper tile) {
        myTileMap.put(location, tile);
    }

    public void remove (Loc location) {
        myTileMap.remove(location);
    }

    
    public Map<String, List<Map<String, String>>> generateJSONDataStructure () {

        // Don't kill me Professor Duvall
        Map<String, List<Map<String, String>>> jsonFormattedMap =
                new HashMap<String, List<Map<String, String>>>();

        for (Entry<Loc, TileWrapper> tile : myTileMap.entrySet()) {

            Map<String, String> fieldValueMap = new HashMap<String, String>();
            String type = tile.getValue().getType();

            String name = tile.getValue().getName();
            String x = Integer.toString(tile.getKey().getX());
            String y = Integer.toString(tile.getKey().getY());

            fieldValueMap.put(Constants.MAP_NAME, name);
            fieldValueMap.put(Constants.MAP_X_STRING, x);
            fieldValueMap.put(Constants.MAP_Y_STRING, y);
            fieldValueMap.put(Constants.ADDITIONAL, "");

            if (jsonFormattedMap.containsKey(type)) {
                // List for this type already exists
                jsonFormattedMap.get(type).add(fieldValueMap);
                System.out.println(Constants.VALUE_ADDED_TO + type + Constants.LIST_STRING);
                printInfo(fieldValueMap, x, y);
            }
            else {
                // Create new List
                List<Map<String, String>> thisTypeList = new ArrayList<Map<String, String>>();
                thisTypeList.add(fieldValueMap);
                System.out.println(Constants.NEW_LIST_MADE_FOR_TYPE + type);
                printInfo(fieldValueMap, x, y);
                jsonFormattedMap.put(type, thisTypeList);
            }
        }
        return jsonFormattedMap;
    }

    private void printInfo (Map<String, String> fieldValueMap, String x, String y) {
        System.out.println(
        		Constants.NAME_COLON + fieldValueMap.get(Constants.MAP_NAME) +
                        Constants.X_COLON + fieldValueMap.get(Constants.MAP_X_STRING) + Constants.AND_FOR_COMPARISON + x +
                        Constants.Y_COLON + fieldValueMap.get(Constants.MAP_Y_STRING) + Constants.AND_FOR_COMPARISON + y);
    }

    public Map<Loc, TileWrapper> getTilesInWindow (int x1, int x2, int y1, int y2) {
        Map<Loc, TileWrapper> currentWindowMap = new HashMap<Loc, TileWrapper>();
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                Loc currentLoc = new Loc(i, j);
                if (myTileMap.containsKey(currentLoc)) {
                    currentWindowMap.put(currentLoc, myTileMap.get(currentLoc));
                }
            }
        }
        return currentWindowMap;
    }

    public Map<Loc, TileWrapper> getWorldTileMap () {
        return myTileMap;
    }
}
