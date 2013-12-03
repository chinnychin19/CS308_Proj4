package author.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import author.mapCreation.CanvasTileManager;

public abstract class MapCreationKeyListener implements KeyListener {

	protected int myKeyNumber;
	protected CanvasTileManager myTileManager;
	
	public MapCreationKeyListener(CanvasTileManager tileManager, int keyNumber) {
		myKeyNumber = keyNumber;
		myTileManager = tileManager;
	}
	
	public int getKeyNumber(){
		return myKeyNumber;
	}

	public abstract void performAction();
	
	public abstract void performPrintUpdate();
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == myKeyNumber){
			performAction();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == myKeyNumber){
			performPrintUpdate();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { /* do nothing... */ }
	
	/*
	
	public class ZoomInListener extends MapCreationKeyListener {
		
	}

	public class UpListener extends MapCreationKeyListener {
		
	}
	
	public class DownListener extends MapCreationKeyListener {
		
	}
	
	public class LeftListener extends MapCreationKeyListener {
		
	}
	
	public class RightListener extends MapCreationKeyListener {
		
	}*/
	
}
