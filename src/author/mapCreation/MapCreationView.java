package author.mapCreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import location.Loc;
import author.listeners.MapCreationKeyListener;
import author.listeners.MapCreationMouseListener;
import constants.Constants;


@SuppressWarnings("serial")
public class MapCreationView extends JPanel {

    // private WorldTiles myWorld;
    private BufferedImage myBackground;
    private CanvasTileManager myTileManager;
    private WorldCreationMap myWorld;
    private Map<Loc, GenericTileWrapper> myWorldTiles;

    public MapCreationView () {
        setFocusable(true);
        this.setPreferredSize(Constants.MAP_CREATOR_SIZE);

        // Try to get the image of the specified background.
        try {
            myBackground = ImageIO.read(new File(Constants.TEST_SHORTGRASS_PNG_FILEPATH));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
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
        g.drawImage(myBackground, 0, 0, getWidth(), getHeight(), null);
    }

    public void paintAndRecordTile (Graphics2D g, int x, int y) {
        double topLeftX = myTileManager.getTileAnchorX(x);
        double topLeftY = myTileManager.getTileAnchorY(y);

        TexturePaint tp =
                new TexturePaint(myBackground, new Rectangle(0, 0,
                                                             (int) myTileManager.getTileWidth(),
                                                             (int) myTileManager.getTileHeight()));

        super.paintComponent(g);
        g.setPaint(tp);
        g.fillRect((int) topLeftX, (int) topLeftY, (int) myTileManager.getTileWidth(),
                   (int) myTileManager.getTileHeight());

        myWorld.getWorldTileMap().put(new Loc((int) topLeftX, (int) topLeftY),
                                      new GenericTileWrapper("tileName", myBackground));

        repaint();
    }

    public void paintTile (Graphics2D g, int x, int y) {
        double topLeftX = myTileManager.getTileAnchorX(x);
        double topLeftY = myTileManager.getTileAnchorY(y);

        TexturePaint tp =
                new TexturePaint(myBackground, new Rectangle(0, 0,
                                                             (int) myTileManager.getTileWidth(),
                                                             (int) myTileManager.getTileHeight()));

        //super.paintComponent(g);
        g.setPaint(tp);
        g.fillRect((int) topLeftX, (int) topLeftY, (int) myTileManager.getTileWidth(),
                   (int) myTileManager.getTileHeight());
    }

    public BufferedImage getBackgroundImage () {
        return myBackground;
    }

    public void setBackgroundImage (GenericTileWrapper gtw) {
        myBackground = gtw.getImage();
    }

    @Override
    public void repaint () {
        if (myWorldTiles != null) {
            for (Map.Entry<Loc, GenericTileWrapper> tile : myWorldTiles.entrySet()) {
                paintTile((Graphics2D) this.getGraphics(),
                          tile.getKey().getX(), tile.getKey().getY());
            } 
        }
    }

    public Map<Loc, GenericTileWrapper> getMyWorldTiles () {
        return myWorldTiles;
    }

}
