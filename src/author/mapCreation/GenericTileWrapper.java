package author.mapCreation;

import java.awt.Image;
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
}
