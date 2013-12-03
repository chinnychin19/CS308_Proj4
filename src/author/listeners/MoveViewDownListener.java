package author.listeners;

import author.mapCreation.CanvasTileManager;
import constants.Constants;

public class MoveViewDownListener extends MapCreationKeyListener {

	public MoveViewDownListener(CanvasTileManager tileManager) {
		super(tileManager, Constants.DOWN_ARROW_KEY);
	}

	@Override
	public void performAction() {
		myTileManager.increaseVerticalOffset();
	}

	@Override
	public void performPrintUpdate() {
		System.out.println("Window moved. Showing "
				+ "columns " + myTileManager.getHorizontalTileNum(0)
				+ " through " + (myTileManager.getHorizontalTileNum(0) + myTileManager.getTotalHorizontalTiles())
				+ " and rows " + myTileManager.getVerticalTileNum(0)
				+ " through " + (myTileManager.getVerticalTileNum(0) + myTileManager.getTotalVerticalTiles())
				);
	}

}
