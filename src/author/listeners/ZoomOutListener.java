package author.listeners;

import constants.Constants;

import author.mapCreation.CanvasTileManager;

public class ZoomOutListener extends MapCreationKeyListener {

	public ZoomOutListener(CanvasTileManager tileManager) {
		super(tileManager, Constants.ZOOM_OUT_KEY);
	}

	@Override
	public void performAction() {
		myTileManager.expandView();
	}

	@Override
	public void performPrintUpdate() {
		System.out.println("View size changed to " + 
				myTileManager.getTotalHorizontalTiles() + " x " +
				myTileManager.getTotalVerticalTiles() + " tiles"
				);
	}

}
