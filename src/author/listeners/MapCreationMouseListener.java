package author.listeners;

// import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import constants.Constants;
import author.mapCreation.CanvasTileManager;
import author.mapCreation.MapCreationView;


/**
 * Listener that listens for mouse events on the MapCreationView.
 * Retrieves the coordinates of a mouse click and converts the
 * coordinates into a game tile depending on the user's current
 * view.
 * 
 * @author Michael Marion, weskpga
 * 
 */
public class MapCreationMouseListener implements MouseMotionListener, MouseListener {

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
    public void mouseDragged (MouseEvent e) {
        JPanel parentPanel = (JPanel) e.getSource();
        parentPanel.requestFocus();

        int x = e.getX();
        int y = e.getY();

        int xTile = myTileManager.getHorizontalTileNum(x);
        int yTile = myTileManager.getVerticalTileNum(y);

        System.out.println(Constants.MOUSE_CLICKED_MESSAGE + x + ", " + y);
        System.out.println(Constants.CLICK_TILE_MESSAGE +
                           Constants.COLUMN_MESSAGE + myTileManager.getHorizontalTileNum(x) +
                           Constants.ROW_MESSAGE + myTileManager.getVerticalTileNum(y));

        myMapCreationView.paintAndRecordTile((Graphics2D) myMapCreationView.getGraphics(), xTile,
                                             yTile);

        System.out.println(myMapCreationView.getMyWorldTiles().toString());
    }

    @Override
    public void mouseMoved (MouseEvent e) { /* do nothing... */ }

    /**
     * Handles a mouse click event, which can include a click on a JList
     * item in the object panel on the right, or a click on the map, in
     * which case the map will create/delete a tile.
     * 
     */
    @Override
    public void mouseClicked (MouseEvent e) {
        
        JPanel parentPanel = (JPanel) e.getSource();
        parentPanel.requestFocus();

        int x = e.getX();
        int y = e.getY();

        int xTile = myTileManager.getHorizontalTileNum(x);
        int yTile = myTileManager.getVerticalTileNum(y);

        
        if(SwingUtilities.isLeftMouseButton(e)) {
            System.out.println(Constants.MOUSE_CLICKED_MESSAGE + x + ", " + y);
            System.out.println(Constants.CLICK_TILE_MESSAGE +
                               Constants.COLUMN_MESSAGE + myTileManager.getHorizontalTileNum(x) +
                               Constants.ROW_MESSAGE + myTileManager.getVerticalTileNum(y));

            myMapCreationView.paintAndRecordTile((Graphics2D) myMapCreationView.getGraphics(), xTile,
                                                 yTile);

            System.out.println(myMapCreationView.getMyWorldTiles().toString());
        }
        
        else if (SwingUtilities.isRightMouseButton(e)) {
            myMapCreationView.removeTileFromMap((Graphics2D) myMapCreationView.getGraphics(), xTile, yTile);
            myMapCreationView.repaint();
        }
        
    }

    @Override
    public void mousePressed (MouseEvent e) { /* do nothing */}

    @Override
    public void mouseReleased (MouseEvent e) { /* do nothing */}

    @Override
    public void mouseEntered (MouseEvent e) { /* do nothing */}

    @Override
    public void mouseExited (MouseEvent e) { /* do nothing */}

}
