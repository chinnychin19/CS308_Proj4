package game.model;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import constants.Constants;

public class FightingNPC extends NPC {
    private List<Monster> myParty;
    private String myPostDialogue;
    private List<KeyItem> myKeyItems;
    private int myBet;
    private int myLineOfSightDistance;

    public FightingNPC (World world, JSONObject definition, JSONObject objInWorld) {
        super(world, definition, objInWorld);
        myPostDialogue = definition.get(Constants.JSON_POST_DIALOGUE).toString();
        myKeyItems = new ArrayList<KeyItem>();
        for (Object obj : (JSONArray) definition.get(Constants.JSON_KEYITEMS)) {
            myKeyItems.add(new KeyItem(obj.toString()));
        }
        myBet = Integer.parseInt(definition.get(Constants.JSON_BET).toString());
        myLineOfSightDistance =
                Integer.parseInt(definition.get(Constants.JSON_LINE_OF_SIGHT_DISTANCE).toString());
        // TODO: load the party
    }

    /**
     * Returns the NPC's line of sight distance. Essentially, how far ahead the NPC can see -- used
     * for detecting when the main
     * player is within range to interact with
     * 
     * @return myLineOfSightDistance
     */
    public int getLineOfSightDistance(){
        return myLineOfSightDistance;
    }
        
    /**
     * Returns the NPC's key items. These are items that will help the main player get through
     * obstacles and advance through the game.
     * 
     * @return myKeyItems
     */
    public List<KeyItem> getKeyItem(){
        return myKeyItems;
    } 
    
    /**
     * Returns the NPC's party, containing the monsters that it has
     * @return myParty
     */
    public List<Monster> getParty(){
        return myParty;
    } 
}
