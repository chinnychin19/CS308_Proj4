package author.listeners;

import constants.Constants;
import author.mapCreation.CanvasTileManager;

public class ZoomInListener extends MapCreationKeyListener {

	public ZoomInListener(CanvasTileManager tileManager) {
		super(tileManager, Constants.ZOOM_IN_KEY);
	}

	@Override
	public void performAction() {
		myTileManager.contractView();
	}

	@Override
	public void performPrintUpdate() {
		System.out.println("View size changed to " + 
				myTileManager.getTotalHorizontalTiles() + " x " +
				myTileManager.getTotalVerticalTiles() + " tiles"
				);
	}
	
	
	
}