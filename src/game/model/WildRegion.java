package game.model;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


public abstract class WildRegion extends AbstractViewableObject {

    private Image myImage;
    private double myFreq;
    private List<Monster> myMonsters;

    public WildRegion (World world, SmartJsonObject definition, SmartJsonObject objInWord) {
        super(world, definition, objInWord);

        try {
            String imageURL = definition.getString(Constants.JSON_IMAGE);
            myFreq = definition.getDouble(Constants.JSON_FREQ);
            myImage = new ImageIcon(imageURL).getImage();
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
        // TODO: Implement myMonsters
    }

    // frequency of tile
    // frequnecy of monsters

}
