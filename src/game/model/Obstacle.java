package game.model;

import java.awt.Image;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;

import constants.Constants;

public class Obstacle extends AbstractInteractableObject {
    private Image myImage;
    private Set<KeyItem> myRequiredKeyItems;
    public Obstacle (World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(world, definition, objInWorld);
        try{
            String imageURL = definition.getString(Constants.JSON_IMAGE);
            myImage = new ImageIcon(imageURL).getImage();
            myRequiredKeyItems = new HashSet<KeyItem>();
            JSONArray keyItemArray = objInWorld.getJSONArray(Constants.JSON_KEYITEMS);
            if (null != keyItemArray) {
                for (Object name : keyItemArray) {
                    myRequiredKeyItems.add(new KeyItem(name.toString()));
                }            
            }
        } catch(SmartJsonException e){
            e.printStackTrace();
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

   
