package author.listeners;

// import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import constants.Constants;
import author.mapCreation.CanvasTileManager;
import author.mapCreation.MapCreationView;


/**
 * Listener that listens for mouse events on the MapCreationView.
 * Retrieves the coordinates of a mouse click and converts the
 * coordinates into a game tile depending on the user's current
 * view.
 * 
 * @author Michael Marion
 * 
 */
public class MapCreationMouseListener implements MouseListener {

    private CanvasTileManager myTileManager;
    private MapCreationView myMapCreationView;

    public MapCreationMouseListener (MapCreationView mapCreation, CanvasTileManager tileManager) {
        myTileManager = tileManager;
        myMapCreationView = mapCreation;
    }

    /**
     * Retrieves the coordinates of the mouse click and
     * translates those x- and y-coordinates into a
     * second set of x- and y-coordinates that correspond
     * to a system of tiles. Paints the corresponding tile.
     */

    @Override
    public void mouseClicked (MouseEvent arg0) {
        
        JPanel parentPanel = (JPanel) arg0.getSource();
        parentPanel.requestFocus();

        int x = arg0.getX();
        int y = arg0.getY();

        int xTile = myTileManager.getHorizontalTileNum(x);
        int yTile = myTileManager.getVerticalTileNum(y);

        System.out.println(Constants.MOUSE_CLICKED_MESSAGE + x + ", " + y);
        System.out.println(Constants.CLICK_TILE_MESSAGE + 
                           Constants.COLUMN_MESSAGE + myTileManager.getHorizontalTileNum(x) + 
                           Constants.ROW_MESSAGE + myTileManager.getVerticalTileNum(y));

        //myTileManager.getTileClickLoc(xTile, yTile);

        myMapCreationView.paintAndRecordTile((Graphics2D) myMapCreationView.getGraphics(), xTile, yTile);
        
        System.out.println(myMapCreationView.getMyWorldTiles().toString());
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
