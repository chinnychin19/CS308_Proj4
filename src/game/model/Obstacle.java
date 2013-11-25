package game.model;

import game.controller.AbstractMode;
import game.controller.Input;

import java.awt.Image;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;

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
    @Override
    public Image getImage () {
        return myImage;
    }
    
    public Set<KeyItem> getRequiredKeyItems() {
        return myRequiredKeyItems;
    }
    
    @Override
    public void doFrame(World w, Input input) {
        if (input.isKeyInteractPressed() && getLoc().equals(w.locInFrontOfPlayer())) {
            if(myRequiredKeyItems.isEmpty()) {
                return;
            }
            for(KeyItem item : myRequiredKeyItems){
                if(!w.getPlayer().getKeyItems().contains(item)){
                    System.out.println("MISSING ITEM: "+item.toString());
                    return;
                }
            }
            destroy();            
        }
    }
}

   
