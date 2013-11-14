package game.model;

import java.awt.Image;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Obstacle extends AbstractViewableObject {
    private Image myImage;
    private Set<String> myRequiredKeyItems;
    public Obstacle (JSONObject definition, JSONObject objInWorld) {
        super(definition, objInWorld);
        String imageURL = definition.get("image").toString();
        myImage = new ImageIcon(imageURL).getImage();
        myRequiredKeyItems = new HashSet<String>();
        Object keyItemArray = objInWorld.get("keyItems");
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
}

   
