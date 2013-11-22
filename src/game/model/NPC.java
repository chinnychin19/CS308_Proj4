package game.model;

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
        
    public NPC (World world, SmartJsonObject definition, SmartJsonObject objInWorld){
        super(world, definition, objInWorld);
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
    
    @Override
    public void doInteraction(Player p) {
        setDirection(Direction.opposite(p.getDirection()));
        System.out.println(myDialogue);
    }
}
