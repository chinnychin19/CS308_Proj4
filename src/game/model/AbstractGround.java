package game.model;

import java.awt.Image;
import javax.swing.ImageIcon;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;

/**
 * The abstract class that deals with terrain in the game.  This class establishes what image is set as the ground and if 
 * the terrain can be stepped on by other objects that are movable.
 * @author tylernisonoff
 *
 */
public abstract class AbstractGround extends AbstractViewable {
    private Image myImage;

    public AbstractGround (GameModel model, World world, SmartJsonObject definition, SmartJsonObject objInWorld) {
        super(model, world, definition, objInWorld);
    }

    @Override
    public boolean canStepOn () {
        return true;
    }

    @Override
    public Image getImage () {
        return myImage;
    }
   
    @Override
    protected void readDefinition (SmartJsonObject definition) throws SmartJsonException {
        super.readDefinition(definition);
        String imageURL = definition.getString(Constants.JSON_IMAGE);
        myImage = new ImageIcon(imageURL).getImage();    
    }
}
