package game.model;

import game.controller.Input;
import game.controller.AbstractMode;
import game.controller.state.TextState;

import java.util.ArrayList;
import java.util.List;

import location.Direction;
import location.Loc;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

/**
 * A class used to create FightingNPCs and to interact with the Player.  The NPC will check to see whether or not the Player is within its line of sight.  If so,
 * the NPC will move towards the player and go into battle mode.
 * @author rtoussaint
 *
 */

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
     * Check to see if the FightingNPC should interact with the Player
     */
    @Override
    public void doFrame(World w, Input input) {
    	 
    	boolean playerWithinRange = checkLineOfSight();
    	if(playerWithinRange){
    		moveTowardsPlayer();
        	//TODO: Chinmay uncomment onInteract method when you implement it.
    		AbstractMode mode = getModel().getController().getMode();
    		mode.addDynamicState(new TextState(mode,
                    Constants.BORDER_THICKNESS,
                    Constants.HEIGHT - Constants.BORDER_THICKNESS -
                            Constants.DIALOGUE_HEIGHT,
                    Constants.WIDTH - 2 * Constants.BORDER_THICKNESS,
                    Constants.DIALOGUE_HEIGHT,
                    Constants.PROMPT_FIGHTING_NPC_BEFORE_BATTLE));
    		
    		//onInteract();	
    		/*mode.addDynamicState(new TextState(mode,
                    Constants.BORDER_THICKNESS,
                    Constants.HEIGHT - Constants.BORDER_THICKNESS -
                            Constants.DIALOGUE_HEIGHT,
                    Constants.WIDTH - 2 * Constants.BORDER_THICKNESS,
                    Constants.DIALOGUE_HEIGHT,
                    myPostDialogue));
                    */
    		//TODO: uncomment post dialogue box above
    		//TODO: move NPC back to original spot??
    	}
    }
        

    /**
     * Check to see if the player is within the line of sight of the player
     * @return whether or not the NPC can 'see' the player
     */
	private boolean checkLineOfSight() {
    	int sight = 0;
		Loc tempLoc = getLoc();
    	while(sight <= myLineOfSightDistance){
    		if(tempLoc.equals(getModel().getPlayer().getLoc())){
    			return true;
    		}
    		tempLoc = tempLoc.adjacentLoc(getDirection());
    		sight++;	
    	}
		return false;
	}
	
	/**
	 * move towards the player when it is within the NPC's line of sight
	 */
    private void moveTowardsPlayer() {
    	///TODO: freeze player keyboard for movement/animation
    	Direction oppositeDirection = Direction.opposite(getDirection());
    	while(!getLoc().equals(getModel().getPlayer().getLoc().adjacentLoc(oppositeDirection))){
    		setLoc(getLoc().adjacentLoc(getDirection()), getWorld());
    	}
    	getModel().getPlayer().setDirection(oppositeDirection);
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
    
    @Override
    public void onInteract() {
        super.onInteract();
        AbstractMode mode = getModel().getController().getMode();
        //TODO: Wrap Dialogue every 63 characters (the amount for one line)
        mode.addDynamicState(new TextState(mode, 
                    Constants.BORDER_THICKNESS, 
                                    Constants.HEIGHT - Constants.BORDER_THICKNESS - Constants.DIALOGUE_HEIGHT, 
                                    Constants.WIDTH - 2*Constants.BORDER_THICKNESS, 
                                    Constants.DIALOGUE_HEIGHT,  
                                    getDialogue()));
    }
}
