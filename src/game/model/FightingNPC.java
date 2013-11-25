package game.model;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

public class FightingNPC extends NPC implements Fighter {
    private List<Monster> myParty;
    private String myPostDialogue;
    private List<KeyItem> myKeyItems;
    private int myBet;
    private int myLineOfSightDistance;

    public FightingNPC (GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld);
        try{
        myPostDialogue = definition.getString(Constants.JSON_POST_DIALOGUE);
        myKeyItems = new ArrayList<KeyItem>();
        for (Object obj : definition.getJSONArray(Constants.JSON_KEYITEMS)) {
            myKeyItems.add(new KeyItem(model, obj.toString()));
        }
        myBet = definition.getInt(Constants.JSON_BET);
        myLineOfSightDistance = definition.getInt(Constants.JSON_LINE_OF_SIGHT_DISTANCE);
        // TODO: load the party
        } catch(SmartJsonException e){
            e.printStackTrace();
        }
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
    @Override
    public List<Monster> getParty(){
        return myParty;
    } 
}
