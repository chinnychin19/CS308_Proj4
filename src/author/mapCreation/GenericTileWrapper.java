package author.mapCreation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import author.model.TileWrapper;
import constants.Constants;


/**
 * Acts as a representation of an object
 * to be placed on a tile in the map creation
 * view.
 * 
 * @author Michael Marion
 * 
 */

public class GenericTileWrapper implements TileWrapper{

    private BufferedImage myImage;
    private String myName;
    private String myType;
    private String myAdditionalInformation;
    private String ALERT = "!!!";

    /**
     * Constructor that allows the user to pass in a BufferedImage object
     * as-is.
     * 
     * @param name
     * @param type
     * @param image
     */
    public GenericTileWrapper (String name, String type, BufferedImage image) {
        myName = name;
        myType = type;
        myImage = image;
    }

    /**
     * Constructor that allows the user to specify a file path instead of a
     * BufferedImage.
     * 
     * @param name
     * @param type
     * @param imagePath
     */
    public GenericTileWrapper (String name, String type, String imagePath) {
        myName = name;
        myType = type;

        try {
            myImage = ImageIO.read(new File(imagePath));
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage () {
        return myImage;
    }

    public String getType () {
        return myType;
    }

    public String getName () {
        return myName;
    }

    /**
     * Return a string representation of the object. If an image exists,
     * display the name of the object. If an image is null or of the
     * wrong type, display an alert in the form of three exclamation points.
     */
    @Override
    public String toString () {
        if (myImage instanceof BufferedImage && myImage != null) {
            return myName + Constants.SPACE_STRING + "(" + myType + ")";
        }
        else {
            return myName + " " + ALERT;
        }
    }

    /**
     * Retrieve any additional JSON information supplied by the user in the form
     * of a string. This information is passed to a JDialog box that is shown when
     * the user selects a given item from the SidebarPanel.
     * 
     * @return Additional JSON information in the form of a string
     */
    public String getMyAdditionalInformation () {
        if (myAdditionalInformation != null && myAdditionalInformation.length() > 0) {
            return myAdditionalInformation;
        }
        else {
            return Constants.EMPTY_STRING;
        }
    }

    /**
     * Called by the SidebarPanel. Allows the user to specify additional
     * JSON information about an object in the form of a plaintext string. The user
     * inputs this string into a dialog box upon choosing an element to place
     * in the map creation view.
     * 
     * @param myAdditionalInformation
     */
    public void setMyAdditionalInformation (String myAdditionalInformation) {
        this.myAdditionalInformation = myAdditionalInformation;
    }

}
