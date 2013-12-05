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

public class FightingNPC extends NPC implements Fighter {
    private List<Monster> myParty;
    private String myPostDialogue;
    private List<KeyItem> myKeyItems;
    private int myBet;
    private int myLineOfSightDistance;
    private Direction myDirection;
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
     * Check line of site of NPC with current positionf of the player
     */
    @Override
    public void doFrame(World w, Input input) {
    	 
    	boolean playerWithinRange = checkLineOfSight();
    	
    	System.out.println("check lineOfSight boolean is:" + playerWithinRange);
    	
    	if (input.isKeyInteractPressed()) {
            onInteract();
        }

        if (input.isKeyBackPressed()) {
            onBack();
        }
    }
        
    private boolean checkLineOfSight() {
    	System.out.println("inside method");
    	int sight = 0;
		Loc tempLoc = this.getLoc();
    	while(sight <= myLineOfSightDistance){
    		System.out.println("inside while loop");
    		if(tempLoc.equals(getModel().getPlayer().getLoc())){
    			System.out.println("location is equal");
    			return true;
			}
    		else if(myDirection == Direction.UP){
    			System.out.println("up change");
    			tempLoc.setY(tempLoc.getY()-1);
    		}
    		else if(myDirection == Direction.LEFT){
    			System.out.println("left change");
    			tempLoc.setX(tempLoc.getX()-1);
    		}
    		else if(myDirection == Direction.DOWN){
    			System.out.println("down change");
    			tempLoc.setY(tempLoc.getY()+1);
    		}
    		//right
    		else{
    			System.out.println("right change");
    			tempLoc.setX(tempLoc.getX()+1);
    		}
    		sight++;
    		
    	}
    	
    	// TODO Auto-generated method stub
		return false;
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
