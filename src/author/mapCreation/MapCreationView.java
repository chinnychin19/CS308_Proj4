package author.mapCreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import author.listeners.MapCreationKeyListener;
import author.listeners.MapCreationMouseListener;
import constants.Constants;


@SuppressWarnings("serial")
public class MapCreationView extends JPanel {

    private WorldTiles myWorld;
    private Image myBackground;
    private CanvasTileManager myTileManager;

    public MapCreationView () {
        setFocusable(true);
        //myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.setPreferredSize(Constants.MAP_CREATOR_SIZE);
        myBackground = new ImageIcon(Constants.SHORTGRASS_PNG_FILEPATH).getImage();
        myTileManager = new CanvasTileManager(); // 15, 9
        initListeners();
        
    }

    public WorldTiles getMyWorld () {
        return myWorld;
    }

    private void initListeners () {
        this.addKeyListener(new MapCreationKeyListener(myTileManager));
        this.addMouseListener(new MapCreationMouseListener(this, myTileManager));
    }

    public CanvasTileManager getTileManager () {
        return myTileManager;
    }

    public void paintComponent (Graphics g) {
        g.drawImage(myBackground, 0, 0, getWidth(), getHeight(), null);
    }

    public void paintTile (Graphics g, int x, int y, ImageObserver io) {
        double topLeftX = myTileManager.getTileAnchorX(x);
        double topLeftY = myTileManager.getTileAnchorY(y);
        super.paintComponent(g);
        g.drawRect((int) topLeftX, (int) topLeftY, (int) myTileManager.getTileWidth(), (int) myTileManager.getTileHeight());
        g.setColor(Color.RED);
        g.fillRect((int) topLeftX, (int) topLeftY, (int) myTileManager.getTileWidth(), (int) myTileManager.getTileHeight());
        //g.drawImage(myBackground, (int) myTileManager.getTileAnchorX(x), (int) myTileManager.getTileAnchorY(y), (int) myTileManager.getTileWidth(), (int) myTileManager.getTileHeight(), io);
    }

    public Image getBackgroundImage () {
        return myBackground;
    }

}
