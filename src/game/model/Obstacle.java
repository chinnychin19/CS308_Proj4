package game.model;

import game.controller.AbstractMode;
import game.controller.Input;
import game.controller.state.TextState;

import java.awt.Image;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;

import location.Direction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

/**
 * Represents an Obstacle in the game, such as a tree
 * @author tylernisonoff
 *
 */
public class Obstacle extends AbstractViewableObject {
    private Image myImage;
    private Set<KeyItem> myRequiredKeyItems;
    public Obstacle (GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld);
        try{
            String imageURL = definition.getString(Constants.JSON_IMAGE);
            myImage = new ImageIcon(imageURL).getImage();
            myRequiredKeyItems = new HashSet<KeyItem>();
            JSONArray keyItemArray = objInWorld.getJSONArray(Constants.JSON_KEYITEMS);
            if (null != keyItemArray) {
                for (Object name : keyItemArray) {
                    myRequiredKeyItems.add(new KeyItem(model, name.toString()));
                }            
            }
        } catch(SmartJsonException e){
            e.printStackTrace();
        }
        
    }
    
    /**
     * Returns the Image of the obstacle
     */
    @Override
    public Image getImage () {
        return myImage;
    }
    
    /**
     * 
     * @return - The required key Items for an obstacle to disappear
     */
    public Set<KeyItem> getRequiredKeyItems() {
        return myRequiredKeyItems;
    }
    
    /**
     * Called when a player is allowed to interact with an obstacle given
     * that it has the required key items
     * 
     * Performs the necessary action
     * For a default Obstacle, this will destroy the obstacle
     */
    public void playerInteractingWithKeyItems(){
        destroy();
    }

    /**
     * Checks to see if a player is trying to interact with it
     * If it is, it checks to see if the player has the required key items
     * It if does, it calls playerInteractingWithKeyItems
     */
	@Override
	protected void onInteract() {
		if (getLoc().equals(getWorld().locInFrontOfPlayer())) {
            if(myRequiredKeyItems.isEmpty()) {
                return;
            }
            for(KeyItem item : myRequiredKeyItems){
                if(!getWorld().getPlayer().getKeyItems().contains(item)){
                            AbstractMode mode = getModel().getController().getMode();
                            //TODO: Make Constants
                            mode.addDynamicState(new TextState(mode, 20, 20, Constants.WIDTH-Constants.BORDER_THICKNESS-20, 100, Constants.PROMPT_MISSING_ITEM+item.toString() + Constants.PROMPT_AQUIRE_MISSING_ITEM));
                    return;
                }
            }
            playerInteractingWithKeyItems();
        }
	}

	@Override
	protected void onBack() {
		// TODO Auto-generated method stub
		
	}
}

   
