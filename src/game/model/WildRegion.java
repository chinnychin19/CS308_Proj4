package game.model;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import org.json.simple.JSONObject;
import constants.Constants;

public abstract class WildRegion extends AbstractViewableObject {

    private Image myImage;
    private double myFreq;
    private List<Monster> myMonsters;
	
    public WildRegion (World world, JSONObject definition, JSONObject objInWord) {
        super(world, definition, objInWord);

        String imageURL = definition.get(Constants.JSON_IMAGE).toString();
        myFreq = Double.parseDouble(definition.get(Constants.JSON_FREQ).toString());
        myImage = new ImageIcon(imageURL).getImage();
        
        //TODO: Implement myMonsters
    }

    //frequency of tile
    //frequnecy of monsters
    
}
