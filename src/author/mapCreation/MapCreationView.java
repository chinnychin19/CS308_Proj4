package author.mapCreation;

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


@SuppressWarnings("serial")
public class MapCreationView extends JPanel {

    // private WorldTiles myWorld;
    private BufferedImage myCurrentTileImage;
    private CanvasTileManager myTileManager;
    private WorldCreationMap myWorld;
    private Map<Loc, GenericTileWrapper> myWorldTiles;

    public MapCreationView () {
        setFocusable(true);
        this.setPreferredSize(Constants.MAP_CREATOR_SIZE);

        // Try to get the image of the specified background.
        try {
            myCurrentTileImage = ImageIO.read(new File(Constants.TEST_FILE));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        myTileManager = new CanvasTileManager(); // 15, 9
        myWorld = myTileManager.getWorld();
        myWorldTiles = myWorld.getWorldTileMap();
        initListeners();
    }

    private void initListeners () {
        this.addKeyListener(new MapCreationKeyListener(this, myTileManager));
        this.addMouseListener(new MapCreationMouseListener(this, myTileManager));
    }

    public CanvasTileManager getTileManager () {
        return myTileManager;
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (myWorldTiles != null) {
            for (Map.Entry<Loc, GenericTileWrapper> tile : myWorldTiles.entrySet()) {
                paintTile((Graphics2D) this.getGraphics(),
                          tile.getKey().getX(), tile.getKey().getY());
            } 
        }
    }

    public void paintAndRecordTile (Graphics2D g, int x, int y) {
        paintTile(g, x, y);
        
        double topLeftX = myTileManager.getTileAnchorX(x);
        double topLeftY = myTileManager.getTileAnchorY(y);
        myWorld.getWorldTileMap().put(new Loc((int) topLeftX, (int) topLeftY),
                                      new GenericTileWrapper(Constants.TILENAME, myCurrentTileImage));
    }

    public void paintTile (Graphics2D g, int x, int y) {
        double topLeftX = myTileManager.getTileAnchorX(x);
        double topLeftY = myTileManager.getTileAnchorY(y);

        TexturePaint tp =
                new TexturePaint(myCurrentTileImage, new Rectangle(0, 0,
                                                             (int) myTileManager.getTileWidth(),
                                                             (int) myTileManager.getTileHeight()));

        g.setPaint(tp);
        g.fillRect((int) topLeftX, (int) topLeftY, (int) myTileManager.getTileWidth(),
                   (int) myTileManager.getTileHeight());
    }

    public BufferedImage getBackgroundImage () {
        return myCurrentTileImage;
    }

    public void setBackgroundImage (GenericTileWrapper gtw) {
        myCurrentTileImage = gtw.getImage();
    }

    public void repaint (Graphics g) {
        paintComponent(g);
    }

    public Map<Loc, GenericTileWrapper> getMyWorldTiles () {
        return myWorldTiles;
    }

}
