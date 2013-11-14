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
    public Obstacle (JSONObject definition, JSONObject objInWorld) {
        super(definition, objInWorld);
        String imageURL = definition.get(Constants.JSON_IMAGE).toString();
        System.out.println(imageURL);
        myImage = new ImageIcon(imageURL).getImage();
        myRequiredKeyItems = new HashSet<String>();
        for (Object name : (JSONArray) objInWorld.get(Constants.JSON_KEYITEMS)) {
            myRequiredKeyItems.add(name.toString());
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

   
