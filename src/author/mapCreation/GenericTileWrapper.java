package author.mapCreation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.json.simple.JSONObject;

public class GenericTileWrapper {

	private BufferedImage myImage;
	private String myName;
	private String myType;
	private String myAdditionalInformation;
	
	public GenericTileWrapper(String name, String type, BufferedImage image){
		myName = name;
		myType = type;
		myImage = image;
	}
	
	public GenericTileWrapper(String name, String type, String imagePath){
            myName = name;
            myType = type;
            
            try {
                myImage = ImageIO.read(new File(imagePath));
            }
            
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
	
	public BufferedImage getImage() {
	    return myImage;
	}
	
	public String getType() {
	    return myType;
	}
	
	public String getName(){
	    return myName;
	}
	
	@Override
	public String toString() {
            if(myImage instanceof BufferedImage && myImage != null) {
                return myName + " (" + myType + ")";
            }
            else {
                return myName + " " + "!!!";
            }
	}

    public String getMyAdditionalInformation () {
        if(myAdditionalInformation != null && myAdditionalInformation.length() > 0) {
            return myAdditionalInformation;
        }
        else {
            return "";
        }
    }

    public void setMyAdditionalInformation (String myAdditionalInformation) {
        this.myAdditionalInformation = myAdditionalInformation;
    }
	
	
}
