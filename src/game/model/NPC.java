package game.model;

import game.controller.AbstractMode;
import game.controller.Input;
import game.controller.state.TextState;
import game.controller.state.option.TextOptionState;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import jsoncache.JSONCache;
import location.Direction;
import location.Loc;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

/**
 * NPC Class that creates a new of a non-player character and initializes all of its variables based on reading in the correct
 * JSON information.
 * @author rtoussaint
 *
 */

public class NPC extends AbstractCharacter {

    private String myDialogue;
        
    public NPC (GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWorld){
        super(model, world, definition, objInWorld);
        try{
            myDialogue = definition.getString(Constants.JSON_DIALOGUE);
        } catch(SmartJsonException e){
            e.printStackTrace();
        }
    }
                
    /**
     * Returns the dialogue of the NPC.  This is the speech that is displayed when the main player interacts with it
     * @return myDialogue
     */
    public String getDialogue(){
    	return myDialogue;
    }
  
    
    public void paintDialogue(){
    	
    }

    /**
     * Checks to see if a user is trying to interact with it
     * If so, it shows the dialogue
     */
	@Override
	protected void onInteract() {
		if (getLoc().equals(getWorld().locInFrontOfPlayer())) {
            setDirection(Direction.opposite(getWorld().getPlayer().getDirection()));
            System.out.println(myDialogue);
            AbstractMode mode = getModel().getController().getMode();
            //TODO: Make Constants
            mode.addDynamicState(new TextState(mode, 20, 20, Constants.WIDTH-Constants.BORDER_THICKNESS-20, 100, myDialogue));
        }
	}

	@Override
	protected void onBack() {

	}
}
