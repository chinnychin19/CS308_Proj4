package game.model;

import java.awt.Image;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import constants.Constants;

public class Obstacle extends AbstractViewableObject {
    private Image myImage;
    private Set<String> myRequiredKeyItems;
    public Obstacle (World world, JSONObject definition, JSONObject objInWorld) {
        super(world, definition, objInWorld);
        String imageURL = definition.get(Constants.JSON_IMAGE).toString();
        myImage = new ImageIcon(imageURL).getImage();
        myRequiredKeyItems = new HashSet<String>();
        Object keyItemArray = objInWorld.get(Constants.JSON_KEYITEMS);
        if (null != keyItemArray) {
            for (Object name : (JSONArray) keyItemArray) {
                myRequiredKeyItems.add(name.toString());
            }            
        }
    }
    @Override
    public Image getImage () {
        return myImage;
    }
    
    public Set<String> getRequiredKeyItems() {
        return myRequiredKeyItems;
    }
    
    @Override
    public void doInteraction(Player p) {
        //TODO: put a delay on this
        // Immediately check if this requires any key items. If not, then return.
        // First check if player has all required key items
        // If not, say what the player needs
        // If yes, destroy
        System.out.println(myRequiredKeyItems);
        //TODO: implement proper behavior
    }
}

   
