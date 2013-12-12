package author.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import constants.Constants;
import author.mapCreation.CanvasTileManager;
import author.mapCreation.MapCreationView;


/**
 * Defines keyboard constants and listens for keyboard events
 * relating to map creation. Dynamically redefines the view
 * of tiles as the user zooms in or out or moves around.
 * 
 * 'Z' = Zoom In
 * 'X' = Zoom Out
 * Arrow Keys = Move View
 * 
 * @author Wes Koorbusch
 * 
 */
public class MapCreationKeyListener implements KeyListener {

    private MapCreationView myView;
    private CanvasTileManager myTileManager;

    public MapCreationKeyListener (MapCreationView view, CanvasTileManager tileManager) {
        myView = view;
        myTileManager = tileManager;
    }

    @Override
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();

        if (key == Constants.ZOOM_IN_KEY) { // 'Z' == Zoom In
            myTileManager.contractView();
            System.out.println(myView.getMyWorldTiles().toString());
            myView.repaint();
        }

        if (key == Constants.ZOOM_OUT_KEY) { // 'X' == Zoom Out
            myTileManager.expandView();
            System.out.println(myView.getMyWorldTiles().toString());
            myView.repaint();
        }

        if (key == Constants.DOWN_ARROW_KEY) { // Move View Down
            myTileManager.increaseVerticalOffset();
            myView.repaint();
        }

        if (key == Constants.UP_ARROW_KEY) { // Move View Up
            myTileManager.decreaseVerticalOffset();
            myView.repaint();
        }

        if (key == Constants.LEFT_ARROW_KEY) { // Move View Left
            myTileManager.decreaseHorizontalOffset();
            myView.repaint();
        }

        if (key == Constants.RIGHT_ARROW_KEY) { // Move View Right
            myTileManager.increaseHorizontalOffset();
            myView.repaint();
        }

    }

    @Override
    public void keyReleased (KeyEvent e) { /* do nothing */ }

    @Override
    public void keyTyped (KeyEvent e) { /* do nothing... */ }

}
