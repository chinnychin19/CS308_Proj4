package game.model;

import java.awt.Image;
import javax.swing.ImageIcon;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;


public abstract class AbstractGround extends AbstractViewable {
    private Image myImage;

    public AbstractGround (World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(world, definition, objInWorld);
        try {
            String imageURL = definition.getString(Constants.JSON_IMAGE);
            myImage = new ImageIcon(imageURL).getImage();
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean canStepOn () {
        return true;
    }

    @Override
    public Image getImage () {
        return myImage;
    }
}
