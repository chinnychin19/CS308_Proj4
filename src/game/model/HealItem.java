package game.model;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import util.jsonwrapper.SmartJsonObject;

public class HealItem extends AbstractViewableObject {

	private Image myImage;
	
	public HealItem(GameModel model, World world, SmartJsonObject definition,
			SmartJsonObject objInWorld) {
		super(model, world, definition, objInWorld);
		
		myImage = new ImageIcon("images/items/healPokeBall.png").getImage();
	}

	@Override
	public Image getImage() {
		return myImage;
		//TODO: consider moving this to abstract
	}
	
	
	/**
	 * Work in progress -- ideally this method will not take any arguments
	 * 
	 */
	public void interact(){
		System.out.println("at Heal item");
		for(Monster monster : getModel().getPlayer().getParty()){
			monster.heal();
		}
	}

}
