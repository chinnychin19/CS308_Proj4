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
    private Set<KeyItem> myRequiredKeyItems;
    public Obstacle (World world, JSONObject definition, JSONObject objInWorld) {
        super(world, definition, objInWorld);
        String imageURL = definition.get(Constants.JSON_IMAGE).toString();
        myImage = new ImageIcon(imageURL).getImage();
        myRequiredKeyItems = new HashSet<KeyItem>();
        Object keyItemArray = objInWorld.get(Constants.JSON_KEYITEMS);
        if (null != keyItemArray) {
            for (Object name : (JSONArray) keyItemArray) {
                myRequiredKeyItems.add(new KeyItem(name.toString()));
            }            
        }
    }
    @Override
    public Image getImage () {
        return myImage;
    }
    
    public Set<KeyItem> getRequiredKeyItems() {
        return myRequiredKeyItems;
    }
    
    @Override
    public void doInteraction(Player p) {
        //System.out.println(myRequiredKeyItems);
        if(myRequiredKeyItems.isEmpty()) {
            return;
        }
        for(KeyItem item : myRequiredKeyItems){
            if(!p.getKeyItems().contains(item)){
                //TODO: Notify
                System.out.println("MISSING ITEM: "+item.toString());
                return;
            }
        }
        destroy();
    }
}

   
