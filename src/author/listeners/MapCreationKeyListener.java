package author.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import constants.Constants;

import author.mapCreation.CanvasTileManager;

public class MapCreationKeyListener implements KeyListener {

	private CanvasTileManager myTileManager;

	public MapCreationKeyListener(CanvasTileManager tileManager) {
		myTileManager = tileManager;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == Constants.ZOOM_IN_KEY){ // 'Z' == Zoom In
			myTileManager.contractView();
		}
		
		if (key == Constants.ZOOM_OUT_KEY){ // 'X' == Zoom Out
			myTileManager.expandView();
		}
		
		if (key == Constants.DOWN_ARROW_KEY){ // Move View Down
			myTileManager.increaseVerticalOffset();
		}
		
		if (key == Constants.UP_ARROW_KEY){ // Move View Up
			myTileManager.decreaseVerticalOffset();
		}
		
		if (key == Constants.LEFT_ARROW_KEY){ // Move View Left
			myTileManager.decreaseHorizontalOffset();
		}
		
		if (key == Constants.RIGHT_ARROW_KEY){ // Move View Right
			myTileManager.increaseHorizontalOffset();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == Constants.ZOOM_IN_KEY || key == Constants.ZOOM_OUT_KEY) {
			// Finished changing window size
			printViewExpandInfo();
		}

		if (	   key == Constants.UP_ARROW_KEY
				|| key == Constants.DOWN_ARROW_KEY
				|| key == Constants.LEFT_ARROW_KEY
				|| key == Constants.RIGHT_ARROW_KEY) { 
			// Finished changing window
			printViewShiftInfo();
		}
	}

	private void printViewExpandInfo(){
		System.out.println("View size changed to " + 
				myTileManager.getTotalHorizontalTiles() + " x " +
				myTileManager.getTotalVerticalTiles() + " tiles"
				);
	}

	private void printViewShiftInfo(){
		System.out.println("Window moved.  Showing "
				+ "columns " + myTileManager.getHorizontalTileNum(0)
				+ " through " + (myTileManager.getHorizontalTileNum(0) + myTileManager.getTotalHorizontalTiles())
				+ " and rows " + myTileManager.getVerticalTileNum(0)
				+ " through " + (myTileManager.getVerticalTileNum(0) + myTileManager.getTotalVerticalTiles())
				);
	}

	@Override
	public void keyTyped(KeyEvent e) { /* do nothing... */ }

}
