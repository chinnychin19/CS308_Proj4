package author.model;

import java.awt.image.BufferedImage;

public interface TileWrapper {

    public BufferedImage getImage();

    public String getType();
    
    public String getName();

    /**
     * Return a string representation of the object. If an image exists,
     * display the name of the object. If an image is null or of the
     * wrong type, display an alert in the form of three exclamation points.
     */
    @Override
    public String toString();

    /**
     * Retrieve any additional JSON information supplied by the user in the form
     * of a string. This information is passed to a JDialog box that is shown when
     * the user selects a given item from the SidebarPanel.
     * 
     * @return Additional JSON information in the form of a string
     */
    public String getMyAdditionalInformation(); 

    /**
     * Called by the SidebarPanel. Allows the user to specify additional
     * JSON information about an object in the form of a plaintext string. The user
     * inputs this string into a dialog box upon choosing an element to place
     * in the map creation view.
     * 
     * @param myAdditionalInformation
     */
    public void setMyAdditionalInformation (String myAdditionalInformation);
};
