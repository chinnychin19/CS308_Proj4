package author.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import author.mapCreation.CanvasTileManager;

public class MapCreationMouseListener implements MouseListener {

	private CanvasTileManager myTileManager;

	public MapCreationMouseListener(CanvasTileManager tileManager){
		myTileManager = tileManager;
	}


	@Override
	public void mouseClicked (MouseEvent arg0) {
		// TODO: Will need to make this do more stuff later
		
		int x = arg0.getX();
		int y = arg0.getY();

		System.out.println("Mouse clicked at: " + x + ", " + y);
		System.out.println("Click translates to tile " +
				"column: " + myTileManager.getHorizontalTileNum(x) + 
				", row: " + myTileManager.getVerticalTileNum(y));
	}

	@Override
	public void mouseEntered (MouseEvent arg0) { /* do nothing... */ }
	@Override
	public void mouseExited (MouseEvent arg0) { /* do nothing... */ }
	@Override
	public void mousePressed (MouseEvent arg0) { /* do nothing... */ }
	@Override
	public void mouseReleased (MouseEvent arg0) { /* do nothing... */ }

}
