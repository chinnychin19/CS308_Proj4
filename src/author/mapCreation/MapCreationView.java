package author.mapCreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import location.Loc;
import author.listeners.MapCreationKeyListener;
import author.listeners.MapCreationMouseListener;
import constants.Constants;


/**
 * Class that handles the painting of tile-based world objects into
 * a given world. Renders graphics onto a JPanel.
 * 
 * @author Michael Marion
 * 
 */

@SuppressWarnings("serial")
public class MapCreationView extends JPanel {
    
    private MapCreationView singleton;

    private BufferedImage myCurrentTileImage;
    private String myCurrentTileName;
    private String myCurrentTileType;
    
    private CanvasTileManager myTileManager;
    private WorldCreationMap myWorldCreationMap;

    public MapCreationView () {

        // Set JPanel attributes
        setFocusable(true);
        this.setPreferredSize(Constants.MAP_CREATOR_SIZE);
        this.setBackground(Color.BLACK);
        
        myCurrentTileImage = null;
        myCurrentTileName = null;
        myCurrentTileType = null;

        // Instantiate the tile manager and world creation map.
        myTileManager = new CanvasTileManager(); // 15, 9
        myWorldCreationMap = myTileManager.getWorld();

        initListeners();
        
        singleton = this;
        this.setVisible(true);
    }

    /**
     * Attaches a keyboard and mouse listener to the map creation view
     * to allow user interactivity.
     */
    private void initListeners () {
        this.addKeyListener(new MapCreationKeyListener(this, myTileManager));
        this.addMouseListener(new MapCreationMouseListener(this, myTileManager));
    }

    /**
     * Paints the component with all tiles currently placed in the
     * world tile map.
     */
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (myWorldCreationMap.getWorldTileMap() != null) {
            for (Map.Entry<Loc, GenericTileWrapper> tile : myWorldCreationMap.getWorldTileMap().entrySet()) {
                paintTile((Graphics2D) g,
                          tile.getKey().getX(),
                          tile.getKey().getY(),
                          tile.getValue());
            }
        }
    }

    /**
     * Paints a tile at the given coordinate and records its
     * location in the WorldTileMap.
     * 
     * @param g
     * @param x
     * @param y
     */
    public void paintAndRecordTile (Graphics2D g, int x, int y) {
        paintTile(g, x, y);
        if(isValueSelected()) {
            myWorldCreationMap.put(new Loc(x,y),new GenericTileWrapper(myCurrentTileName, myCurrentTileType, myCurrentTileImage));  
        }       
    }

    /**
     * Paints a tile in the view at the given x-
     * and y-coordinates
     * 
     * @param g
     * @param x
     * @param y
     */
    public void paintTile (Graphics2D g, int x, int y) {
        if(isValueSelected()) {
            TexturePaint tp =
                    new TexturePaint(myCurrentTileImage,
                                     new Rectangle(0,
                                                   0,
                                                   (int) myTileManager.getTileWidth(),
                                                   (int) myTileManager.getTileHeight()));

            g.setPaint(tp);
            g.fillRect((int) myTileManager.getTileAnchorX(x),
                       (int) myTileManager.getTileAnchorY(y),
                       (int) myTileManager.getTileWidth(),
                       (int) myTileManager.getTileHeight());
        }
        
    }
    
    /**
     * Paints a tile in the view at the given x-
     * and y-coordinates
     * 
     * @param g
     * @param x
     * @param y
     */
    public void paintTile (Graphics2D g, int x, int y, GenericTileWrapper tile) {
        TexturePaint tp =
                new TexturePaint(tile.getImage(),
                                 new Rectangle(0,
                                               0,
                                               (int) myTileManager.getTileWidth(),
                                               (int) myTileManager.getTileHeight()));

        g.setPaint(tp);
        g.fillRect((int) myTileManager.getTileAnchorX(x),
                   (int) myTileManager.getTileAnchorY(y),
                   (int) myTileManager.getTileWidth(),
                   (int) myTileManager.getTileHeight());
    }
    
    public boolean isValueSelected() {
        return (myCurrentTileImage != null && myCurrentTileName !=null && myCurrentTileType != null);
    }

    /*
     * Getter and setter methods below
     */

    public BufferedImage getCurrentTileImage () {
        return myCurrentTileImage;
    }

    public void setCurrentTileImage (GenericTileWrapper gtw) {
        myCurrentTileImage = gtw.getImage();
    }
    
    public void setCurrentTileName (GenericTileWrapper gtw) {
        myCurrentTileName = gtw.getName();
    }
    
    public void setCurrentTileType (GenericTileWrapper gtw) {
        myCurrentTileType = gtw.getType();
    }

    public Map<Loc, GenericTileWrapper> getMyWorldTiles () {
        return myWorldCreationMap.getWorldTileMap();
    }

    public CanvasTileManager getTileManager () {
        return myTileManager;
    }

    public MapCreationView getMapCreationView() {
        if (singleton == null) {
            return new MapCreationView();
        }
        else {
            return singleton;
        }
    }
    
}
