package author.mapCreation;

import java.awt.image.BufferedImage;

public class GenericTileWrapper {

	private BufferedImage myImage;
	private String myName;
	
	public GenericTileWrapper(String name, BufferedImage image){
		myName = name;
		myImage = image;
	}
	
	public BufferedImage getImage() {
		return myImage;
	}
	
	public String getName(){
		return myName;
	}
	
	@Override
	public String toString() {
            if(myImage instanceof BufferedImage && myImage != null) {
                return myName + " " + "ImageAttached";
            }
            else {
                return myName + " " + "No image!";
            }
	}
}
