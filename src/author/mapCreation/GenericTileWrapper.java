package author.mapCreation;

import java.awt.Image;

public class GenericTileWrapper {

	private Image myImage;
	private String myName;
	
	public GenericTileWrapper(String name, Image image){
		myName = name;
		myImage = image;
	}
	
	public Image getImage() {
		return myImage;
	}
	
	public String getName(){
		return myName;
	}
}
