package game.model;

import game.controller.AbstractMode;
import game.controller.state.TextState;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import constants.Constants;

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
		
	}

	@Override
	protected void onInteract() {
		if (getLoc().equals(getWorld().locInFrontOfPlayer())) {
			for (Monster monster : getModel().getPlayer().getParty()) {
				monster.heal();
			}
			AbstractMode mode = getModel().getController().getMode();
			// TODO: make constants
			mode.addDynamicState(new TextState(mode, 20, 20, Constants.WIDTH
					- Constants.BORDER_THICKNESS - 20, 100,
					Constants.PROMPT_MONSTERS_HEALED));
		}
	}

	@Override
	protected void onBack() {
		// TODO Auto-generated method stub
		
	}
}
