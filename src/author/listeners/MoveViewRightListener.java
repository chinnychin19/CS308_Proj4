package author.listeners;

import author.mapCreation.CanvasTileManager;
import constants.Constants;

public class MoveViewRightListener extends MapCreationKeyListener {

	public MoveViewRightListener(CanvasTileManager tileManager) {
		super(tileManager, Constants.RIGHT_ARROW_KEY);
	}

	@Override
	public void performAction() {
		myTileManager.increaseHorizontalOffset();
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
