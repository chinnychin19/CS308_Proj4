package author.mapCreation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import location.Loc;


/**
 * WorldCreationMap is a class that contains a HashMap of Locs to
 * GenericTileWrappers, and a method that allows the user to only get the
 * tiles within a certain range, which helps with the scalability of our
 * world size.
 * 
 * @author weskpga
 * 
 */

public class WorldCreationMap {

    private Map<Loc, GenericTileWrapper> myTileMap;

    public WorldCreationMap () {
        myTileMap = new HashMap<Loc, GenericTileWrapper>();
    }

    public void put (Loc location, GenericTileWrapper tile) {
        myTileMap.put(location, tile);
    }

    public void remove (Loc location) {
        myTileMap.remove(location);
    }

    // Maps Type to list of things that are that type
    public Map<String, List<Map<String, String>>> generateJSONDataStructure () {

        // Don't kill me Professor Duvall
        Map<String, List<Map<String, String>>> jsonFormattedMap =
                new HashMap<String, List<Map<String, String>>>();

        for (Entry<Loc, GenericTileWrapper> tile : myTileMap.entrySet()) {
            // GenericTileWrapper currentType = tile.getValue();
            Map<String, String> fieldValueMap = new HashMap<String, String>();
            String type = tile.getValue().getType();

            String name = tile.getValue().getName();
            String x = Integer.toString(tile.getKey().getX());
            String y = Integer.toString(tile.getKey().getY());

            fieldValueMap.put("name", name);
            fieldValueMap.put("x", x);
            fieldValueMap.put("y", y);
            fieldValueMap.put("additional", "");

            if (jsonFormattedMap.containsKey(type)) {
                // List for this type already exists
                jsonFormattedMap.get(type).add(fieldValueMap);
                System.out.println("Value added to " + type + " list");
                printInfo(fieldValueMap, x, y);
            }
            else {
                // Create new List
                List<Map<String, String>> thisTypeList = new ArrayList<Map<String, String>>();
                thisTypeList.add(fieldValueMap);
                System.out.println("New list made for type " + type);
                printInfo(fieldValueMap, x, y);
                jsonFormattedMap.put(type, thisTypeList);
            }
        }
        return jsonFormattedMap;
    }

    private void printInfo (Map<String, String> fieldValueMap, String x, String y) {
        System.out.println(
                "name: " + fieldValueMap.get("name") +
                        "  x: " + fieldValueMap.get("x") + " and for comparison " + x +
                        "  y: " + fieldValueMap.get("y") + " and for comparison " + y);
    }

    public Map<Loc, GenericTileWrapper> getTilesInWindow (int x1, int x2, int y1, int y2) {
        Map<Loc, GenericTileWrapper> currentWindowMap = new HashMap<Loc, GenericTileWrapper>();
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

    public Map<Loc, GenericTileWrapper> getWorldTileMap () {
        return myTileMap;
    }
}
