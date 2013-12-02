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
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Work in progress -- ideally this method will not take any arguments
	 * 
	 */
	public void interact(List<Monster> monsterParty){
		for(Monster monster : monsterParty){
			monster.heal();
		}
	}

}
